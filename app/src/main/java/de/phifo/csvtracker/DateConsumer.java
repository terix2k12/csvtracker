package de.phifo.csvtracker;


public interface DateConsumer {

	// public void setDate(Date date);
	public void setDate(int year, int month, int day);

	public int getYear();

	public int getMonth();

	public int getDay();
}
