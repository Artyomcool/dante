package com.github.artyomcool.dante.core.dao;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public interface MigrationInfo {

    List<Migration> migrations(SQLiteDatabase db, int onVersion);

}
