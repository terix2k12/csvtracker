package de.phifo.csvtracker.widget;

import android.support.v4.app.FragmentActivity;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import de.phifo.csvtracker.R;
import de.phifo.persistance.ColumnContract;

public class SelectionWidget implements IWidget {

    private final FragmentActivity activity;
    Spinner unterkategorieSpinner;

    LinearLayout.LayoutParams p44 = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);
    LinearLayout.LayoutParams p66 = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);

    public SelectionWidget(FragmentActivity activity,
                           ColumnContract col, LinearLayout ll){
        this.activity = activity;
        p44.weight = 0.75f;
        p66.weight = 0.25f;

        LinearLayout ll4 = new LinearLayout(activity);
        ll4.setOrientation(LinearLayout.HORIZONTAL);
        ll4.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        TextView da2 = addText(col.getColumnName()+":", android.R.style.TextAppearance_Material_Small);

        unterkategorieSpinner = addSpinner(col.getProperties());

        unterkategorieSpinner.setLayoutParams(p66);
        da2.setLayoutParams(p44);
        ll4.addView(da2);
        ll4.addView(unterkategorieSpinner);
        ll.addView(ll4);
    }

    public TextView addText(String txt, int appearance) {
        TextView tv = new TextView(activity);
        tv.setText(txt);
        tv.setTextAppearance(activity, appearance);
        return tv;
    }

    public Spinner addSpinner(int array) {
        Spinner spinner = new Spinner(activity);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                activity, array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        //ll.addView(spinner, createLayout());

        return spinner;
    }

    @Override
    public String getWerte() {
        return unterkategorieSpinner.getSelectedItem().toString();
    }
}
