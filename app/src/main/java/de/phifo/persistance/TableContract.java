package de.phifo.persistance;

import java.util.ArrayList;
import java.util.List;

public class TableContract {

    private String tableName;

    private List<ColumnContract> columns = new ArrayList<>();

    public TableContract(String tableName, ColumnContract... columns) {
        this.tableName = tableName;

        ColumnContract idColumn = new ColumnContract("_ID", ColumnType.INTEGER, 0);
        this.columns.add(idColumn);
        idColumn.keyProperty = true;
        this.columns.add(new ColumnContract("_SYNC", ColumnType.BOOL, 0));

        for (ColumnContract column : columns) {
            this.columns.add(column);
        }
    }

    public String getTableName() {
        return tableName;
    }

    public List<ColumnContract> getColumns() {
        return columns;
    }

}