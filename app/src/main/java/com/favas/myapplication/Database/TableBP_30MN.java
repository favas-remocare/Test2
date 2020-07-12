package com.favas.myapplication.Database;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "TableBP_30MN")
public class TableBP_30MN {

@PrimaryKey(autoGenerate = true)
private int id;


    private String dat;

    private String tym;

    private String hbp;

    private String lbp;

    public TableBP_30MN(int id, String dat, String tym, String hbp, String lbp) {
        this.id = id;
        this.dat = dat;
        this.tym = tym;
        this.hbp = hbp;
        this.lbp = lbp;
    }

    @Ignore
    public TableBP_30MN()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDat() {
        return dat;
    }

    public void setDat(String dat) {
        this.dat = dat;
    }

    public String getTym() {
        return tym;
    }

    public void setTym(String tym) {
        this.tym = tym;
    }

    public String getHbp() {
        return hbp;
    }

    public void setHbp(String hbp) {
        this.hbp = hbp;
    }

    public String getLbp() {
        return lbp;
    }

    public void setLbp(String lbp) {
        this.lbp = lbp;
    }
}
