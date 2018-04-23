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
import at.ac.univie.stefan.fast.DataBase.DataBaseCreator;
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
        textViewStationFinishedTime.setText(StopWatchService.getStopwatch().getTimeinString());

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
            @Override
            protected void onPostExecute(List<SensorData> sensorDataList) {
                int i=0;
                int max=sensorDataList.get(0).getHeartrate();
                int min=sensorDataList.get(0).getHeartrate();
                int sum=0;
                int avg=0;
                System.out.println("Size : "+sensorDataList.size());
                for (SensorData currenSensordata : sensorDataList) {
                    i++;
                    int currentheartrate = currenSensordata.getHeartrate();
                    sum+= currentheartrate;
                    if (currentheartrate<min) min = currentheartrate;
                    if (currentheartrate>max) max = currentheartrate;
                }

                avg = sum/i;
                textViewStationFinishedMinHR.setText(""+min);
                textViewStationFinishedMaxHR.setText(""+max);
                textViewStationFinishedAvgHR.setText(""+avg);
            }

            @Override
            protected List<SensorData> doInBackground(Void... voids) {
                AppDatabase appDatabase = DataBaseCreator.getDataBase();
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
