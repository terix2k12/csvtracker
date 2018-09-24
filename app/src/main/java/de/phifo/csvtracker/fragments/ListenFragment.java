package de.phifo.csvtracker.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.phifo.android.persistance.AndroidDAO;
import de.phifo.csvtracker.CsvTrackerMain;
import de.phifo.csvtracker.persistance.CsvTrackerDatabase;
import de.phifo.persistance.TableContract;

public class ListenFragment extends ListFragment {

    public CsvTrackerDatabase database;
    public TableContract tableContract;

    public ArrayAdapter<Object> adapter;
    public CsvTrackerMain main;

    ArrayList<Object> values;

    private AndroidDAO dao;

    private Object selectedObject;

    public ListenFragment() {
        values = new ArrayList<>();
    }

    public void add(Object s) {
        if (adapter != null) {
            adapter.add(s.toString());
        } else {
            values.add(s);
        }
    }

    public void update() {
         dao = new AndroidDAO(database);

        List<List<Object>> list = dao.ReadAllObj(tableContract);

        for (List<Object> row : list) {
            add(row.toString());
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter = new ArrayAdapter<Object>(getActivity(),
                android.R.layout.simple_list_item_1, values);

        setListAdapter(adapter);

        update();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        LinearLayout view = new LinearLayout(getActivity());

        view.setOrientation(LinearLayout.VERTICAL);

        Button txt = new Button(getActivity());
        txt.setText("Sync");
        txt.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (selectedObject != null) {
                            String s = selectedObject.toString();

                            String[] ry = s.split(";");
                            List<Object> l = new ArrayList);
                            for(int i=0; i<ry.length;i++){
                                l.add(ry[i]);
                            }

                            main.AddToServer(tableContract, l);

                        }else{

                            Toast.makeText(getActivity(),
                                    "nothing selected to sync", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        Button txt2 = new Button(getActivity());
        txt2.setText("Delete");
        txt2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (selectedObject != null) {
                            values.remove(selectedObject);
                            dao.remove(selectedObject);
                            adapter.remove(selectedObject);
                        }else{

                        Toast.makeText(getActivity(),
                                "nothing selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        Button txt3 = new Button(getActivity());
        txt3.setText("Edit");

        view.addView(txt);
        view.addView(txt2);
        view.addView(txt3);

        view.addView(super.onCreateView(inflater, container, savedInstanceState));

        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        selectedObject = adapter.getItem(position);

    }
}