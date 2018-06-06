package at.ac.univie.stefan.fast.DataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


@Database(entities = {RecordingData.class}, version = 1)
public abstract class AppDataBaseRecordingData extends RoomDatabase {
    public abstract RecordingDataDao recordingDataDao();
}

