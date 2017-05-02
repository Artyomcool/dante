package com.github.artyomcool.dante.core.migration;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Information about custom migrations.
 */
public interface MigrationInfo {

    /**
     * Returns list of migration for a version.
     *
     * @param db        database
     * @param onVersion migration version
     * @return list of migrations
     * @see com.github.artyomcool.dante.annotation.Migration
     */
    List<Migration> migrations(SQLiteDatabase db, int onVersion);

}
