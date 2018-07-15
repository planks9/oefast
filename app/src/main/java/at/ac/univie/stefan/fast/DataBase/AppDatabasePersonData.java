package at.ac.univie.stefan.fast.DataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by Stefan on 25.05.2018.
 */

@Database(entities = {PersonData.class}, version = 1)
public abstract class AppDatabasePersonData extends RoomDatabase {
    public abstract PersonDataDao personDataDao();
}
