package de.phifo.sending;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpSender {
    public String Post(String data) throws Exception
    {
        String urlString = "https://philippfonteyn.de/csh/interface.php";

        OutputStream out = null;

        URL url = new URL(urlString);

        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
        //HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        urlConnection.setRequestMethod("POST");

        out = new BufferedOutputStream(urlConnection.getOutputStream());

        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(out, "UTF-8")
        );

        writer.write(data);
        writer.flush();
        writer.close();
        out.close();
        urlConnection.connect();

        // Process response
        int responseCode=urlConnection.getResponseCode();
        if(responseCode==200){
            String response = "";
            String line;

            BufferedReader br=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            while ((line=br.readLine()) != null) {
                response+=line;
            }

            return response;
        }else{
            return "Ein externer Fehler ist aufgetreten!";
        }
    }
}
