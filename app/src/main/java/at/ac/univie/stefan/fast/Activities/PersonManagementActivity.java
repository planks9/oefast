package at.ac.univie.stefan.fast.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import at.ac.univie.stefan.fast.DataBase.AppDatabasePersonData;
import at.ac.univie.stefan.fast.DataBase.DataBaseCreator;
import at.ac.univie.stefan.fast.DataBase.PersonData;
import at.ac.univie.stefan.fast.PersonDataArrayAdapter;
import at.ac.univie.stefan.fast.R;

/**
 * Created by Stefan on 14.04.2018.
 */

public class PersonManagementActivity extends AppCompatActivity {

    private AppDatabasePersonData appDatabasePersonData;
    private ListView listView;
    private FloatingActionButton buttonnewCycle;
    private ArrayList<String> nameList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personmanagement);

        listView = (ListView) findViewById(R.id.listView_persons);
        buttonnewCycle = (FloatingActionButton) findViewById(R.id.newdurchlauf);



        DataBaseCreator.createnewDataBasePersonData(getApplicationContext());
        DataBaseCreator.createnewDataBase(getApplicationContext());
        appDatabasePersonData = DataBaseCreator.getAppDatabasePersonData();

        new AsyncTask<Void, Void, List<PersonData>>() {

            @Override
            protected List<PersonData> doInBackground(Void... voids) {
                return appDatabasePersonData.personDataDao().getAll();
            }

            @Override
            protected void onPostExecute(List<PersonData> personDataList) {
                nameList = new ArrayList<String>();
                if (!personDataList.isEmpty()) {
                    for (PersonData personData : personDataList) {
                        nameList.add(personData.getPersonname());
                    }

                    PersonDataArrayAdapter arrayAdapter = new PersonDataArrayAdapter(getApplicationContext(), personDataList);
                    listView.setAdapter(arrayAdapter);
                }


            }

        }.execute();

        buttonnewCycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchForDevicesActivity.class);
                startActivity(intent);

            }
        });


    }


}
