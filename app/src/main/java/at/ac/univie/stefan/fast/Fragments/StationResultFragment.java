package at.ac.univie.stefan.fast.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import at.ac.univie.stefan.fast.DataBase.AppDatabasePersonData;
import at.ac.univie.stefan.fast.DataBase.DataBaseCreator;
import at.ac.univie.stefan.fast.DataBase.PersonData;
import at.ac.univie.stefan.fast.R;

/**
 * Created by Stefan on 25.05.2018.
 */

public class StationResultFragment extends Fragment {


    private long primarykey;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stationresult, container , false);

        new AsyncTask<Void, Void, PersonData>() {
            @Override
            protected PersonData doInBackground(Void... voids) {
                AppDatabasePersonData appDatabasePersonData = DataBaseCreator.getAppDatabasePersonData();
                return appDatabasePersonData.personDataDao().getPersonbyID(primarykey);

            }

            @Override
            protected void onPostExecute(PersonData personData) {

            }
        }.execute();

        return view;
    }

    public long getPrimarykey() {
        return primarykey;
    }

    public void setPrimarykey(long primarykey) {
        this.primarykey = primarykey;
    }

}
