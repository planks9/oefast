package at.ac.univie.stefan.fast.DataBase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Stefan on 10.04.2018.
 */

@Dao
public interface SensorDataDao {

    @Query("SELECT * FROM SensorData")
    List<SensorData> getAll();

    @Query("SELECT * FROM sensordata WHERE timestamp = :time")
    SensorData findybyTime (long time);

    @Query("SELECT * FROM sensordata WHERE person LIKE :name")
    SensorData findbyPersonName (String name);

    @Insert
    void insertAll(List<SensorData> sensorDataList);

    @Insert
    void insertSensorData (SensorData sensorData);

    @Delete
    void delete(SensorData sensorData);
}
