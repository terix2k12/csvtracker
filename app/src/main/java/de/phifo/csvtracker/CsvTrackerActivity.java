package de.phifo.csvtracker;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import android.util.AttributeSet;
import android.view.View;
import android.support.design.widget.NavigationView;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import de.phifo.csvtracker.fragments.WidgetFragment;
import de.phifo.csvtracker.persistance.CsvTrackerDatabase;

import de.phifo.persistance.TableContract;

public class CsvTrackerActivity extends AppCompatActivity {

    private Map<String, TabsPagerAdapter> map = new HashMap<>();

    private CsvTrackerDatabase database;

    // http://saulmm.github.io/mastering-coordinator

    DrawerLayout drawerLayout;
    NavigationView nav;

    NavigationControl navi;

    CsvTrackerMain main;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createContentView();

        database = new CsvTrackerDatabase(this);
        main = new CsvTrackerMain(database, this);

        main.InitWithMetaitems();
    }

    public void createContentView() {
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
         ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                 R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navi = new NavigationControl(this);
    }

    public void activate(TableContract table) {

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);

        LinearLayout lin = findViewById(R.id.lin_lay);

        ViewPager pager = (ViewPager) findViewById(R.id.pager);

        TabsPagerAdapter tabsAdaptert = GetTabsAdapter(table);

        pager.setAdapter(tabsAdaptert);

        tabs.setupWithViewPager(pager);

        if(drawerLayout!=null){
            drawerLayout.closeDrawers();
        }
    }

    public TabsPagerAdapter GetTabsAdapter(TableContract c){

        if(!map.containsKey(c.getTableName())){

            map.put(c.getTableName(), CreateTabsAdapter(c));

        }

            return map.get((c.getTableName()));
    }

    public TabsPagerAdapter CreateTabsAdapter(TableContract c){
        TabsPagerAdapter tabsAdaptert;

        tabsAdaptert = new TabsPagerAdapter(getSupportFragmentManager(), database, c, main);

        final WidgetFragment eingabe = (WidgetFragment) tabsAdaptert.getItem(0);
        eingabe.main = main;
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "My Action!", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();


            }
        });*/

        return tabsAdaptert;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = drawerLayout;//(DrawerLayout) findViewById(R.id.drawer_layout);
        if(drawer!=null){

        drawer.closeDrawers();
        }

        /*
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            drawer.closeDrawer(GravityCompat.START);
            // super.onBackPressed();
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void toast(String message) {
        // Toast t = new Toast(this);
        Toast t = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        // t.setText(message);

        t.show();
    }

    public void handleException(Exception e)   {
        toast(e.toString());

        e.printStackTrace();
    }

}