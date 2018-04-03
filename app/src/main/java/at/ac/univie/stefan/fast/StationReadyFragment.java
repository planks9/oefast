package at.ac.univie.stefan.fast;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Stefan on 03.04.2018.
 */

public class StationReadyFragment extends Fragment {

    TextView textViewPersonName;
    TextView textViewStationName;
    TextView textViewStatonDescription;
    TextView textViewHR;
    TextView textViewRR;
    TextView textViewconnectionState;
    Button buttonStart;
    Handler handler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.stationready,container,false);
        textViewPersonName = (TextView) view.findViewById(R.id.textViewPersonName);
        textViewStationName = (TextView) view.findViewById(R.id.textViewStationName);
        textViewStatonDescription = (TextView) view.findViewById(R.id.textViewStatonDescription);
        textViewHR = (TextView) view.findViewById(R.id.textViewStatonDescription);
        textViewRR = (TextView) view.findViewById(R.id.textViewRR);
        textViewconnectionState = (TextView) view.findViewById(R.id.textViewconnectionState);
        textViewconnectionState.setText("waiting...");
        buttonStart = (Button) view.findViewById(R.id.buttonStart);

        handler = MessageHandlerFactory.getInstance().getHandlerandsetViews(view,R.id.textViewHR,R.id.textViewRR,R.id.textViewConnected);




        return view;
    }
}
