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
    List<SensorData> findybyTime (double time);

    @Query("SELECT * FROM sensordata WHERE person LIKE :name")
    List<SensorData> findbyPersonName (String name);

    @Query("SELECT * FROM sensordata WHERE person LIKE :personname AND stationname = :stationname AND isconnected=1")
    List<SensorData> findbyPersonNameandStationName (String personname, String stationname);

    @Query("SELECT MAX(heartrate) FROM sensordata WHERE person LIKE :personname AND stationname = :stationname AND isconnected=1")
    int getmaxhrfromNameandStationName(String personname, String stationname);

    @Query("SELECT AVG(heartrate) FROM sensordata WHERE person LIKE :personname AND stationname = :stationname AND isconnected=1")
    int getavghr(String personname, String stationname);



    @Insert
    void insertAll(List<SensorData> sensorDataList);

    @Insert
    void insertSensorData (SensorData sensorData);

    @Delete
    void delete(SensorData sensorData);
}
