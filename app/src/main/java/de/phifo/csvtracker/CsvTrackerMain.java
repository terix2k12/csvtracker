package de.phifo.csvtracker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import de.phifo.android.persistance.AndroidDAO;
import de.phifo.csvtracker.fragments.ListenFragment;
import de.phifo.csvtracker.persistance.CsvTrackerDatabase;
import de.phifo.csvtracker.sending.CsvTrackerInternetThread;
import de.phifo.persistance.ColumnContract;
import de.phifo.persistance.ColumnType;
import de.phifo.persistance.TableContract;

public class CsvTrackerMain {

    private CsvTrackerActivity activity;
    private CsvTrackerDatabase database;

    public CsvTrackerMain(CsvTrackerDatabase database, CsvTrackerActivity activity){
        this.activity = activity;
        this.database = database;
        activity.main = this;
    }

    public void InitWithMetaitems(){
        TableContract contract = getMetaTable();
        AddTable(contract, null, null);


        AndroidDAO dao = new AndroidDAO(database);

        TableContract defaultC = Default();
        activity.navi.LinkNavigation(defaultC, false);
        activity.GetTabsAdapter(defaultC);

        String curName = null;
        String name = null;
        List<ColumnContract> columns = new ArrayList<>();
        for(List<Object> row : dao.ReadAllObj(contract))
        {
            name = (String) row.get(0);
            if (curName == null) {
                curName = name;
            }

            if(!name.equals(curName))
            {
                AddTable(null, name, columns);
            }else{
                ColumnType type = (ColumnType)row.get(1);
                String colName = (String) row.get(2);
                columns.add(new ColumnContract(colName, type,0));
            }
        }

        if(name != null){
            AddTable(null, name, columns);
        }
     }

    private TableContract getMetaTable(){
        return new TableContract("CSVTABELLEN",
                new ColumnContract("Tabelle", ColumnType.TEXT,0),
                new ColumnContract("Spalte", ColumnType.TEXT,0),
                new ColumnContract("Typ", ColumnType.TEXT,0)
        );
    }

    private TableContract Default(){
        return  new TableContract("AUSGABEN",
                new ColumnContract("Datum", ColumnType.DATE,0),
                new ColumnContract("Konto", ColumnType.SELECTION,R.array.kto_array),
                new ColumnContract("Skonto", ColumnType.SELECTION,R.array.skto_array),
                new ColumnContract("Währung", ColumnType.SELECTION,R.array.cur_array),
                new ColumnContract("Betrag", ColumnType.NUMBER,0),
                new ColumnContract("Kategorie", ColumnType.SELECTION,R.array.kat_array),
                new ColumnContract("Unterkategorie", ColumnType.SELECTION, R.array.sub_array),
                new ColumnContract("Kommentar", ColumnType.TEXT,0)
        );
    }

    private void AddTable(TableContract meta, String name, List<ColumnContract> columns){
        TableContract contract;
        if(meta != null)
        {
            contract = meta;

            TabsPagerAdapter ada = activity.GetTabsAdapter(contract);
            ada.getListe().add(contract);
        }else{
            ColumnContract[] array = new ColumnContract[columns.size()];
            columns.toArray(array);
            contract = new TableContract(name, array );
        }

        activity.navi.LinkNavigation(contract, meta!=null);
    }

    public void AddItem(TableContract con, List<Object> s)  {
        AddToServer(con, s);
        AddToListe(con, s);
        AddToDatabase( con,  s);
    }

    private void AddToServer(TableContract con, List<Object> s)
    {
        CsvTrackerInternetThread t = new CsvTrackerInternetThread();
        t.senden(con, s);

        try {
            activity.toast(t.get());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            activity.handleException(e);
        } catch (ExecutionException e) {
            activity.handleException(e);
        }
    }

    private void AddToListe(TableContract con, List<Object> s){
        final ListenFragment listenFragment = (ListenFragment) activity.GetTabsAdapter(con)
                .getItem(1);
        listenFragment.add(s);
    }

    private void AddToDatabase(TableContract con, List<Object> s)
    {
        try {
            database.Write(con, s);
        } catch ( Exception e) {
            activity.handleException(e);
        }
    }
}