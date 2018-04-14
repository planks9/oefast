package at.ac.univie.stefan.fast.StationTracking;

/**
 * Created by Stefan on 14.04.2018.
 */

public class StationTrackingData {

    //keyvalues
    public static final String STATIONONE="StationOne";
    public static final String STATIONTWO="StationTwo";
    public static final String STATIONTHREE="StationThree";
    public static final String STATIONFOUR="StationFour";
    public static final String STATIONFIVE="StationFive";




    private static String personname;
    private static String actualstation;
    private static boolean isrecording;


    private StationTrackingData () {

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

    public static boolean isIsrecording() {
        return isrecording;
    }

    public static void setIsrecording(boolean isrecording) {
        StationTrackingData.isrecording = isrecording;
    }

}
