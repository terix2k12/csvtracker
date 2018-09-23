package de.phifo.persistance;

public enum ColumnType {
    DATE("DATE"),
    NUMBER("NUMBER"),
    TEXT("TEXT"),
    SELECTION("NUMBER"),
    SWITCH("BOOL"),
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