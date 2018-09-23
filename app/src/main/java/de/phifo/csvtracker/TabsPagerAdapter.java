package de.phifo.csvtracker;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import de.phifo.csvtracker.fragments.WidgetFragment;
import de.phifo.csvtracker.fragments.ListenFragment;
import de.phifo.csvtracker.fragments.PageFragment;
import de.phifo.csvtracker.persistance.CsvTrackerDatabase;
import de.phifo.persistance.TableContract;

public class TabsPagerAdapter extends FragmentStatePagerAdapter {

    // https://github.com/codepath/android_guides/wiki/ViewPager-with-FragmentPagerAdapter#dynamic-viewpager-fragments

    private ListenFragment list;
    private WidgetFragment eingabeFrag;

    private CsvTrackerDatabase database;
    private TableContract tableContract;

    private CsvTrackerMain main;

    public TabsPagerAdapter(FragmentManager fm, CsvTrackerDatabase dao,
                            TableContract tableContract,
                            CsvTrackerMain main) {
        super(fm);
        this.main = main;
        this.tableContract = tableContract;
        this.database = dao;

        eingabeFrag = new WidgetFragment();
        eingabeFrag.contract = tableContract;
        eingabeFrag.main = main;

        list=    new ListenFragment();
        list.database = database;
        list.tableContract = tableContract;
        list.main = main;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object obj = super.instantiateItem(container, position);
         return obj;
    }

    @Override
    public int getItemPosition(Object object) {
       // if(update){
       //     return POSITION_NONE;
       // }else
        return super.getItemPosition(object);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position)
        {
            case 0:
                return eingabeFrag;
            case 1:
                return list;
            default:
                return PageFragment.newInstance(position + 1);
        }

    }

    public ListenFragment getListe()
    {
        return list;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position) {
            case 0:
                return "Eingeben";
            case 1:
                return "Liste";
            case 2:
                return "Einstellungen";
            default:
                return "TAB " + (position + 1);
        }
    }
}
