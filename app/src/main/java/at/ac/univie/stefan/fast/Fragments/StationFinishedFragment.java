package at.ac.univie.stefan.fast.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import at.ac.univie.stefan.fast.DataBase.AppDatabase;
import at.ac.univie.stefan.fast.DataBase.AppDatabasePersonData;
import at.ac.univie.stefan.fast.DataBase.DataBaseCreator;
import at.ac.univie.stefan.fast.DataBase.PersonData;
import at.ac.univie.stefan.fast.DataBase.SensorData;
import at.ac.univie.stefan.fast.R;
import at.ac.univie.stefan.fast.StationTracking.StationTrackingData;
import at.ac.univie.stefan.fast.Stopwatch.StopWatchService;

import static at.ac.univie.stefan.fast.StationTracking.StationTrackingData.POSTTEST;
import static at.ac.univie.stefan.fast.StationTracking.StationTrackingData.PRAETEST;
import static at.ac.univie.stefan.fast.StationTracking.StationTrackingData.STATIONFIVE;
import static at.ac.univie.stefan.fast.StationTracking.StationTrackingData.STATIONFOUR;
import static at.ac.univie.stefan.fast.StationTracking.StationTrackingData.STATIONONE;
import static at.ac.univie.stefan.fast.StationTracking.StationTrackingData.STATIONTHREE;
import static at.ac.univie.stefan.fast.StationTracking.StationTrackingData.STATIONTWO;

/**
 * Created by Stefan on 09.04.2018.
 */

public class StationFinishedFragment extends Fragment {

    private String personname;
    private String stationname;
    private String timeelapsed;
    private Button buttonStationFinishedback;
    private TextView textViewStationFinishedTime;
    private TextView textViewStationFinishedMinHR;
    private TextView textViewStationFinishedMaxHR;
    private TextView textViewStationFinishedAvgHR;
    private TextView textViewStationFinishedPersonName;
    private TextView textViewStationFinishedStationName;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stationfinished, container, false);
        textViewStationFinishedPersonName = (TextView) view.findViewById(R.id.textViewStationFinishedPersonName);
        textViewStationFinishedStationName = (TextView) view.findViewById(R.id.textViewStationFinishedStationName);
        textViewStationFinishedTime = (TextView) view.findViewById(R.id.textViewStationFinishedTime);
        textViewStationFinishedMinHR = (TextView) view.findViewById(R.id.textViewStationFinishedMinHR);
        textViewStationFinishedMaxHR = (TextView) view.findViewById(R.id.textViewStationFinishedMaxHR);
        textViewStationFinishedAvgHR = (TextView) view.findViewById(R.id.textViewStationFinishedAvgHR);
        buttonStationFinishedback = (Button) view.findViewById(R.id.buttonStationFinishedback);
        personname = StationTrackingData.getPersonname();
        stationname = StationTrackingData.getActualStation();
        textViewStationFinishedStationName.setText(""+stationname);
        textViewStationFinishedPersonName.setText(""+personname);
        timeelapsed = StopWatchService.getStopwatch().getTimeinString();
        textViewStationFinishedTime.setText(timeelapsed);

        switch (stationname) {
            case STATIONONE:
                textViewStationFinishedStationName.setText(R.string.station_one_name);
                break;

            case STATIONTWO:
                textViewStationFinishedStationName.setText(R.string.station_two_name);
                break;

            case STATIONTHREE:
                textViewStationFinishedStationName.setText(R.string.station_three_name);
                break;

            case STATIONFOUR:
                textViewStationFinishedStationName.setText(R.string.station_four_name);
                break;

            case STATIONFIVE:
                textViewStationFinishedStationName.setText(R.string.station_five_name);
                break;

            case PRAETEST:
                textViewStationFinishedStationName.setText(R.string.station_praetest);
                break;

            case POSTTEST:
                textViewStationFinishedStationName.setText(R.string.station_posttest);
                break;
        }
        new AsyncTask<Void, Void, List<SensorData>>() {

            AppDatabasePersonData appDatabasePersonData;
            PersonData personData;
            @Override
            protected void onPostExecute(List<SensorData> sensorDataList) {
                int i=0;
                int sum=0;
                int avg=0;
                int max=0;
                int min=0;
                if (sensorDataList.size()>0) {
                    max = sensorDataList.get(0).getHeartrate();
                    min = sensorDataList.get(0).getHeartrate();
                    for (SensorData currenSensordata : sensorDataList) {
                        i++;
                        int currentheartrate = currenSensordata.getHeartrate();
                        sum += currentheartrate;
                        if (currentheartrate < min) min = currentheartrate;
                        if (currentheartrate > max) max = currentheartrate;
                    }

                    avg = sum / i;
                }
                textViewStationFinishedMinHR.setText(""+min);
                textViewStationFinishedMaxHR.setText(""+max);
                textViewStationFinishedAvgHR.setText(""+avg);


                switch (StationTrackingData.getActualStation()) {
                    case STATIONONE:
                        personData.setStationonetime(timeelapsed);
                        personData.setStationoneavhr(avg);
                        personData.setStationonemaxhr(max);
                        break;

                    case STATIONTWO:
                        personData.setStationtwotime(timeelapsed);
                        personData.setStationtwoavhr(avg);
                        personData.setStationtwomaxhr(max);
                        break;

                    case STATIONTHREE:
                        personData.setStationthreetime(timeelapsed);
                        personData.setStationthreeavhr(avg);
                        personData.setStationthreemaxhr(max);
                        break;

                    case STATIONFOUR:
                        personData.setStationfourtime(timeelapsed);
                        personData.setStationfouravhr(avg);
                        personData.setStationfourmaxhr(max);
                        break;

                    case STATIONFIVE:
                        personData.setStationfivetime(timeelapsed);
                        personData.setStationfiveavhr(avg);
                        personData.setStationfivemaxhr(max);
                        break;

                    default: break;
                }

                new AsyncTask<Void, Void, Void>() {


                    @Override
                    protected Void doInBackground(Void... voids) {
                        appDatabasePersonData.personDataDao().updatePerson(personData);

                    return null;
                    }
                }.execute();



            }

            @Override
            protected List<SensorData> doInBackground(Void... voids) {
                AppDatabase appDatabase = DataBaseCreator.getDataBase();
                appDatabasePersonData = DataBaseCreator.getAppDatabasePersonData();
                personData = appDatabasePersonData.personDataDao().getPersonbyID(StationTrackingData.getPrimarykeypersondata());
                return appDatabase.sensorDataDao().findbyPersonNameandStationName(personname,stationname);
            }

        }.execute();



        buttonStationFinishedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StationMenueFragment stationMenueFragment = new StationMenueFragment();

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentcontainerid, stationMenueFragment).commit();

            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //handle on backbuttonpressed event
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_BACK && keyEvent.getAction() == KeyEvent.ACTION_UP ) {

                    StationMenueFragment stationMenueFragment = new StationMenueFragment();

                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentcontainerid, stationMenueFragment).commit();
                    return true;
                }
                else {
                    return false;
                }
            }
        });

    }
}
