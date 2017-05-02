package com.github.artyomcool.dante.core.query;

public abstract class BaseRowReader<E> implements RowReader<E> {

    public static boolean getBoolean(Row row, int column) {
        return row.getLong(column) != 0;
    }

    public static Boolean getNullableBoolean(Row row, int column) {
        return row.isNull(column) ? null : getBoolean(row, column);
    }

    public static byte getByte(Row row, int column) {
        return (byte) row.getLong(column);
    }

    public static Byte getNullableByte(Row row, int column) {
        return row.isNull(column) ? null : getByte(row, column);
    }

    public static short getShort(Row row, int column) {
        return (short) row.getLong(column);
    }

    public static Short getNullableShort(Row row, int column) {
        return row.isNull(column) ? null : getShort(row, column);
    }

    public static int getInteger(Row row, int column) {
        return (int) row.getLong(column);
    }

    public static Integer getNullableInteger(Row row, int column) {
        return row.isNull(column) ? null : getInteger(row, column);
    }

    public static long getLong(Row row, int column) {
        return row.getLong(column);
    }

    public static Long getNullableLong(Row row, int column) {
        return row.isNull(column) ? null : row.getLong(column);
    }

    public static char getCharacter(Row row, int column) {
        return (char) row.getLong(column);
    }

    public static Character getNullableCharacter(Row row, int column) {
        return row.isNull(column) ? null : getCharacter(row, column);
    }

    public static float getFloat(Row row, int column) {
        return (float) row.getDouble(column);
    }

    public static Float getNullableFloat(Row row, int column) {
        return row.isNull(column) ? null : getFloat(row, column);
    }

    public static double getDouble(Row row, int column) {
        return row.getDouble(column);
    }

    public static Double getNullableDouble(Row row, int column) {
        return row.isNull(column) ? null : row.getDouble(column);
    }

    public static byte[] getBlob(Row row, int column) {
        return row.getBlob(column);
    }

    public static String getString(Row row, int column) {
        return row.getString(column);
    }

}
