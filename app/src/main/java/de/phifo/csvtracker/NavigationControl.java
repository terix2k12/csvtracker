package de.phifo.csvtracker;

import android.support.design.widget.NavigationView;

import android.view.Menu;
import android.view.MenuItem;

import java.util.HashMap;
import java.util.Map;

import de.phifo.persistance.TableContract;

public class NavigationControl implements NavigationView.OnNavigationItemSelectedListener {

    private CsvTrackerActivity activity;

    private Map<Integer, TableContract> map = new HashMap<>();

    private NavigationView navigationView;

    public NavigationControl(CsvTrackerActivity activity){
        this.activity = activity;

        navigationView = (NavigationView) activity.findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_einstellungen) {
            // Setings
        } else   {

            TableContract table = map.get(id);

            activity.activate(table);
        }

        return true;
    }

    public void LinkNavigation(TableContract tableContract, boolean isMeta) {
        if(isMeta)
        {
            map.put(R.id.nav_tabellen, tableContract);
        }else{
            Menu menu = navigationView.getMenu();
            MenuItem item = menu.add(R.id.nav_head,Menu.NONE,Menu.NONE,tableContract.getTableName());
            map.put(item.getItemId(), tableContract);
        }
    }
}