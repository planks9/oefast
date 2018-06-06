package at.ac.univie.stefan.fast.DataBase;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class RecordingData {

    @PrimaryKey(autoGenerate = true)
    private int primarykey;

    private double timestamp;
    private int heartrate;
    private int rrinterval;
    private String person;
    private boolean isconnected;

    public double getTimestamp() {
        return timestamp;
    }

    public RecordingData() {

    }

    @Ignore
    public RecordingData(double timestamp, String person, int heartrate, int rrinterval, boolean isconnected) {
        this.timestamp = timestamp;
        this.heartrate = heartrate;
        this.rrinterval = rrinterval;
        this.person = person;
        this.isconnected = isconnected;
    }

    public void setTimestamp(double timestamp) {
        this.timestamp = timestamp;
    }

    public int getHeartrate() {
        return heartrate;
    }

    public void setHeartrate(int heartrate) {
        this.heartrate = heartrate;
    }

    public int getRrinterval() {
        return rrinterval;
    }

    public void setRrinterval(int rrinterval) {
        this.rrinterval = rrinterval;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public int getPrimarykey() {
        return primarykey;
    }

    public void setPrimarykey(int primarykey) {
        this.primarykey = primarykey;
    }


    public boolean isIsconnected() {
        return isconnected;
    }

    public void setIsconnected(boolean isconnected) {
        this.isconnected = isconnected;
    }
}
