package de.phifo.android.persistance;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import de.phifo.persistance.ColumnContract;
import de.phifo.persistance.TableContract;

public class AndroidDAO {
    // TODO ON ASYNC THREAD

    private AndroidDatabase dbHelper;

    public AndroidDAO(AndroidDatabase database) {
        dbHelper = database;
    }

    public void DeleteAll(TableContract tableContract) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM " + tableContract.getTableName() + ";");
        dbHelper.close();
    }

    public void remove(Object obj) {

    }

    public void Write(TableContract tableContract, List<Object> urls) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        int i = 0;
        for (ColumnContract column : tableContract.getColumns()) {
            values.put(column.getColumnName(), urls.get(i).toString());
            i++;
        }

        long newRowId = db.insert(tableContract.getTableName(), null, values);

        dbHelper.close();
    }

    public List<List<Object>> ReadAllObj(TableContract tableContract) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        List<String> proj = new ArrayList<>();
        for (ColumnContract column : tableContract.getColumns()) {
            proj.add(column.getColumnName());
        }
        String[] projection = new String[proj.size()];
        proj.toArray(projection);

        // Filter results WHERE "title" = 'My Title'
        // String selection =  "COLUMN_NAME_TITLE = ?";
        // String[] selectionArgs = { "My Title" };

        // How you want the results sorted in the resulting Cursor
        String sortOrder = tableContract.getColumns().get(0).getColumnName() + " DESC";

        Cursor cursor = db.query(
                tableContract.getTableName(),                     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        List<List<Object>> rows = new ArrayList<>();
        while (cursor.moveToNext()) {
            List<Object> items = new ArrayList<>();
            for (ColumnContract column : tableContract.getColumns()) {
                int idx = cursor.getColumnIndexOrThrow(column.getColumnName());
                switch (column.getType()) {
                    default:
                        String itemString = cursor.getString(idx);
                        items.add(itemString);
                        break;
                    /*case DECIMAL:
                        long itemLong = cursor.getLong(idx);
                        items.add(itemLong);
                        break;*/
                }
            }
            rows.add(items);
        }
        cursor.close();
        return rows;
    }

    /*public void Delete(SQLiteDatabase db) throws Exception {
        throw new Exception("not implemented");
        // Define 'where' part of query.
        // String selection =  "COLUMN_NAME_TITLE LIKE ?";
        // Specify arguments in placeholder order.
        // String[] selectionArgs = { "MyTitle" };
        // Issue SQL statement.
        // db.delete("TABLE_NAME", selection, selectionArgs);
    }*/
}