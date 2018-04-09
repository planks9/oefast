package at.ac.univie.stefan.fast.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by Stefan on 09.04.2018.
 */


public class StopRecordingDialogFragment extends DialogFragment {

    public StopRecordingDialogFragment () {

    }

    public StopRecordingDialogFragment (StopRecordingDialogListener stopRecordingDialogListenercallback) {
        this.stopRecordingDialogListenercallback=stopRecordingDialogListenercallback;
    }


    public interface StopRecordingDialogListener {
        public void onDialogPositiveClick();
        public void onDialogNegativeClick();

    }

    StopRecordingDialogListener stopRecordingDialogListenercallback;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //check if the callback interface is implented
        if (stopRecordingDialogListenercallback == null) {
            throw new RuntimeException("Interface StopRecordingDialogListener must be implemented");
        }


    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Aufzeichung beenden?")
                .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        stopRecordingDialogListenercallback.onDialogPositiveClick();
                    }
                })
                .setNegativeButton("Nein", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        stopRecordingDialogListenercallback.onDialogNegativeClick();
                    }
                });

        return builder.create();
    }
}
