package at.ac.univie.stefan.fast.Fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import at.ac.univie.stefan.fast.Activities.ConnectToMonitorActivity;
import at.ac.univie.stefan.fast.BluetoothMessageHandler;
import at.ac.univie.stefan.fast.R;
import at.ac.univie.stefan.fast.StationTracking.StationTrackingData;

import static at.ac.univie.stefan.fast.StationTracking.StationTrackingData.POSTTEST;
import static at.ac.univie.stefan.fast.StationTracking.StationTrackingData.PRAETEST;
import static at.ac.univie.stefan.fast.StationTracking.StationTrackingData.STATIONFIVE;
import static at.ac.univie.stefan.fast.StationTracking.StationTrackingData.STATIONFOUR;
import static at.ac.univie.stefan.fast.StationTracking.StationTrackingData.STATIONONE;
import static at.ac.univie.stefan.fast.StationTracking.StationTrackingData.STATIONTHREE;
import static at.ac.univie.stefan.fast.StationTracking.StationTrackingData.STATIONTWO;


/**
 * Created by Stefan on 03.04.2018.
 */

public class StationReadyFragment extends Fragment {

    TextView textViewPersonName;
    TextView textViewStationName;
    TextView textViewStationDescription;
    TextView textViewStationTimelimit;
    TextView textViewHR;
    TextView textViewRR;
    TextView textViewconnectionState;
    Button buttonStart;
    private StationRecordingFragment stationRecordingFragment;
    private String stationName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.stationrecording, container, false);

        stationName = StationTrackingData.getActualStation();
        textViewPersonName = (TextView) view.findViewById(R.id.textViewRecordingPersonName);
        textViewStationName = (TextView) view.findViewById(R.id.textViewRecordingStationName);
        textViewStationDescription = (TextView) view.findViewById(R.id.textViewRecordingStationDescription);
        textViewStationTimelimit = (TextView) view.findViewById(R.id.textViewRecordingStationTimelimit);
        textViewHR = (TextView) view.findViewById(R.id.textViewRecordingHR);
        textViewRR = (TextView) view.findViewById(R.id.textViewRecordingRR);
        textViewconnectionState = (TextView) view.findViewById(R.id.beltconnectionstate);
        buttonStart = (Button) view.findViewById(R.id.buttonRecordingStop);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((ConnectToMonitorActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });
        ((ConnectToMonitorActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        stationRecordingFragment = new StationRecordingFragment();
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stationRecordingFragment.getStopWatchService().startTimer();
                StationTrackingData.setIsrecordingStation(true);

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentcontainerid, stationRecordingFragment).addToBackStack("BackToStationMenue").commit();


            }
        });
        textViewconnectionState.setText("getrennt");
        textViewconnectionState.setTextColor(getResources().getColor(R.color.colorError));
        textViewPersonName.setText(StationTrackingData.getPersonname());
        switch (stationName) {
            case STATIONONE:
                textViewStationName.setText(R.string.station_one_name);
                textViewStationDescription.setText(R.string.station_one_description);
                textViewStationTimelimit.setText("Zeitlimit: "+getResources().getString(R.string.station_one_timelimit)+" Minuten");
                break;

            case STATIONTWO:
                textViewStationName.setText(R.string.station_two_name);
                textViewStationDescription.setText(R.string.station_two_description);
                textViewStationTimelimit.setText("Zeitlimit: "+getResources().getString(R.string.station_two_timelimit)+" Minuten");
                break;

            case STATIONTHREE:
                textViewStationName.setText(R.string.station_three_name);
                textViewStationDescription.setText(R.string.station_three_description);
                textViewStationTimelimit.setText("Zeitlimit: "+getResources().getString(R.string.station_three_timelimit)+" Minuten");
                break;

            case STATIONFOUR:
                textViewStationName.setText(R.string.station_four_name);
                textViewStationDescription.setText(R.string.station_four_description);
                textViewStationTimelimit.setText("Zeitlimit: "+getResources().getString(R.string.station_four_timelimit)+" Minuten");
                break;

            case STATIONFIVE:
                textViewStationName.setText(R.string.station_five_name);
                textViewStationDescription.setText(R.string.station_five_description);
                textViewStationTimelimit.setText("Zeitlimit: "+getResources().getString(R.string.station_five_timelimit)+" Minuten");
                break;

            case PRAETEST:
                textViewStationName.setText(R.string.station_praetest);
                textViewStationDescription.setText(R.string.station_praetest_description);
                textViewStationTimelimit.setText("Zeitlimit: "+getResources().getString(R.string.station_praetest_timelimit)+" Minuten");
                break;

            case POSTTEST:
                textViewStationName.setText(R.string.station_posttest);
                textViewStationDescription.setText(R.string.station_posttest_description);
                textViewStationTimelimit.setText("Zeitlimit: "+getResources().getString(R.string.station_posttest_timelimit)+" Minuten");
                break;
        }

        BluetoothMessageHandler.getInstance().setTextViews(view, textViewHR.getId(), textViewRR.getId(), textViewconnectionState.getId());


        return view;
    }


}
