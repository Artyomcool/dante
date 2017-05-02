@Grab(group = 'org.ow2.asm', module = 'asm', version = '5.2')
import org.objectweb.asm.*

import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import java.util.zip.ZipOutputStream

import static org.objectweb.asm.Opcodes.*

def originalJar = "http://central.maven.org/maven2/com/google/android/android/4.1.1.4/android-4.1.1.4.jar"
ZipInputStream zipInputStream = new ZipInputStream(originalJar.toURL().openStream())

File mockJarRepo = new File('../../../../mock-android-jar')
mockJarRepo.mkdirs()

def file = new File(mockJarRepo, 'android.jar')
file.delete()

ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(file))


for (int pos = 0; ;) {
    def nextEntry = zipInputStream.nextEntry
    if (nextEntry == null) {
        break
    }
    if (!nextEntry.name.endsWith('.class')) {
        continue
    }
    if (nextEntry.name.startsWith('java/')
            || nextEntry.name.startsWith('javax/')
            || nextEntry.name.startsWith('junit/')) {
        continue
    }
    print '.'
    if (++pos % 60 == 0) {
        println()
    }
    zipOutputStream.putNextEntry(new ZipEntry(nextEntry.name))

    ClassReader classReader = new ClassReader(zipInputStream)
    ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES) {
        @Override
        protected String getCommonSuperClass(String type1, String type2) {
            "java/lang/Object"
        }
    }
    ClassVisitor visitor = new ClassVisitor(ASM5, classWriter) {

        String superName
        String className

        @Override
        void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
            this.superName = superName
            this.className = name
            super.visit(version, access & ~ACC_FINAL, name, signature, superName, interfaces)
        }

        @Override
        MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            if (name.equals("<clinit>")) {
                return null
            }
            access &= ~ACC_FINAL
            def isConstructor = name.equals('<init>')
            def isStatic = (access & ACC_STATIC) == ACC_STATIC
            if (isConstructor) {
                access &= ~ACC_PRIVATE
                access &= ~ACC_PROTECTED
                access |= ACC_PUBLIC
            }
            def needToCallSuperConstructor = isConstructor

            def del = super.visitMethod(access, name, desc, signature, exceptions)
            return new MethodVisitor(ASM5, del) {

                @Override
                void visitInsn(int opcode) {
                    if (needToCallSuperConstructor) {
                        mv.visitInsn(opcode)
                    }
                }

                @Override
                void visitIntInsn(int opcode, int operand) {
                    if (needToCallSuperConstructor) {
                        mv.visitIntInsn(opcode, operand)
                    }
                }

                @Override
                void visitVarInsn(int opcode, int var) {
                    if (needToCallSuperConstructor) {
                        mv.visitVarInsn(opcode, var)
                    }
                }

                @Override
                void visitTypeInsn(int opcode, String type) {
                    if (needToCallSuperConstructor) {
                        mv.visitTypeInsn(opcode, type)
                    }
                }

                @Override
                void visitFieldInsn(int opcode, String owner, String n, String d) {
                    if (needToCallSuperConstructor) {
                        mv.visitFieldInsn(opcode, owner, n, d)
                    }
                }

                @Override
                void visitInvokeDynamicInsn(String n, String d, Handle bsm, Object... bsmArgs) {
                    if (needToCallSuperConstructor) {
                        mv.visitInvokeDynamicInsn(n, d, bsm, bsmArgs)
                    }
                }

                @Override
                void visitJumpInsn(int opcode, Label label) {
                    if (needToCallSuperConstructor) {
                        mv.visitJumpInsn(opcode, label)
                    }
                }

                @Override
                void visitLabel(Label label) {
                    if (isConstructor) {
                        mv.visitLabel(label)
                    }
                }

                @Override
                void visitLdcInsn(Object cst) {
                    if (needToCallSuperConstructor) {
                        mv.visitLdcInsn(cst)
                    }
                }

                @Override
                void visitIincInsn(int var, int increment) {
                    if (needToCallSuperConstructor) {
                        mv.visitIincInsn(var, increment)
                    }
                }

                @Override
                void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels) {
                    if (needToCallSuperConstructor) {
                        mv.visitTableSwitchInsn(min, max, dflt, labels)
                    }
                }

                @Override
                void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
                    if (needToCallSuperConstructor) {
                        mv.visitLookupSwitchInsn(dflt, keys, labels)
                    }
                }

                @Override
                void visitMultiANewArrayInsn(String d, int dims) {
                    if (needToCallSuperConstructor) {
                        mv.visitMultiANewArrayInsn(d, dims)
                    }
                }

                @Override
                void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
                }

                @Override
                void visitLocalVariable(String n, String d, String s, Label start, Label end, int index) {
                    if (needToCallSuperConstructor) {
                        mv.visitLocalVariable(n, d, s, start, end, index)
                    }
                }

                @Override
                void visitLineNumber(int line, Label start) {}

                @Override
                AnnotationVisitor visitInsnAnnotation(int typeRef, TypePath typePath, String d, boolean visible) {
                    null
                }

                @Override
                AnnotationVisitor visitTryCatchAnnotation(int typeRef, TypePath typePath, String d, boolean visible) {
                    null
                }

                @Override
                AnnotationVisitor visitLocalVariableAnnotation(int typeRef, TypePath typePath, Label[] start, Label[] end, int[] index, String d, boolean visible) {
                    null
                }

                @Override
                void visitMethodInsn(int opcode, String owner, String n, String d, boolean itf) {
                    if (needToCallSuperConstructor) {
                        if (!itf && opcode == INVOKESPECIAL) {
                            if (superName == owner || name == owner && n == "<init>") {
                                mv.visitMethodInsn(opcode, owner, n, d, itf)
                                needToCallSuperConstructor = false
                            }
                        }
                    }
                    if (needToCallSuperConstructor) {
                        mv.visitMethodInsn(opcode, owner, n, d, itf)
                    }
                }

                @Override
                void visitMaxs(int maxStack, int maxLocals) {
                    def visitConst = { int i ->
                        if (i < 6) {
                            mv.visitInsn(ICONST_0 + i)
                        } else {
                            mv.visitLdcInsn(i)
                        }
                    }

                    def types = Type.getArgumentTypes(desc) as List
                    if (!isStatic) {
                        types.add(0, Type.getType(Object.class))
                    }

                    mv.visitLdcInsn(className)
                    mv.visitLdcInsn(name)
                    mv.visitLdcInsn(desc)
                    visitConst(types.size())
                    mv.visitTypeInsn(ANEWARRAY, "java/lang/Object")

                    int p = 0
                    for (int i = 0; i < types.size(); i++) {
                        mv.visitInsn(DUP)
                        visitConst(i)
                        switch (types[i].sort) {
                            case Type.BOOLEAN:
                                mv.visitVarInsn(ILOAD, p)
                                mv.visitMethodInsn(INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;", false)
                                break
                            case Type.BYTE:
                                mv.visitVarInsn(ILOAD, p)
                                mv.visitMethodInsn(INVOKESTATIC, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;", false)
                                break
                            case Type.CHAR:
                                mv.visitVarInsn(ILOAD, p)
                                mv.visitMethodInsn(INVOKESTATIC, "java/lang/Character", "valueOf", "(C)Ljava/lang/Character;", false)
                                break
                            case Type.SHORT:
                                mv.visitVarInsn(ILOAD, p)
                                mv.visitMethodInsn(INVOKESTATIC, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;", false)
                                break
                            case Type.INT:
                                mv.visitVarInsn(ILOAD, p)
                                mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false)
                                break
                            case Type.LONG:
                                mv.visitVarInsn(LLOAD, p++)
                                mv.visitMethodInsn(INVOKESTATIC, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;", false)
                                break
                            case Type.FLOAT:
                                mv.visitVarInsn(FLOAD, p)
                                mv.visitMethodInsn(INVOKESTATIC, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;", false)
                                break
                            case Type.DOUBLE:
                                mv.visitVarInsn(DLOAD, p++)
                                mv.visitMethodInsn(INVOKESTATIC, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;", false)
                                break
                            case Type.ARRAY:
                            case Type.OBJECT:
                                mv.visitVarInsn(ALOAD, p)
                                break
                            default:
                                throw new Exception(types[i].sort as String)
                        }
                        mv.visitInsn(AASTORE)
                        p++
                    }

                    mv.visitMethodInsn(INVOKESTATIC, 'com/github/artyomcool/dante/AndroidMethodHandler', "handle", '(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;', false)

                    def returnType = Type.getReturnType(desc)
                    switch (returnType.sort) {
                        case Type.VOID:
                            mv.visitInsn(POP)
                            mv.visitInsn(RETURN)
                            break
                        case Type.BOOLEAN:
                            mv.visitTypeInsn(CHECKCAST, "java/lang/Boolean");
                            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Boolean", "booleanValue", "()Z", false);
                            mv.visitInsn(IRETURN);
                            break
                        case Type.BYTE:
                            mv.visitTypeInsn(CHECKCAST, "java/lang/Byte");
                            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Byte", "byteValue", "()B", false);
                            mv.visitInsn(IRETURN);
                            break
                        case Type.CHAR:
                            mv.visitTypeInsn(CHECKCAST, "java/lang/Character");
                            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Character", "charValue", "()C", false);
                            mv.visitInsn(IRETURN);
                            break
                        case Type.SHORT:
                            mv.visitTypeInsn(CHECKCAST, "java/lang/Short");
                            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Short", "shortValue", "()S", false);
                            mv.visitInsn(IRETURN);
                            break
                        case Type.INT:
                            mv.visitTypeInsn(CHECKCAST, "java/lang/Integer");
                            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Integer", "intValue", "()I", false);
                            mv.visitInsn(IRETURN);
                            break
                        case Type.LONG:
                            mv.visitTypeInsn(CHECKCAST, "java/lang/Long");
                            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Long", "longValue", "()J", false);
                            mv.visitInsn(LRETURN);
                            break
                        case Type.FLOAT:
                            mv.visitTypeInsn(CHECKCAST, "java/lang/Float");
                            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Float", "floatValue", "()F", false);
                            mv.visitInsn(FRETURN);
                            break
                        case Type.DOUBLE:
                            mv.visitTypeInsn(CHECKCAST, "java/lang/Double");
                            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Double", "doubleValue", "()D", false);
                            mv.visitInsn(DRETURN);
                            break
                        case Type.ARRAY:
                        case Type.OBJECT:
                            mv.visitTypeInsn(CHECKCAST, returnType.internalName);
                            mv.visitInsn(ARETURN);
                            break
                        default:
                            throw new Exception(returnType.sort as String)
                    }
                    super.visitMaxs(maxStack, maxLocals)
                }
            }
        }
    }
    classReader.accept(visitor, ClassReader.SKIP_FRAMES)
    zipOutputStream.write(classWriter.toByteArray())
}
zipInputStream.close()
zipOutputStream.close()