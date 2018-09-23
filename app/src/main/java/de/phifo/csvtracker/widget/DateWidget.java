package de.phifo.csvtracker.widget;

import android.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import de.phifo.csvtracker.DateText;
import de.phifo.csvtracker.fragments.DatePickerFragment;
import de.phifo.persistance.ColumnContract;

public class DateWidget implements IWidget {

    DateText txt;
    FragmentActivity activity;

    LinearLayout.LayoutParams p44 = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);
    LinearLayout.LayoutParams p66 = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);

    public DateWidget(final FragmentActivity activity,
                      ColumnContract col, LinearLayout ll){
        this. activity=activity;

        p44.weight = 0.75f;
        p66.weight = 0.25f;

        LinearLayout ll1 = new LinearLayout(activity );
        ll1.setOrientation(LinearLayout.HORIZONTAL);
        ll1.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        TextView d1 = addText(col.getColumnName()+":",
                android.R.style.TextAppearance_Material_Small);
        txt = new DateText(activity );
        txt.setTextAppearance(activity , android.R.style.TextAppearance_Material_Medium);
        txt.setLayoutParams(p66);
        d1.setLayoutParams(p44);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePickerFragment(txt);
                newFragment.show(activity.getFragmentManager(), "datePicker");
            }
        });
        ll1.addView(d1);
        ll1.addView(txt);
        ll.addView(ll1);
    }

    public TextView addText(String txt, int appearance) {
        TextView tv = new TextView(activity);
        tv.setText(txt);
        tv.setTextAppearance(activity, appearance);
        return tv;
    }

    @Override
    public String getWerte() {
        NumberFormat f2 = new DecimalFormat("00");
        NumberFormat f4 = new DecimalFormat("0000");

        String dd = f2.format( txt.getDay() );
        String mm = f2.format( txt.getMonth() );
        String yyyy = f4.format( txt.getYear() );

        return txt.getText().toString();
    }
}
