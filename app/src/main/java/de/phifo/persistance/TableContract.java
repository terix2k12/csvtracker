package de.phifo.persistance;

import java.util.ArrayList;
import java.util.List;

public class TableContract {

    private String tableName;

    private List<ColumnContract> columns = new ArrayList<>();

    public TableContract(String tableName, ColumnContract... columns) {
        this.tableName = tableName;

        // this.columns.add(new EingabeFeld("_ID", "Id", EingabeFeldTyp.DECIMAL));

        for(ColumnContract column : columns){
            this.columns.add(column);
        }

       /* addColumn( "DatumDD" , ColumnType.NUMBER);
        addColumn(  "DatumMM", ColumnType.NUMBER);
        addColumn(  "DatumYY", ColumnType.NUMBER);
        addColumn(  "Datum", ColumnType.DATE);
        addColumn(  "Konto", ColumnType.SELECTION);
        addColumn( "Skonto", ColumnType.SWITCH);
        addColumn(  "Waehrung", ColumnType.SELECTION);
        addColumn(  "Betrag", ColumnType.NUMBER);
        addColumn(  "Kategorie", ColumnType.SELECTION);
        addColumn(  "Unterkategorie", ColumnType.SELECTION);
        addColumn(  "Kommentar", ColumnType.TEXT); */
    }

   /* private ColumnType type2type(EingabeFeldTyp typ){
        switch(typ){
            case SPINNER:
                return ColumnType.SELECTION;
            case DATUM:
                return ColumnType.DATE;
            default:
            case TEXT:
                return ColumnType.TEXT;
            case DECIMAL:
                return ColumnType.NUMBER;
        }
    }*/

    public String getTableName() {
        return tableName;
    }

    public List<ColumnContract> getColumns() {
        return columns;
    }
}