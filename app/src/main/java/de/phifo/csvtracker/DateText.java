package de.phifo.csvtracker;

import android.content.Context;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;

public class DateText extends TextView implements DateConsumer {

    public int year;
    public int month;
    public int day;

    public DateText(Context context){
        super(context);

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        setDate(year, month+1, day);
    }

    @Override
    public void setDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;

        // TODO use use defined pattern
        NumberFormat format2 = new DecimalFormat("00");
        String text = format2.format(day) + ".";
        text += format2.format(month) + ".";
        text += year;
        setText(text);
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public int getMonth() {
        return month;
    }

    @Override
    public int getDay() {
        return day;
    }
}