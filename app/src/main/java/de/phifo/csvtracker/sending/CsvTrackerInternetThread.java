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
        String yyyy_mm_dd = s.get(0).toString();
        data += "&yy="+ yyyy_mm_dd.substring(2,4);
        data += "&mm="+ yyyy_mm_dd.substring(5,7);
        data += "&dd="+ yyyy_mm_dd.substring(8,10);
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
