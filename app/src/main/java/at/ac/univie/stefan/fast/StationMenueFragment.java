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

public class StationMenueFragment extends Fragment {

    Button buttonStationOne;
    Button buttonStationTwo;
    Button buttonStationThree;
    Button buttonStationFour;
    Button buttonStationFive;
    Button buttonGesamtstatistik;
    Button buttonBerichtStationOne;
    Button buttonBerichtStationTwo;
    Button buttonBerichtStationThree;
    Button buttonBerichtStationFour;
    Button buttonBerichtStationFive;
    TextView textViewConnected;
    Handler handler;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.stationmenue, container, false);
       buttonStationOne = (Button) view.findViewById(R.id.buttonStationOne);
       buttonStationTwo = (Button) view.findViewById(R.id.buttonStationTwo);
       buttonStationThree = (Button) view.findViewById(R.id.buttonStationThree);
       buttonStationFour = (Button) view.findViewById(R.id.buttonStationFour);
       buttonStationFive = (Button) view.findViewById(R.id.buttonStationFive);
       buttonGesamtstatistik = (Button) view.findViewById(R.id.buttonGesamtstatistik);
       buttonBerichtStationOne = (Button) view.findViewById(R.id.buttonBerichtStationOne);
       buttonBerichtStationTwo = (Button) view.findViewById(R.id.buttonBerichtStationTwo);
       buttonBerichtStationThree = (Button) view.findViewById(R.id.buttonBerichtStationThree);
       buttonBerichtStationFour = (Button) view.findViewById(R.id.buttonBerichtStationFour);
       buttonBerichtStationFive = (Button) view.findViewById(R.id.buttonBerichtStationFive);
       textViewConnected = (TextView) view.findViewById(R.id.textViewConnected);
        System.out.println("Ask Factory");
       handler = MessageHandlerFactory.getInstance().getHandlerandsetViews(view,R.id.textViewHR,R.id.textViewRR,R.id.textViewConnected);





       return view;


    }


}
