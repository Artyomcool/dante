/*
 * Copyright (c)  2015-2016, Artyom Drozdov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.artyomcool.dante.core.dao;

import android.database.sqlite.SQLiteDatabase;
import com.github.artyomcool.dante.core.EntityInfo;
import com.github.artyomcool.dante.core.Property;
import com.github.artyomcool.dante.core.db.DatabaseOpener;
import com.github.artyomcool.dante.core.migration.Migration;
import com.github.artyomcool.dante.core.migration.MigrationInfo;

import java.util.ArrayList;
import java.util.List;

public class DaoMaster {

    private final DaoRegistry delegate;
    private final DatabaseOpener opener;

    public DaoMaster(DatabaseOpener opener) {
        this(opener, defaultRegistry());
    }

    public DaoMaster(DatabaseOpener opener, DaoRegistry delegate) {
        this.opener = opener;
        this.delegate = delegate;
    }

    public void init() {
        SQLiteDatabase db = opener.open();
        db.beginTransaction();
        try {
            delegate.init(db);
            int schemeVersion = delegate.getVersion();
            int dbVersion = db.getVersion();
            if (dbVersion > schemeVersion) {
                onDowngrade(db);
            } else if (dbVersion < schemeVersion) {
                if (dbVersion == 0) {
                    onCreate();
                } else {
                    onUpgrade(db);
                }
            }
            db.setVersion(schemeVersion);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    private void onCreate() {
        for (Dao<?> dao : delegate.getDao()) {
            dao.createTable();
        }
    }

    private void onDowngrade(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            for (final Dao<?> dao : delegate.getDao()) {
                dao.dropTable();
            }
            onCreate();
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    private void onUpgrade(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            List<? extends MigrationInfo> customMigrations = delegate.createCustomMigrations();
            for (int i = db.getVersion() + 1; i <= delegate.getVersion(); i++) {
                for (Migration migration : getMigrations(i)) {
                    migration.migrate();
                }
                for (MigrationInfo info : customMigrations) {
                    for (Migration migration : info.migrations(db, i)) {
                        migration.migrate();
                    }
                }
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    private List<Migration> getMigrations(final int version) {
        List<Migration> result = new ArrayList<>();
        for (final Dao<?> dao : delegate.getDao()) {
            if (dao.getSinceVersion() == version) {
                result.add(new Migration() {
                    @Override
                    public void migrate() {
                        dao.ensureTable(version);
                    }
                });
            } else {
                for (final Property property : dao.getEntityInfo().getProperties()) {
                    if (property.getSinceVersion() == version) {
                        result.add(new Migration() {
                            @Override
                            public void migrate() {
                                dao.ensureProperty(property, version);
                            }
                        });
                    }
                    if (property.getIndexedSince() == version) {
                        result.add(new Migration() {
                            @Override
                            public void migrate() {
                                dao.ensureIndex(property, version);
                            }
                        });
                    }
                }
            }
        }
        return result;
    }

    public <T> T queries(Class<T> clazz) {
        return delegate.queries(clazz);
    }

    public <T> Dao<T> dao(Class<T> clazz) {
        return delegate.dao(clazz);
    }

    public <T> EntityInfo<T> entity(Class<T> clazz) {
        return delegate.entity(clazz);
    }

    //TODO explain what android version has problem with multi-catch
    @SuppressWarnings("TryWithIdenticalCatches")
    private static DaoRegistry defaultRegistry() {
        try {
            return (DaoRegistry) Class.forName("com.github.artyomcool.dante.DefaultRegistry").newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
