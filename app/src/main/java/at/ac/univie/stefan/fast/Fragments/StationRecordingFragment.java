package at.ac.univie.stefan.fast.Fragments;

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

import at.ac.univie.stefan.fast.MessageHandlerFactory;
import at.ac.univie.stefan.fast.R;
import at.ac.univie.stefan.fast.Stopwatch.StopWatchService;
import static at.ac.univie.stefan.fast.KeyValues.STATIONNAME;

/**
 * Created by Stefan on 04.04.2018.
 */

public class StationRecordingFragment extends Fragment  {

    public static final String TAG = StationRecordingFragment.class.getSimpleName();


    private String stationName;
    private TextView textViewRecordingPersonName;
    private TextView textViewRecordingStationName;
    private TextView textViewRecordingStationDescription;
    private TextView textViewRecordingHR;
    private TextView textViewRecordingRR;
    private TextView textViewRecordingTime;
    private TextView textViewRecordingConnected;
    private Button buttonRecordingStop;

    private StopWatchService stopWatchService;

    public StationRecordingFragment () {
        stopWatchService = new StopWatchService();
    }

    public StopWatchService getStopWatchService() {
        return stopWatchService;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        stationName = getArguments().getString(STATIONNAME);
        View view = inflater.inflate(R.layout.stationrecording, container, false);
        textViewRecordingPersonName = (TextView) view.findViewById(R.id.textViewRecordingPersonName);
        textViewRecordingStationName = (TextView) view.findViewById(R.id.textViewRecordingStationName);
        textViewRecordingStationDescription = (TextView) view.findViewById(R.id.textViewRecordingStationDescription);
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
                stopRecordingDialogFragment.show(getFragmentManager(),"Show StopRecordingFragment");


            }
        });

        textViewRecordingStationName.setText(stationName);
        MessageHandlerFactory.getInstance().setTextViews(view,textViewRecordingHR.getId(),textViewRecordingRR.getId(),textViewRecordingConnected.getId());



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
                    StopRecordingDialogFragment stopRecordingDialogFragment = new StopRecordingDialogFragment(stopRecordingDialogListener);
                    stopRecordingDialogFragment.show(getFragmentManager(),"Show StopRecordingFragment");
                    return true;
                }
                else {
                    return false;
                }
            }
        });
    }

    private StopRecordingDialogFragment.StopRecordingDialogListener stopRecordingDialogListener = new StopRecordingDialogFragment.StopRecordingDialogListener() {
        @Override
        public void onDialogPositiveClick() {
            stopWatchService.stopTimer();
            Bundle bundle = new Bundle();
            bundle.putString(STATIONNAME, stationName);
            StationFinishedFragment stationFinishedFragment = new StationFinishedFragment();
            stationFinishedFragment.setArguments(bundle);

            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentcontainerid, stationFinishedFragment).commit();

        }

        @Override
        public void onDialogNegativeClick() {

        }
    };




}
