package at.ac.univie.stefan.fast.DataBase;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.util.Log;

/**
 * Created by Stefan on 10.04.2018.
 */

public class DataBaseCreator {

    public static final String TAG=DataBaseCreator.class.getSimpleName();

    private static AppDatabasePersonData appDatabasePersonData;
    private static AppDatabase appDatabase;
    private static  AppDataBaseRecordingData appDataBaseRecordingData;


    public static void createnewDataBase (Context context) {
        if (appDatabase == null)
        appDatabase = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"SensorDataBase").build();
    }

    public static AppDatabase getDataBase () {
        if (appDatabase==null) {
            Log.e(TAG,"Call createDataBase before calling get #sensorDatabase");
        }
        return appDatabase;
    }

    public static void createnewDataBasePersonData (Context context) {
        if (appDatabasePersonData == null) {
            appDatabasePersonData = Room.databaseBuilder(context.getApplicationContext(), AppDatabasePersonData.class, "PersonDataBase").build();
        } else Log.d(TAG, "DataBase already created");
    }

    public static AppDatabasePersonData getAppDatabasePersonData () {
        if (appDatabasePersonData==null) {
            Log.e(TAG, "Call createnewDataBasePersonData before calling get #persondatabase");
        }

        return appDatabasePersonData;
    }

    public static void createnewDataBaseRecordingData (Context context) {
        if (appDataBaseRecordingData == null) {
            appDataBaseRecordingData = Room.databaseBuilder(context.getApplicationContext(),AppDataBaseRecordingData.class,"RecordingDataBase").build();
        } else Log.d(TAG,"DataBaseRecordingData already created");
    }

    public static AppDataBaseRecordingData getAppDataBaseRecordingData () {
        if (appDataBaseRecordingData == null) {
            Log.e(TAG,"Call createnewDataBaseRecordingData before calling get #recordingdatabase");
        }

        return appDataBaseRecordingData;
    }




}
