package at.ac.univie.stefan.fast.DataBase;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Stefan on 25.05.2018.
 */

@Entity
public class PersonData {
    @PrimaryKey(autoGenerate = true)
    private int primarykey;

    private String personname;

    private String stationonetime;
    private String stationtwotime;
    private String stationthreetime;
    private String stationfourtime;
    private String stationfivetime;

    private int stationonemaxhr;
    private int stationoneavhr;
    private int stationtwomaxhr;
    private int stationtwoavhr;
    private int stationthreemaxhr;
    private int stationthreeavhr;
    private int stationfourmaxhr;
    private int stationfouravhr;
    private int stationfivemaxhr;
    private int stationfiveavhr;

    @Ignore
    public PersonData (String personname) {
        this.personname=personname;
    }

    public PersonData () {

    }

    public String getPersonname() {
        return personname;
    }

    public void setPersonname(String personname) {
        this.personname = personname;
    }

    public String getStationonetime() {
        return stationonetime;
    }

    public void setStationonetime(String stationonetime) {
        this.stationonetime = stationonetime;
    }

    public String getStationtwotime() {
        return stationtwotime;
    }

    public void setStationtwotime(String stationtwotime) {
        this.stationtwotime = stationtwotime;
    }

    public String getStationthreetime() {
        return stationthreetime;
    }

    public void setStationthreetime(String stationthreetime) {
        this.stationthreetime = stationthreetime;
    }

    public String getStationfourtime() {
        return stationfourtime;
    }

    public void setStationfourtime(String stationfourtime) {
        this.stationfourtime = stationfourtime;
    }

    public String getStationfivetime() {
        return stationfivetime;
    }

    public void setStationfivetime(String stationfivetime) {
        this.stationfivetime = stationfivetime;
    }

    public int getStationonemaxhr() {
        return stationonemaxhr;
    }

    public void setStationonemaxhr(int stationonemaxhr) {
        this.stationonemaxhr = stationonemaxhr;
    }

    public int getStationoneavhr() {
        return stationoneavhr;
    }

    public void setStationoneavhr(int stationoneavhr) {
        this.stationoneavhr = stationoneavhr;
    }

    public int getStationtwomaxhr() {
        return stationtwomaxhr;
    }

    public void setStationtwomaxhr(int stationtwomaxhr) {
        this.stationtwomaxhr = stationtwomaxhr;
    }

    public int getStationtwoavhr() {
        return stationtwoavhr;
    }

    public void setStationtwoavhr(int stationtwoavhr) {
        this.stationtwoavhr = stationtwoavhr;
    }

    public int getStationthreemaxhr() {
        return stationthreemaxhr;
    }

    public void setStationthreemaxhr(int stationthreemaxhr) {
        this.stationthreemaxhr = stationthreemaxhr;
    }

    public int getStationthreeavhr() {
        return stationthreeavhr;
    }

    public void setStationthreeavhr(int stationthreeavhr) {
        this.stationthreeavhr = stationthreeavhr;
    }

    public int getStationfourmaxhr() {
        return stationfourmaxhr;
    }

    public void setStationfourmaxhr(int stationfourmaxhr) {
        this.stationfourmaxhr = stationfourmaxhr;
    }

    public int getStationfouravhr() {
        return stationfouravhr;
    }

    public void setStationfouravhr(int stationfouravhr) {
        this.stationfouravhr = stationfouravhr;
    }

    public int getStationfivemaxhr() {
        return stationfivemaxhr;
    }

    public void setStationfivemaxhr(int stationfivemaxhr) {
        this.stationfivemaxhr = stationfivemaxhr;
    }

    public int getStationfiveavhr() {
        return stationfiveavhr;
    }

    public void setStationfiveavhr(int stationfiveavhr) {
        this.stationfiveavhr = stationfiveavhr;
    }

    public int getPrimarykey() {
        return primarykey;
    }

    public void setPrimarykey(int primarykey) {
        this.primarykey = primarykey;
    }
}
