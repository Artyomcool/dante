package com.github.artyomcool.dante.core.query;

/**
 * Helper base class for row readers, provides method that useful for generating readers.
 * @param <E> entity
 */
public abstract class BaseRowReader<E> implements RowReader<E> {

    /**
     * Returns boolean from the row.
     * @param row row
     * @param column column index
     * @return true, if {@link Row#getLong(int)} != 0
     */
    public static boolean getBoolean(Row row, int column) {
        return row.getLong(column) != 0;
    }

    /**
     * Returns nullable boolean from the row.
     * @param row row
     * @param column column index
     * @return null, if {@link Row#isNull(int)}, {@link #getBoolean(Row, int)} otherwise
     */
    public static Boolean getNullableBoolean(Row row, int column) {
        return row.isNull(column) ? null : getBoolean(row, column);
    }

    /**
     * Returns byte from the row.
     * @param row row
     * @param column column index
     * @return {@link Row#getLong(int)} casted to byte
     */
    public static byte getByte(Row row, int column) {
        return (byte) row.getLong(column);
    }

    /**
     * Returns nullable byte from the row.
     * @param row row
     * @param column column index
     * @return {@link Row#getLong(int)} casted to byte or null
     */
    public static Byte getNullableByte(Row row, int column) {
        return row.isNull(column) ? null : getByte(row, column);
    }

    /**
     * Returns short from the row.
     * @param row row
     * @param column column index
     * @return {@link Row#getLong(int)} casted to short
     */
    public static short getShort(Row row, int column) {
        return (short) row.getLong(column);
    }

    /**
     * Returns nullable short from the row.
     * @param row row
     * @param column column index
     * @return {@link Row#getLong(int)} casted to short or null
     */
    public static Short getNullableShort(Row row, int column) {
        return row.isNull(column) ? null : getShort(row, column);
    }

    /**
     * Returns int from the row.
     * @param row row
     * @param column column index
     * @return {@link Row#getLong(int)} casted to int
     */
    public static int getInteger(Row row, int column) {
        return (int) row.getLong(column);
    }

    /**
     * Returns nullable int from the row.
     * @param row row
     * @param column column index
     * @return {@link Row#getLong(int)} casted to int or null
     */
    public static Integer getNullableInteger(Row row, int column) {
        return row.isNull(column) ? null : getInteger(row, column);
    }

    /**
     * Returns long from the row.
     * @param row row
     * @param column column index
     * @return {@link Row#getLong(int)}
     */
    public static long getLong(Row row, int column) {
        return row.getLong(column);
    }

    /**
     * Returns nullable long from the row.
     * @param row row
     * @param column column index
     * @return {@link Row#getLong(int)} or null
     */
    public static Long getNullableLong(Row row, int column) {
        return row.isNull(column) ? null : row.getLong(column);
    }

    /**
     * Returns char from the row.
     * @param row row
     * @param column column index
     * @return {@link Row#getLong(int)} casted to char
     */
    public static char getCharacter(Row row, int column) {
        return (char) row.getLong(column);
    }

    /**
     * Returns nullable char from the row.
     * @param row row
     * @param column column index
     * @return {@link Row#getLong(int)} casted to char or null
     */
    public static Character getNullableCharacter(Row row, int column) {
        return row.isNull(column) ? null : getCharacter(row, column);
    }

    /**
     * Returns float from the row.
     * @param row row
     * @param column column index
     * @return {@link Row#getDouble(int)} casted to float
     */
    public static float getFloat(Row row, int column) {
        return (float) row.getDouble(column);
    }

    /**
     * Returns nullable float from the row.
     * @param row row
     * @param column column index
     * @return {@link Row#getDouble(int)} casted to float or null
     */
    public static Float getNullableFloat(Row row, int column) {
        return row.isNull(column) ? null : getFloat(row, column);
    }

    /**
     * Returns double from the row.
     * @param row row
     * @param column column index
     * @return {@link Row#getDouble(int)}
     */
    public static double getDouble(Row row, int column) {
        return row.getDouble(column);
    }

    /**
     * Returns nullable double from the row.
     * @param row row
     * @param column column index
     * @return {@link Row#getDouble(int)} or null
     */
    public static Double getNullableDouble(Row row, int column) {
        return row.isNull(column) ? null : row.getDouble(column);
    }

    /**
     * Returns byte[] from the row.
     * @param row row
     * @param column column index
     * @return {@link Row#getBlob(int)}
     */
    public static byte[] getBlob(Row row, int column) {
        return row.getBlob(column);
    }

    /**
     * Returns string from the row.
     * @param row row
     * @param column column index
     * @return {@link Row#getString(int)}
     */
    public static String getString(Row row, int column) {
        return row.getString(column);
    }

}
