package com.github.artyomcool.dante.core.query;

public interface RowReader<E> {

    E readEntity(Row cursor);

}
