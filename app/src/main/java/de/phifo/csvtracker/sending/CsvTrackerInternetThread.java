package de.phifo.csvtracker.sending;

import java.util.List;

import de.phifo.android.sending.AndroidInternetThread;
import de.phifo.csvtracker.model.AusgabenBE;
import de.phifo.persistance.TableContract;

public class CsvTrackerInternetThread extends AndroidInternetThread {

    // TODO Password aus config laden

    // TODO empfangen

    public String senden(TableContract con, List<Object> s)
    {
        String data = "";
        data += "usr=Vollidiot";
        data += "&pwd=susi";
        data += "&action=put";

        // TODO generic
        data += "&mm="+s.get(0);
        data += "&dd="+s.get(0);
        data += "&yy="+s.get(0);
        data += "&kto="+s.get(1);
        data += "&sko="+s.get(2);
        data += "&cur="+s.get(3);
        data += "&val="+s.get(4);
        data += "&cat="+s.get(5);
        data += "&sub="+s.get(6);
        data += "&cmt="+s.get(7);

        execute(data);

        return "empty";
    }
}
