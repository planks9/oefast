package at.ac.univie.stefan.fast;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import at.ac.univie.stefan.fast.Activities.ConnectToMonitorActivity;
import at.ac.univie.stefan.fast.Activities.SetNameActivity;

/**
 * Created by Stefan on 23.03.2018.
 * Extend the ArrayAdapter to
 */

public class BluetoothDeviceArrayAdapter extends ArrayAdapter {
    public static final String TAG=BluetoothDeviceArrayAdapter.class.getSimpleName();
    private Context context;



    public BluetoothDeviceArrayAdapter (Context context, ArrayList<BluetoothDevice> bluetoothDeviceArrayList) {
        super(context,0, bluetoothDeviceArrayList);
        this.context=context;
    }

   @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final BluetoothDevice bluetoothDevice = (BluetoothDevice) getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_bluetoothdevice, parent, false);
        }

        // Lookup view for data population
        TextView textView = (TextView) convertView.findViewById(R.id.textitembluetooth);
        // Populate the data into the template view using the data object
        textView.setText(bluetoothDevice.getName());
        // Return the completed view to render on screen
       textView.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View view) {
               Log.d(TAG, bluetoothDevice.getName() + " is selected");
               //ToDo: Delete this

               Intent intent = new Intent(context, SetNameActivity.class);
               intent.putExtra(ConnectToMonitorActivity.MACADRESSBLUETOOTHDEVICE,bluetoothDevice.getAddress());
               intent.putExtra(SetNameActivity.BLUETOOTHDEVICENAME,bluetoothDevice.getName());

               context.startActivity(intent);

           }
       });

        return convertView;
    }





}
