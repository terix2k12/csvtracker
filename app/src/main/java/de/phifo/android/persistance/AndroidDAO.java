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

    public void update(TableContract con, ColumnContract col, long id, Object newValue) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(col.getColumnName(), newValue.toString());

        String whereClause = "_ID = ?";
        String[] whereArgs = {"" + id};

        db.update(con.getTableName(), values, whereClause, whereArgs);

        db.close();
    }

    public void insert(TableContract tableContract, List<Object> newItem) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        // _ID is added automatically
        values.put("_SYNC", false);

        int i = 0;
        for (ColumnContract column : tableContract.getColumns()) {
            // TODO toString method here must be replaced with a more generic interface
            // TODO must handle nullables

            if (i > 1) { // skip sync and id
                Object v = newItem.get(i - 2);
                values.put(column.getColumnName(), v.toString());
            }

            i++;
        }

        long newRowId = db.insert(tableContract.getTableName(), null, values);

        dbHelper.close();

        // Add "synced" and "id" elements
        newItem.add(0, false);
        newItem.add(0, newRowId);
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
                tableContract.getTableName(),  // The table to query
                projection,                    // The columns to return
                null,                  // The columns for the WHERE clause
                null,               // The values for the WHERE clause
                null,                   // don't group the rows
                null,                    // don't filter by row groups
                sortOrder                       // The sort order
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

    public void deleteAll(TableContract tableContract) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM " + tableContract.getTableName() + ";");
        dbHelper.close();
    }

    public void delete(TableContract tableContract, long id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String whereClause = "_ID = ?";
        String[] whereArgs = {"" + id};

        db.delete(tableContract.getTableName(), whereClause, whereArgs);

        db.close();
    }

}