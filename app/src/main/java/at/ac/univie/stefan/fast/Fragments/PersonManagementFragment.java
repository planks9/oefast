package at.ac.univie.stefan.fast.Fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import at.ac.univie.stefan.fast.Activities.SearchForDevicesActivity;
import at.ac.univie.stefan.fast.DataBase.AppDatabasePersonData;
import at.ac.univie.stefan.fast.DataBase.DataBaseCreator;
import at.ac.univie.stefan.fast.DataBase.PersonData;
import at.ac.univie.stefan.fast.ArrayAdapters.PersonDataArrayAdapter;
import at.ac.univie.stefan.fast.R;

/**
 * Created by Stefan on 19.06.2018.
 */

public class PersonManagementFragment extends Fragment {

    private AppDatabasePersonData appDatabasePersonData;
    private ListView listView;
    private FloatingActionButton buttonnewCycle;
    private ArrayList<String> nameList;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.personmanagement,container,false);

        listView = (ListView) view.findViewById(R.id.listView_persons);
        buttonnewCycle = (FloatingActionButton) view.findViewById(R.id.newdurchlauf);


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

                    PersonDataArrayAdapter arrayAdapter = new PersonDataArrayAdapter(view.getContext(), personDataList);
                    listView.setAdapter(arrayAdapter);
                }


            }

        }.execute();

        buttonnewCycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),SearchForDevicesActivity.class);
                startActivity(intent);

            }
        });


    return view;
    }
}
