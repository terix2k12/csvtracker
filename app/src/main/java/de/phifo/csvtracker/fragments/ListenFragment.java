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

    public CsvTrackerMain main;
    public TableContract tableContract;

    private ArrayAdapter<List<Object>> adapter;

    ArrayList<List<Object>> values;

    private List<Object> selectedObject;

    public ListenFragment() {
        values = new ArrayList<>();
    }

    public void add(List<Object> s) {
        if (adapter != null) {
            adapter.add(s);
        } else {
            values.add(s);
        }
    }

    public void update() {
        List<List<Object>> list = main.ReadAllObj(tableContract);

        for (List<Object> row : list) {
            add(row);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter = new ArrayAdapter<List<Object>>(getActivity(),
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

                            String[] ry = s
                                    .replace("[","")
                                    .replace("]","")
                                    .split(",");
                            List<Object> l = new ArrayList();
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
                            long id = Long.parseLong((String)selectedObject.get(0));
                            main.delete(tableContract, id);
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