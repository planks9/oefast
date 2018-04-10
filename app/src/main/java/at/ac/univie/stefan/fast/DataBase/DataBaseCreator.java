package at.ac.univie.stefan.fast.DataBase;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.util.Log;

/**
 * Created by Stefan on 10.04.2018.
 */

public class DataBaseCreator {

    public static final String TAG=DataBaseCreator.class.getSimpleName();

    private static DataBaseCreator instance;
    private static AppDatabase appDatabase;

    private DataBaseCreator (Context context) {
        appDatabase = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"SensorDataBase").build();
    }

    public static void createnewDataBase (Context context) {
        instance = new DataBaseCreator(context);
    }

    public static AppDatabase getDataBase () {
        if (DataBaseCreator.instance==null) {
            Log.e(TAG,"Call createDataBase before calling getInstance");
        }
        return appDatabase;
    }

    public static DataBaseCreator getDataBaseCreatorInstance () {
        return instance;
    }



}
