package at.ac.univie.stefan.fast.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import at.ac.univie.stefan.fast.Activities.PersonManagementActivity;
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

public class StationMenueFragment extends Fragment {


    View viewone;
    View viewtwo;
    View viewthree;
    View viewfour;
    View viewfive;
    View viewprae;
    View viewpost;
    Button buttonendEndDurchlauf;
    TextView textViewConnected;
    TextView textViewPersonName;
    TextView textViewStationMenueStationOneDescription;
    TextView textViewStationMenueStationTwoDescription;
    TextView textViewStationMenueStationThreeDescription;
    TextView textViewStationMenueStationFourDescription;
    TextView textViewStationMenueStationFiveDescription;
    TextView textViewStationMenueStationPraeDescription;
    TextView textViewStationMenueStationPostDescription;
    ImageView imageViewStationOne;
    ImageView imageViewStationTwo;
    ImageView imageViewStationThree;
    ImageView imageViewStationFour;
    ImageView imageViewStationFive;
    ImageView imageViewPrae;
    ImageView imageViewPost;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //Set View to Layout StationMenue
        View view = inflater.inflate(R.layout.stationmenue, container, false);
        textViewConnected = view.findViewById(R.id.beltconnectionstate);
        buttonendEndDurchlauf = view.findViewById(R.id.enddurchlauf);
        viewone = view.findViewById(R.id.menuestationone);
        viewtwo = view.findViewById(R.id.menuestationtwo);
        viewthree = view.findViewById(R.id.menuestationthree);
        viewfour = view.findViewById(R.id.menuestationfour);
        viewfive = view.findViewById(R.id.menuestationfive);
        viewprae = view.findViewById(R.id.menuestationprae);
        viewpost = view.findViewById(R.id.menuestationpost);
        textViewStationMenueStationOneDescription = (TextView) viewone.findViewById(R.id.stationmenuestationdescription);
        textViewStationMenueStationTwoDescription = (TextView) viewtwo.findViewById(R.id.stationmenuestationdescription);
        textViewStationMenueStationThreeDescription = (TextView) viewthree.findViewById(R.id.stationmenuestationdescription);
        textViewStationMenueStationFourDescription = (TextView) viewfour.findViewById(R.id.stationmenuestationdescription);
        textViewStationMenueStationFiveDescription = (TextView) viewfive.findViewById(R.id.stationmenuestationdescription);
        textViewStationMenueStationPraeDescription = (TextView) viewprae.findViewById(R.id.stationmenuestationdescription);
        textViewStationMenueStationPostDescription = (TextView) viewpost.findViewById(R.id.stationmenuestationdescription);
        ((TextView) viewone.findViewById(R.id.stationmenuestationnumber)).setText(R.string.station_one_number);
        ((TextView) viewtwo.findViewById(R.id.stationmenuestationnumber)).setText(R.string.station_two_number);
        ((TextView) viewthree.findViewById(R.id.stationmenuestationnumber)).setText(R.string.station_three_number);
        ((TextView) viewfour.findViewById(R.id.stationmenuestationnumber)).setText(R.string.station_four_number);
        ((TextView) viewfive.findViewById(R.id.stationmenuestationnumber)).setText(R.string.station_five_number);
        ((TextView) viewprae.findViewById(R.id.stationmenuestationnumber)).setText(R.string.station_prae_number);
        ((TextView) viewpost.findViewById(R.id.stationmenuestationnumber)).setText(R.string.station_post_number);
        imageViewStationOne = viewone.findViewById(R.id.stationmenueimageview);
        imageViewStationTwo = viewtwo.findViewById(R.id.stationmenueimageview);
        imageViewStationThree = viewthree.findViewById(R.id.stationmenueimageview);
        imageViewStationFour = viewfour.findViewById(R.id.stationmenueimageview);
        imageViewStationFive = viewfive.findViewById(R.id.stationmenueimageview);
        imageViewPrae = viewprae.findViewById(R.id.stationmenueimageview);
        imageViewPost = viewpost.findViewById(R.id.stationmenueimageview);
        imageViewStationOne.setImageResource(R.drawable.ic_baseline_done);
        imageViewStationTwo.setImageResource(R.drawable.ic_baseline_arrow_right_alt);
        imageViewStationThree.setImageResource(R.drawable.ic_baseline_arrow_right_alt);
        imageViewStationFour.setImageResource(R.drawable.ic_baseline_arrow_right_alt);
        imageViewStationFive.setImageResource(R.drawable.ic_baseline_arrow_right_alt);
        imageViewPrae.setImageResource(R.drawable.ic_baseline_done);
        imageViewPost.setImageResource(R.drawable.ic_baseline_arrow_right_alt);


        //Set first Text to getrennt because the belt is connecting in the background and as soon as the belt is connected it sets the Text to connected

        BluetoothMessageHandler.getInstance().setTextViews(view, 0, 0, R.id.beltconnectionstate);


        textViewStationMenueStationOneDescription.setText(R.string.station_one_description);
        textViewStationMenueStationTwoDescription.setText(R.string.station_two_description);
        textViewStationMenueStationThreeDescription.setText(R.string.station_three_description);
        textViewStationMenueStationFourDescription.setText(R.string.station_four_description);
        textViewStationMenueStationFiveDescription.setText(R.string.station_five_description);
        textViewStationMenueStationPraeDescription.setText(R.string.station_praetest_description);
        textViewStationMenueStationPostDescription.setText(R.string.station_posttest_description);


        viewone.setOnClickListener(onClickListener);
        viewtwo.setOnClickListener(onClickListener);
        viewthree.setOnClickListener(onClickListener);
        viewfour.setOnClickListener(onClickListener);
        viewfive.setOnClickListener(onClickListener);
        viewprae.setOnClickListener(onClickListener);
        viewpost.setOnClickListener(onClickListener);
        buttonendEndDurchlauf.setOnClickListener(onClickListener);
        return view;


    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == viewone.getId()) {
                StationTrackingData.setActualStation(STATIONONE);
            } else if (view.getId() == viewtwo.getId()) {
                StationTrackingData.setActualStation(STATIONTWO);
            } else if (view.getId() == viewthree.getId()) {
                StationTrackingData.setActualStation(STATIONTHREE);
            } else if (view.getId() == viewfour.getId()) {
                StationTrackingData.setActualStation(STATIONFOUR);
            } else if (view.getId() == viewfive.getId()) {
                StationTrackingData.setActualStation(STATIONFIVE);
            } else if (view.getId() == viewprae.getId()) {
                StationTrackingData.setActualStation(PRAETEST);
            } else if (view.getId() == viewpost.getId()) {
                StationTrackingData.setActualStation(POSTTEST);
            }

            //Handle next step to be made
            if (view.getId() == buttonendEndDurchlauf.getId()) {
                StationTrackingData.setIsrecordingwholedurchlauf(false);
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
