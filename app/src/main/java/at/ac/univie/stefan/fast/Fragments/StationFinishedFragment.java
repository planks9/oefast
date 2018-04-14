package at.ac.univie.stefan.fast.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import at.ac.univie.stefan.fast.R;
import at.ac.univie.stefan.fast.StationTracking.StationTrackingData;

import static at.ac.univie.stefan.fast.KeyValues.STATIONNAME;

/**
 * Created by Stefan on 09.04.2018.
 */

public class StationFinishedFragment extends Fragment {

    private String stationName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        stationName = StationTrackingData.getActualStation();
        View view = inflater.inflate(R.layout.stationfinished, container, false);
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
