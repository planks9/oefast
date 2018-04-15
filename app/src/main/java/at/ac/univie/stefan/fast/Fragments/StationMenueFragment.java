package at.ac.univie.stefan.fast.Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import at.ac.univie.stefan.fast.Activities.PersonManagementActivity;
import at.ac.univie.stefan.fast.BluetoothMessageHandler;
import at.ac.univie.stefan.fast.R;
import at.ac.univie.stefan.fast.StationTracking.StationTrackingData;

import static at.ac.univie.stefan.fast.StationTracking.StationTrackingData.STATIONFIVE;
import static at.ac.univie.stefan.fast.StationTracking.StationTrackingData.STATIONFOUR;
import static at.ac.univie.stefan.fast.StationTracking.StationTrackingData.STATIONONE;
import static at.ac.univie.stefan.fast.StationTracking.StationTrackingData.STATIONTHREE;
import static at.ac.univie.stefan.fast.StationTracking.StationTrackingData.STATIONTWO;

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
    Button buttonendEndDurchlauf;
    TextView textViewConnected;
    TextView textViewPersonName;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //Set View to Layout StationMenue
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
        buttonendEndDurchlauf = (Button) view.findViewById(R.id.buttonEndDurchlauf);
        textViewConnected = (TextView) view.findViewById(R.id.textViewStationMenueConnected);
        textViewPersonName = (TextView) view.findViewById(R.id.textViewStationMenuePersonName);
        //Set first Text to getrennt because the belt is connecting in the background and as soon as the belt is connected it sets the Text to connected
        textViewConnected.setText("getrennt");
        textViewConnected.setTextColor(Color.RED);
        textViewPersonName.setText(StationTrackingData.getPersonname());

        BluetoothMessageHandler.getInstance().setTextViews(view, 0, 0, R.id.textViewStationMenueConnected);


        buttonStationOne.setOnClickListener(onClickListener);

        buttonStationTwo.setOnClickListener(onClickListener);

        buttonStationThree.setOnClickListener(onClickListener);

        buttonStationFour.setOnClickListener(onClickListener);

        buttonStationFive.setOnClickListener(onClickListener);

        buttonendEndDurchlauf.setOnClickListener(onClickListener);


        return view;


    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            if (view.getId()==buttonStationOne.getId()) {
                StationTrackingData.setActualStation(STATIONONE);
            }
            else if (view.getId()==buttonStationTwo.getId()) {
                StationTrackingData.setActualStation(STATIONTWO);
            }
            else if (view.getId()==buttonStationThree.getId()) {
                StationTrackingData.setActualStation(STATIONTHREE);
            }
            else if (view.getId()==buttonStationFour.getId()) {
                StationTrackingData.setActualStation(STATIONFOUR);
            }
            else if (view.getId()==buttonStationFive.getId()) {
                StationTrackingData.setActualStation(STATIONFIVE);
            }

            else {
                Log.e(StationMenueFragment.class.getSimpleName(),"Could not map button press");
            }

            //Handle next step to be made
            if (view.getId()==buttonendEndDurchlauf.getId()) {
                Intent intent = new Intent(getContext(), PersonManagementActivity.class);
                startActivity(intent);
            } else {
                StationReadyFragment stationReadyFragment = new StationReadyFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                //Switch to new Fragment and Set this Fragment to be shown when back button is pressed
                fragmentTransaction.replace(R.id.fragmentcontainerid, stationReadyFragment).addToBackStack("BackToStationMenue").commit();
            }

        }
    };




}
