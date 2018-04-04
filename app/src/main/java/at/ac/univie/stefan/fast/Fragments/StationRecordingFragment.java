package at.ac.univie.stefan.fast.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import at.ac.univie.stefan.fast.MessageHandlerFactory;
import at.ac.univie.stefan.fast.R;
import at.ac.univie.stefan.fast.Stopwatch;

/**
 * Created by Stefan on 04.04.2018.
 */

public class StationRecordingFragment extends Fragment {

    public static final String TAG = StationRecordingFragment.class.getSimpleName();
    public static final int MSG_START_TIMER = 0;
    public static final int MSG_STOP_TIMER = 1;
    public static final int MSG_UPDATE_TIMER = 2;
    public static final int REFRESH_RATE_OF_UI_WATCH=500;

    private TextView textViewRecordingPersonName;
    private TextView textViewRecordingStationName;
    private TextView textViewRecordingStationDescription;
    private TextView textViewRecordingHR;
    private TextView textViewRecordingRR;
    private TextView textViewRecordingTime;
    private TextView textViewRecordingConnected;
    private Button buttonRecordingStop;


    private Stopwatch stopwatch=new Stopwatch();

    public Stopwatch getStopwatch() {
        return stopwatch;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stationrecording, container, false);
        textViewRecordingPersonName = (TextView) view.findViewById(R.id.textViewRecordingPersonName);
        textViewRecordingStationName = (TextView) view.findViewById(R.id.textViewRecordingStationName);
        textViewRecordingStationDescription = (TextView) view.findViewById(R.id.textViewRecordingStationDescription);
        textViewRecordingHR = (TextView) view.findViewById(R.id.textViewRecordingHR);
        textViewRecordingRR = (TextView) view.findViewById(R.id.textViewRecordingRR);
        textViewRecordingTime = (TextView) view.findViewById(R.id.textViewRecordingTime);
        textViewRecordingConnected = (TextView) view.findViewById(R.id.textViewRecordingConnected);
        buttonRecordingStop = (Button) view.findViewById(R.id.buttonRecordingStop);
        buttonRecordingStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopwatchhandler.sendEmptyMessage(MSG_STOP_TIMER);
            }
        });

        textViewRecordingStationName.setText(getArguments().getString(StationMenueFragment.STATIONNAME));
        MessageHandlerFactory.getInstance().getHandlerandsetViews(view,textViewRecordingHR.getId(),textViewRecordingRR.getId(),textViewRecordingConnected.getId());



        return view;
    }


    public Handler getStopwatchhandler() {
        return stopwatchhandler;
    }

    Handler stopwatchhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_START_TIMER:
                    getStopwatch().start(); //start timer
                    stopwatchhandler.sendEmptyMessage(MSG_UPDATE_TIMER);
                    break;

                case MSG_UPDATE_TIMER:
                    textViewRecordingTime.setText("" + getStopwatch().getTimeElapsedinSec());
                    stopwatchhandler.sendEmptyMessageDelayed(MSG_UPDATE_TIMER, REFRESH_RATE_OF_UI_WATCH); //text view is updated every second,
                    break;                                  //though the timer is still running
                case MSG_STOP_TIMER:
                    stopwatchhandler.removeMessages(MSG_UPDATE_TIMER); // no more updates.
                    getStopwatch().stop();//stop timer
                    textViewRecordingTime.setText("" + getStopwatch().getTimeElapsedinSec());
                    break;

                default:
                    break;
            }
        }
    };


}
