package de.phifo.csvtracker.persistance;

import android.content.Context;

import java.util.List;

import de.phifo.android.persistance.AndroidDAO;
import de.phifo.android.persistance.AndroidDatabase;
import de.phifo.persistance.ColumnContract;
import de.phifo.persistance.TableContract;

public class CsvTrackerDatabase extends AndroidDatabase {

    public static final int DATABASE_VERSION = 7;
    public static final String DATABASE_NAME = "FeedReader.db";

    public AndroidDAO dao;

    public CsvTrackerDatabase(Context context) {
        super(context, DATABASE_NAME, DATABASE_VERSION);

        dao = new AndroidDAO(this);
        /*
        MetaTableContract contract = new MetaTableContract();
        createTableIfNotExists(contract);

        AndroidDAO metaTableDao = new AndroidDAO(this, contract);

        ausgabenDao = new AusgabenDAO(this, new AusgabenContract());

        List<List<Object>> result = metaTableDao.ReadAll();


        allTables = new ArrayList<>();

         TableContract newTable = null;
        String current = "";
        for(List<Object> row : result)
        {
            if(!row.get(1).equals(current)){
                newTable = new TableContract(current);
                allTables.add(newTable);
            }

            EingabeFeldTyp typ = EingabeFeldTyp.valueOf((String)row.get(2));
            String name = (String)row.get(3);
            newTable.getColumns().add(new EingabeFeld(name, "", typ));
        }

        for(TableContract toCreate : allTables){
            db.createTableIfNotExists(toCreate);
        }*/

        // database = new AndroidDAO(eingabeFelder);
    }

    public void update(TableContract con, ColumnContract col, long id, Object newValue) {
        dao.update(con, col, id , newValue);
    }

    public void insert(TableContract con, List<Object> cnt) {
        dao.insert(con, cnt);
    }

    public void delete(TableContract con, long id) {
        dao.delete(con, id);
    }
}
