package at.ac.univie.stefan.fast.DataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by Stefan on 10.04.2018.
 */

@Database(entities = {SensorData.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract SensorDataDao sensorDataDao();
}
