package de.phifo.android.persistance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import de.phifo.persistance.ColumnContract;
import de.phifo.persistance.IDatabase;
import de.phifo.persistance.TableContract;

public class AndroidDatabase extends SQLiteOpenHelper implements IDatabase {

    public AndroidDatabase(Context context, String name, int version) {
        super(context, name, null, version);
    }

    public void createTableIfNotExists(TableContract table){
        String create = "CREATE TABLE IF NOT EXISTS " + table.getTableName() + "(" ;
        create += "_ID INTEGER PRIMARY KEY " ;

        for(ColumnContract column : table.getColumns())
        {
            create += ", " + column.getColumnName() + " " + column.getType();
        }
        create += ")";

        getWritableDatabase().execSQL(create);
    }

    private static final String deleteTable(TableContract table){
        return "DROP TABLE IF EXISTS " + table.getTableName();
    }

    public void onCreate(SQLiteDatabase db) {

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL("DROP TABLE IF EXISTS ENTRY");
        db.execSQL("DROP TABLE IF EXISTS DATEN3");
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}