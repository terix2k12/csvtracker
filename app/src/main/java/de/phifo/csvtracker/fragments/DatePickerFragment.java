package de.phifo.csvtracker.fragments;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import de.phifo.csvtracker.DateConsumer;

public class DatePickerFragment extends DialogFragment implements
		DatePickerDialog.OnDateSetListener {

	DateConsumer parent;

	public DatePickerFragment(){
		// used to reinstate fragment!!
	}

	public DatePickerFragment(DateConsumer parent) {
		this.parent = parent;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current date as the default date in the picker
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);

		//parent.setDate(year, month, day);

		// Create a new instance of DatePickerDialog and return it
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}

	@Override
	public void onDateSet(DatePicker view, int year, int month, int day) {
		parent.setDate(year, month+1, day);
	}

}