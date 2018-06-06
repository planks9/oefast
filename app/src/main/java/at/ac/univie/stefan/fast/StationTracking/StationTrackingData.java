package at.ac.univie.stefan.fast.StationTracking;

/**
 * Created by Stefan on 14.04.2018.
 */

public class StationTrackingData {

    //keyvalues
    public static final String STATIONONE = "StationOne";
    public static final String STATIONTWO = "StationTwo";
    public static final String STATIONTHREE = "StationThree";
    public static final String STATIONFOUR = "StationFour";
    public static final String STATIONFIVE = "StationFive";
    public static final String PRAETEST = "PRAETEST";
    public static final String POSTTEST = "POSTTEST";


    private static String personname;


    private static long primarykeypersondata;
    private static String actualstation;
    private static boolean isrecordingStation;
    private static boolean isrecordingwholedurchlauf;
    private static int maxhr;

    public static int getMaxhr() {
        return maxhr;
    }

    public static void setMaxhr(int maxhr) {
        StationTrackingData.maxhr = maxhr;
    }

    public static String getPersonname() {
        return personname;
    }

    public static void setPersonname(String personname) {
        StationTrackingData.personname = personname;
    }

    public static String getActualStation() {
        return actualstation;
    }

    public static void setActualStation(String actualstation) {
        StationTrackingData.actualstation = actualstation;
    }

    public static boolean isIsrecordingStation() {
        return isrecordingStation;
    }

    public static void setIsrecordingStation(boolean isrecordingStation) {
        StationTrackingData.isrecordingStation = isrecordingStation;
    }

    public static long getPrimarykeypersondata() {
        return primarykeypersondata;
    }

    public static void setPrimarykeypersondata(long primarykeypersondata) {
        StationTrackingData.primarykeypersondata = primarykeypersondata;
    }


    public static boolean isIsrecordingwholedurchlauf() {
        return isrecordingwholedurchlauf;
    }

    public static void setIsrecordingwholedurchlauf(boolean isrecordingwholedurchlauf) {
        StationTrackingData.isrecordingwholedurchlauf = isrecordingwholedurchlauf;
    }

}
