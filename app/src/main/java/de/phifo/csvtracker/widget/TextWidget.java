package de.phifo.csvtracker.widget;

import android.support.v4.app.FragmentActivity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import de.phifo.persistance.ColumnContract;

public class TextWidget implements IWidget {

    FragmentActivity activity;
    public EditText be;

    LinearLayout.LayoutParams p44 = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);
    LinearLayout.LayoutParams p66 = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);

    public TextWidget(FragmentActivity activity, ColumnContract col, LinearLayout ll){
        this.activity =  activity;

        p44.weight = 0.75f;
        p66.weight = 0.25f;

        LinearLayout ll8 = new LinearLayout(activity );
        ll8.setOrientation(LinearLayout.HORIZONTAL);
        ll8.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        be = addEditor(  android.R.style.TextAppearance_Material_Small);
        TextView da4 = addText(col.getColumnName()+":",
                android.R.style.TextAppearance_Material_Small);

        be.setLayoutParams(p66);
        da4.setLayoutParams(p44);
        ll8.addView(da4);
        ll8.addView(be);
        ll.addView(ll8);
    }

    public TextView addText(String txt, int appearance) {
        TextView tv = new TextView(activity);
        tv.setText(txt);
        tv.setTextAppearance(activity, appearance);
        return tv;
    }

    public EditText addEditor(int stl) {
        android.widget.EditText editText = new EditText(activity);
        // editText.setHint();
        //ll.addView(editText, createLayout());

        return editText;
    }

    @Override
    public String getWerte() {
        return be.getText().toString();
    }
}