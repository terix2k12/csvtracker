package de.phifo.csvtracker.model;

import de.phifo.persistance.ColumnContract;
import de.phifo.persistance.ColumnType;
import de.phifo.persistance.TableContract;

public class AusgabenBE {

    public TableContract tableContract;

    private String sub;
    private String dd;
    private String yyyy;
    private String cmt;
    private String cat;
    private String cur;
    private String konto;
    private String skonto;
    private String val;
    private String mm;

    public AusgabenBE(){

    }

    public AusgabenBE(String dd,String mm, String yyyy ,
                      String konto, String skonto, String cur,
                      String value, String cats, String scats,  String cmt){
        this. dd = dd;
        this.mm = mm;
        this.yyyy =  yyyy ;
        this.konto= konto;
        this.skonto = skonto;
        this.cur = cur;
                this.val =  value.replace(",",".");
        this.cat =  cats;
        this. sub = scats;
        this.cmt= cmt;
    }

    public String getmm() {
        return mm;
    }

    public String getdd() {
        return dd;
    }

    public String getyyyy() {
        return yyyy;
    }

    public String getkonto() {
        return konto;
    }

    public String getskonto() {
        return skonto;
    }

    public String getcur() {
        return cur;
    }

    public String getvalue() {
        return val;
    }

    public String getcat() {
        return cat;
    }

    public String getsub() {
        return sub;
    }

    public String getcmt() {
        return cmt;
    }
}
