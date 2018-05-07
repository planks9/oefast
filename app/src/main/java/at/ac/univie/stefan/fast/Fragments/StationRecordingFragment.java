package at.ac.univie.stefan.fast.Fragments;

import android.graphics.Color;
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

import at.ac.univie.stefan.fast.BluetoothMessageHandler;
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
 * Created by Stefan on 04.04.2018.
 */

public class StationRecordingFragment extends Fragment {

    public static final String TAG = StationRecordingFragment.class.getSimpleName();


    private String stationName;
    private TextView textViewRecordingPersonName;
    private TextView textViewRecordingStationName;
    private TextView textViewRecordingStationDescription;
    private TextView textViewRecordingStationTimeLimit;
    private TextView textViewRecordingHR;
    private TextView textViewRecordingRR;
    private TextView textViewRecordingTime;
    private TextView textViewRecordingConnected;
    private Button buttonRecordingStop;

    private  StopWatchService stopWatchService;

    public StationRecordingFragment() {
        stopWatchService = StopWatchService.getInstance();
    }

    public  StopWatchService getStopWatchService() {
        return stopWatchService;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        stationName = StationTrackingData.getActualStation();
        View view = inflater.inflate(R.layout.stationrecording, container, false);
        textViewRecordingPersonName = (TextView) view.findViewById(R.id.textViewRecordingPersonName);
        textViewRecordingStationName = (TextView) view.findViewById(R.id.textViewRecordingStationName);
        textViewRecordingStationDescription = (TextView) view.findViewById(R.id.textViewRecordingStationDescription);
        textViewRecordingStationTimeLimit = (TextView) view.findViewById(R.id.textViewRecordingStationTimelimit);
        textViewRecordingHR = (TextView) view.findViewById(R.id.textViewRecordingHR);
        textViewRecordingRR = (TextView) view.findViewById(R.id.textViewRecordingRR);
        textViewRecordingTime = (TextView) view.findViewById(R.id.textViewRecordingTime);
        textViewRecordingConnected = (TextView) view.findViewById(R.id.textViewRecordingConnected);
        buttonRecordingStop = (Button) view.findViewById(R.id.buttonRecordingStop);
        stopWatchService.setTextViewtimedisplay(textViewRecordingTime);
        buttonRecordingStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                StopRecordingDialogFragment stopRecordingDialogFragment = new StopRecordingDialogFragment(stopRecordingDialogListener);
                stopRecordingDialogFragment.show(getFragmentManager(), "Show StopRecordingFragment");


            }
        });

        textViewRecordingConnected.setText("getrennt");
        textViewRecordingConnected.setTextColor(Color.RED);

        textViewRecordingPersonName.setText(StationTrackingData.getPersonname());


        switch (stationName) {
            case STATIONONE:
                textViewRecordingStationName.setText(R.string.station_one_name);
                textViewRecordingStationDescription.setText(R.string.station_one_description);
                textViewRecordingStationTimeLimit.setText("Zeitlimit: "+getResources().getString(R.string.station_one_timelimit));
                stopWatchService.setTimelimit(getResources().getString(R.string.station_one_timelimit));
                break;

            case STATIONTWO:
                textViewRecordingStationName.setText(R.string.station_two_name);
                textViewRecordingStationDescription.setText(R.string.station_two_description);
                textViewRecordingStationTimeLimit.setText("Zeitlimit: "+getResources().getString(R.string.station_two_timelimit));
                stopWatchService.setTimelimit(getResources().getString(R.string.station_two_timelimit));

                break;

            case STATIONTHREE:
                textViewRecordingStationName.setText(R.string.station_three_name);
                textViewRecordingStationDescription.setText(R.string.station_three_description);
                textViewRecordingStationTimeLimit.setText("Zeitlimit: "+getResources().getString(R.string.station_three_timelimit));
                stopWatchService.setTimelimit(getResources().getString(R.string.station_three_timelimit));

                break;

            case STATIONFOUR:
                textViewRecordingStationName.setText(R.string.station_four_name);
                textViewRecordingStationDescription.setText(R.string.station_four_description);
                textViewRecordingStationTimeLimit.setText("Zeitlimit: "+getResources().getString(R.string.station_four_timelimit));
                stopWatchService.setTimelimit(getResources().getString(R.string.station_four_timelimit));

                break;

            case STATIONFIVE:
                textViewRecordingStationName.setText(R.string.station_five_name);
                textViewRecordingStationDescription.setText(R.string.station_five_description);
                textViewRecordingStationTimeLimit.setText("Zeitlimit: "+getResources().getString(R.string.station_five_timelimit));
                stopWatchService.setTimelimit(getResources().getString(R.string.station_five_timelimit));
                break;

            case PRAETEST:
                textViewRecordingStationName.setText(R.string.station_praetest);
                textViewRecordingStationDescription.setText(R.string.station_praetest_description);
                textViewRecordingStationTimeLimit.setText("Zeitlimit: "+getResources().getString(R.string.station_praetest_timelimit));
                break;

            case POSTTEST:
                textViewRecordingStationName.setText(R.string.station_posttest);
                textViewRecordingStationDescription.setText(R.string.station_posttest_description);
                textViewRecordingStationTimeLimit.setText("Zeitlimit: "+getResources().getString(R.string.station_posttest_timelimit));
                break;
        }

        BluetoothMessageHandler.getInstance().setTextViews(view, textViewRecordingHR.getId(), textViewRecordingRR.getId(), textViewRecordingConnected.getId());


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
                if (keyCode == KeyEvent.KEYCODE_BACK && keyEvent.getAction() == KeyEvent.ACTION_UP) {
                    StopRecordingDialogFragment stopRecordingDialogFragment = new StopRecordingDialogFragment(stopRecordingDialogListener);
                    stopRecordingDialogFragment.show(getFragmentManager(), "Show StopRecordingFragment");
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    private StopRecordingDialogFragment.StopRecordingDialogListener stopRecordingDialogListener = new StopRecordingDialogFragment.StopRecordingDialogListener() {
        @Override
        public void onDialogPositiveClick() {
            stopWatchService.stopTimer();
            StationTrackingData.setIsrecording(false);
            StationFinishedFragment stationFinishedFragment = new StationFinishedFragment();

            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentcontainerid, stationFinishedFragment).commit();

        }

        @Override
        public void onDialogNegativeClick() {

        }
    };


}
