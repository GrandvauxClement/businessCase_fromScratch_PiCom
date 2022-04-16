package com.picom.models;

import com.picom.models.db.TableName;

public abstract class AbstractEntity {

    protected TableName tableName;

    public AbstractEntity(TableName tableName) {
        this.tableName = tableName;
    }

    public TableName getTableName() {
        return tableName;
    }
}
