package com.github.artyomcool.dante;

import com.squareup.javapoet.ClassName;

public class GeneratedMigration {

    private final ClassName className;
    private final int maxVersion;

    public GeneratedMigration(ClassName className, int maxVersion) {
        this.className = className;
        this.maxVersion = maxVersion;
    }

    public ClassName getClassName() {
        return className;
    }

    public int getMaxVersion() {
        return maxVersion;
    }
}
