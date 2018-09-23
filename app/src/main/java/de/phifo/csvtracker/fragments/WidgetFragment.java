package de.phifo.csvtracker.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.phifo.csvtracker.CsvTrackerMain;
import de.phifo.csvtracker.widget.DateWidget;
import de.phifo.csvtracker.widget.IWidget;

import de.phifo.csvtracker.widget.SelectionWidget;
import de.phifo.csvtracker.widget.TextWidget;
import de.phifo.persistance.ColumnContract;
import de.phifo.persistance.TableContract;

public class WidgetFragment extends Fragment {

    public TableContract contract;

    public Map<String, IWidget> inputs = new HashMap<>();

    public CsvTrackerMain main;

    public WidgetFragment(){
    }

    public void clear(){
//        be2.getText().clear();
 //       cmt.getText().clear();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        LinearLayout ll = new LinearLayout(getActivity());
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));

        for(ColumnContract col : contract.getColumns())
        {
            switch(col.getType()){
                case DATE:
                    createDate(col, ll);

                    break;
                case SELECTION:
                    SelectionWidget sw = new SelectionWidget(getActivity(), col, ll);
                    inputs.put(col.getColumnName(), sw);

                    break;
                case TEXT:
                    createText(col, ll);
                    break;
                case NUMBER:
                    createText(col, ll);
                    break;
                default:

            }
        }

        Button txt = new Button(getActivity());
        txt.setText("Add");

        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            List<Object> werte = new ArrayList<>();
            for(ColumnContract col : contract.getColumns()) {
                IWidget wid =   inputs.get(col.getColumnName());
                if(wid!=null){
                    werte.add( wid.getWerte() );
                }
            }

            clear();
            main.AddItem(contract, werte );
        }});

        ll.addView(txt);

       return ll;
    }

    private void createDate(ColumnContract col, LinearLayout ll){
        DateWidget dt = new DateWidget(getActivity(), col, ll);
        inputs.put(col.getColumnName(), dt);
    }

    private void createText(ColumnContract col, LinearLayout ll){
        TextWidget be = new TextWidget(getActivity(), col, ll);
        inputs.put(col.getColumnName(), be);
    }


}