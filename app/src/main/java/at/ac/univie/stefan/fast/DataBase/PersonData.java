package at.ac.univie.stefan.fast.DataBase;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class PersonData {
    @PrimaryKey(autoGenerate = true)
    private long primarykey;

    private String personname;

    private int personalmaxhr;

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

    private double stationonesdnn;
    private double stationonermssd;
    private double stationtwosdnn;
    private double stationtwormssd;
    private double stationthreesdnn;
    private double stationthreermssd;
    private double stationfoursdnn;
    private double stationfourrmssd;
    private double stationfivesdnn;
    private double stationfivermssd;

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

    public long getPrimarykey() {
        return primarykey;
    }

    public void setPrimarykey(long primarykey) {
        this.primarykey = primarykey;
    }

    public double getStationonesdnn() {
        return stationonesdnn;
    }

    public void setStationonesdnn(double stationonesdnn) {
        this.stationonesdnn = stationonesdnn;
    }

    public double getStationonermssd() {
        return stationonermssd;
    }

    public void setStationonermssd(double stationonermssd) {
        this.stationonermssd = stationonermssd;
    }

    public double getStationtwosdnn() {
        return stationtwosdnn;
    }

    public void setStationtwosdnn(double stationtwosdnn) {
        this.stationtwosdnn = stationtwosdnn;
    }

    public double getStationtwormssd() {
        return stationtwormssd;
    }

    public void setStationtwormssd(double stationtwormssd) {
        this.stationtwormssd = stationtwormssd;
    }

    public double getStationthreesdnn() {
        return stationthreesdnn;
    }

    public void setStationthreesdnn(double stationthreesdnn) {
        this.stationthreesdnn = stationthreesdnn;
    }

    public double getStationthreermssd() {
        return stationthreermssd;
    }

    public void setStationthreermssd(double stationthreermssd) {
        this.stationthreermssd = stationthreermssd;
    }

    public double getStationfoursdnn() {
        return stationfoursdnn;
    }

    public void setStationfoursdnn(double stationfoursdnn) {
        this.stationfoursdnn = stationfoursdnn;
    }

    public double getStationfourrmssd() {
        return stationfourrmssd;
    }

    public void setStationfourrmssd(double stationfourrmssd) {
        this.stationfourrmssd = stationfourrmssd;
    }

    public double getStationfivesdnn() {
        return stationfivesdnn;
    }

    public void setStationfivesdnn(double stationfivesdnn) {
        this.stationfivesdnn = stationfivesdnn;
    }

    public double getStationfivermssd() {
        return stationfivermssd;
    }

    public void setStationfivermssd(double stationfivermssd) {
        this.stationfivermssd = stationfivermssd;
    }


    public int getPersonalmaxhr() {
        return personalmaxhr;
    }

    public void setPersonalmaxhr(int personalmaxhr) {
        this.personalmaxhr = personalmaxhr;
    }

}
