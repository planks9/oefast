package at.ac.univie.stefan.fast;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import at.ac.univie.stefan.fast.DataBase.AppDatabase;
import at.ac.univie.stefan.fast.DataBase.DataBaseCreator;
import at.ac.univie.stefan.fast.DataBase.SensorData;

/**
 * Created by Stefan on 14.04.2018.
 */

public class PersonManagementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DataBaseCreator.createnewDataBase(getApplicationContext());
        AppDatabase appDatabase = DataBaseCreator.getDataBase();
        List<SensorData> sensorDataList = appDatabase.sensorDataDao().getAll();
        List<String> nameList = new ArrayList<String>();

        for (SensorData sensorData : sensorDataList) {
            String name = sensorData.getPerson();
            if (!nameList.contains(name)) {
                nameList.add(name);
            }
        }

        
    }
}
