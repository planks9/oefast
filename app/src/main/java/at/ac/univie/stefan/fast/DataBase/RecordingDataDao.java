package at.ac.univie.stefan.fast.DataBase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface RecordingDataDao {

    @Query("SELECT * FROM RecordingData")
    List<RecordingData> getAll();

    @Query("SELECT * FROM RecordingData WHERE timestamp = :time")
    List<RecordingData> findybyTime(double time);

    @Query("SELECT * FROM RecordingData WHERE person LIKE :name")
    List<RecordingData> findbyPersonName(String name);

    @Query("SELECT * FROM RecordingData WHERE person LIKE :personname AND isconnected=1")
    List<RecordingData> findbyPersonNameandStationName(String personname);

    @Query("SELECT MAX(heartrate) FROM RecordingData WHERE person LIKE :personname AND isconnected=1")
    int getmaxhrfromNameandStationName(String personname);

    @Query("SELECT AVG(heartrate) FROM RecordingData WHERE person LIKE :personname AND isconnected=1")
    int getavghr(String personname);


    @Insert
    void insertAll(List<RecordingData> recordingDataList);

    @Insert
    void insertRecordingData(RecordingData recordingData);

    @Delete
    void delete(RecordingData recordingData);
}
