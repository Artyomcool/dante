package com.github.artyomcool.dante;

public class Statement {

    private final String code;
    private final Object[] args;

    public Statement(String code, Object... args) {
        this.code = code;
        this.args = args;
    }

    public String getCode() {
        return code;
    }

    public Object[] getArgs() {
        return args;
    }
}
