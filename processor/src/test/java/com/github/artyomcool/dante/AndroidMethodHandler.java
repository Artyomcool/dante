package com.github.artyomcool.dante;

public class AndroidMethodHandler {

    public interface AndroidMethodDelegate {
        Object handle(String owner, String name, String desc, Object... args);
    }

    private static AndroidMethodDelegate delegate;

    public static void setDelegate(AndroidMethodDelegate delegate) {
        AndroidMethodHandler.delegate = delegate;
    }

    public static Object handle(String owner, String name, String desc, Object... args) {
        if (delegate != null) {
            return delegate.handle(owner, name, desc, args);
        }
        if (name.equals("<init>") || name.equals("<clinit>")) {
            return null;
        }
        throw new UnsupportedOperationException("Unsupported operation: " + owner + "#" + name + "/" + desc);
    }

}
