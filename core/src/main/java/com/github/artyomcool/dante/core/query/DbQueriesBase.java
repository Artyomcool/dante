package com.github.artyomcool.dante.core.query;

import android.database.sqlite.SQLiteDatabase;
import com.github.artyomcool.dante.core.EntityInfo;
import com.github.artyomcool.dante.core.Property;
import com.github.artyomcool.dante.core.Registry;

public class DbQueriesBase {

    private final SQLiteDatabase db;
    private final Registry registry;

    public DbQueriesBase(SQLiteDatabase db, Registry registry) {
        this.db = db;
        this.registry = registry;
    }

    protected final <E> String query(Class<E> entity, String where) {
        EntityInfo<E> info = entity(entity);

        StringBuilder tmp = new StringBuilder();
        tmp.append("SELECT ");

        allColumns(info, tmp);

        tmp.append(" FROM \"")
                .append(registry.dao(entity).getTableName())
                .append("\"");

        if (!where.isEmpty()) {
            tmp.append(" WHERE ").append(where);
        }

        return tmp.toString();
    }

    protected final SQLiteDatabase getDb() {
        return db;
    }

    protected final <E> RowReader<E> reader(Class<E> clazz) {
        return entity(clazz).getCachedOrSimpleRowReader();
    }

    protected final <E> QueryImpl<E> create(EntityIteratorFactory<E> iteratorFactory) {
        return new QueryImpl<>(iteratorFactory);
    }

    private <E> EntityInfo<E> entity(Class<E> clazz) {
        return registry.entity(clazz);
    }

    public static void allColumns(EntityInfo<?> entityInfo, StringBuilder builder) {
        for (Property property : entityInfo.getProperties()) {
            builder.append("\"").append(property.getColumnName()).append("\"").append(',');
        }
        builder.setLength(builder.length() - 1);
    }

}
