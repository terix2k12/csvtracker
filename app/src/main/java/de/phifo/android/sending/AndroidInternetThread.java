package de.phifo.android.sending;

import android.os.AsyncTask;

import java.net.UnknownHostException;

import de.phifo.sending.HttpSender;

public class AndroidInternetThread extends AsyncTask<String, Void, String> {

    HttpSender sender = new HttpSender();

	@Override
	protected String doInBackground(String... data) {
        try {
            return sender.Post(data[0]);
        }
        catch(UnknownHostException e){
            return "Kein Internet, oder Server antwortet nicht.";
        }
        catch(Exception e){
            return "Ein Fehler ist aufgetreten: " + e.toString();
        }
    }

	@Override
	protected void onPostExecute(String feed) {
	}
}