package com.github.artyomcool.dante.core.query;

public interface Row {

    boolean isNull(int column);

    String getString(int column);

    byte[] getBlob(int column);

    long getLong(int column);

    double getDouble(int column);
}
