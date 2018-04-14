package at.ac.univie.stefan.fast;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import at.ac.univie.stefan.fast.DataBase.AppDatabase;
import at.ac.univie.stefan.fast.DataBase.DataBaseCreator;
import at.ac.univie.stefan.fast.DataBase.SensorData;

/**
 * Created by Stefan on 14.04.2018.
 */

public class PersonManagementActivity extends AppCompatActivity {

    private AppDatabase appDatabase;
    private ListView listView;
    private ArrayList<String> nameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personmanagement);

        listView = (ListView) findViewById(R.id.listView_persons);


        DataBaseCreator.createnewDataBase(getApplicationContext());
        appDatabase = DataBaseCreator.getDataBase();

        new AsyncTask<Void, Void, List<SensorData>>() {

            @Override
            protected List<SensorData> doInBackground(Void... voids) {
                return appDatabase.sensorDataDao().getAll();
            }

            @Override
            protected void onPostExecute(List<SensorData> sensorDataList) {
                nameList = new ArrayList<String>();

                for (SensorData sensorData : sensorDataList) {
                    String name = sensorData.getPerson();
                    if (!nameList.contains(name)) {
                        nameList.add(name);
                    }
                }
                ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, nameList);
                listView.setAdapter(itemsAdapter);
            }
        }.execute();



    }




}
