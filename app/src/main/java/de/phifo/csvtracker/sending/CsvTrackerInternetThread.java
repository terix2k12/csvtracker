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
        String yyyy_mm_dd = s.get(2).toString();
        data += "&yy="+ yyyy_mm_dd.substring(2,4);
        data += "&mm="+ yyyy_mm_dd.substring(5,7);
        data += "&dd="+ yyyy_mm_dd.substring(8,10);
        data += "&kto="+s.get(3);
        data += "&sko="+s.get(4);
        data += "&cur="+s.get(5);
        data += "&val="+s.get(6);
        data += "&cat="+s.get(7);
        data += "&sub="+s.get(8);
        data += "&cmt="+s.get(9);

        execute(data);

        return "empty";
    }
}
