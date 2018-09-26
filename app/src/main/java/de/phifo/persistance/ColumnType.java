package de.phifo.persistance;

public enum ColumnType {
    DATE("DATE"),
    INTEGER("INTEGER"),
    TEXT("TEXT"),
    SELECTION("INTEGER"),
    BOOL("BOOL"),
;

     ColumnType(String sql)
            {
                this.sql = sql;
            }

     private String sql;

     public String getSql(){
         return sql;
     }

}