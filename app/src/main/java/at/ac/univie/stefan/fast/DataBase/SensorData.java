package at.ac.univie.stefan.fast.DataBase;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Stefan on 10.04.2018.
 */

@Entity
public class SensorData {

    @PrimaryKey(autoGenerate = true)
    private int primarykey;

    private long timestamp;
    private int heartrate;
    private int rrinterval;
    private String person;

    public long getTimestamp() {
        return timestamp;
    }

    public SensorData () {

    }

    @Ignore
    public SensorData (long timestamp, int heartrate, int rrinterval, String person) {
        this.timestamp=timestamp;
        this.heartrate=heartrate;
        this.rrinterval=rrinterval;
        this.person=person;
    }

    public void setTimestamp(long timestamp) {
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
}
