package de.phifo.persistance;

public class ColumnContract {
    private String columnName;
    private ColumnType columnType;

    private int properties;
    public boolean keyProperty;

    public ColumnContract(String columnName, ColumnType columnType, int properties) {
        this.columnName = columnName;
        this.columnType = columnType;
        this.properties = properties;
    }

    public ColumnType getType() {
        return columnType;
    }

    public String getColumnName() {
        return columnName;
    }

    public int getProperties() {
        return properties;
    }

    public boolean isKey() { return keyProperty; }

}
