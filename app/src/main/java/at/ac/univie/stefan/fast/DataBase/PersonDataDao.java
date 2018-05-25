package at.ac.univie.stefan.fast.DataBase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Stefan on 25.05.2018.
 */

@Dao
public interface PersonDataDao {

    @Query("SELECT * FROM PersonData")
    List<PersonData> getAll();

    @Query("SELECT * FROM PersonData WHERE personname LIKE :personname")
    List<PersonData> findPersonbyName (String personname);

    @Query("SELECT * FROM PERSONDATA WHERE primarykey = :primarykey")
    PersonData getPersonbyID (long primarykey);

    @Update
    public void updatePerson (PersonData personData);

    @Insert
    void insertAll(List<PersonData> personDataList);

    @Insert
    long insertPersonData (PersonData personData);


}
