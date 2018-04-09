package at.ac.univie.stefan.fast.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import at.ac.univie.stefan.fast.MessageHandlerFactory;
import at.ac.univie.stefan.fast.R;

import static at.ac.univie.stefan.fast.KeyValues.STATIONNAME;


/**
 * Created by Stefan on 03.04.2018.
 */

public class StationReadyFragment extends Fragment {

    TextView textViewPersonName;
    TextView textViewStationName;
    TextView textViewStationDescription;
    TextView textViewHR;
    TextView textViewRR;
    TextView textViewconnectionState;
    Button buttonStart;
    private StationRecordingFragment stationRecordingFragment;
    private String stationName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.stationready, container, false);

        stationName = getArguments().getString(STATIONNAME);
        textViewPersonName = (TextView) view.findViewById(R.id.textViewPersonName);
        textViewStationName = (TextView) view.findViewById(R.id.textViewStationName);
        textViewStationDescription = (TextView) view.findViewById(R.id.textViewStationDescription);
        textViewHR = (TextView) view.findViewById(R.id.textViewHR);
        textViewRR = (TextView) view.findViewById(R.id.textViewRR);
        textViewconnectionState = (TextView) view.findViewById(R.id.textViewStationReadyConnected);
        textViewconnectionState.setText("waiting...");
        buttonStart = (Button) view.findViewById(R.id.buttonStart);
        stationRecordingFragment = new StationRecordingFragment();
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stationRecordingFragment.getStopWatchService().startTimer();

                Bundle bundle = new Bundle();
                bundle.putString(STATIONNAME, stationName);
                stationRecordingFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentcontainerid, stationRecordingFragment).addToBackStack("BackToStationMenue").commit();


            }
        });

        textViewStationName.setText(stationName);

        MessageHandlerFactory.getInstance().setTextViews(view, textViewHR.getId(), textViewRR.getId(), textViewconnectionState.getId());


        return view;
    }


}
