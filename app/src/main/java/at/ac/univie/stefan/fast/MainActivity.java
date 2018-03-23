package at.ac.univie.stefan.fast;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Set;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_ENABLE_BT = 10;


    private ListView listView;
    private Button buttonsearchfornewDevices;
    private TextView textviewissearching;

    private ArrayList <BluetoothDevice> deviceArrayList;

    private BluetoothAdapter bluetoothAdapter;
    private BluetoothManager bluetoothManager;
    private BluetoothDeviceArrayAdapter arrayAdapter;
    private BluetoothDevice selectedBluetoothDevice;

    public static final String TAG = MainActivity.class.getSimpleName();

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                Log.d(TAG,"found Device with Name: "+device.getName()+" and Adress: "+device.getAddress());
                arrayAdapter.add(device);

            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listview_bluetoothdevices);
        buttonsearchfornewDevices = (Button) findViewById(R.id.button_search_for_new_devices);
        textviewissearching = (TextView) findViewById(R.id.textviewsearching);

        deviceArrayList = new ArrayList<BluetoothDevice>();
        bluetoothManager= (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        bluetoothAdapter = bluetoothManager.getAdapter();

        buttonsearchfornewDevices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"Starting Searching for new BluetoothDevices");
                textviewissearching.setVisibility(View.VISIBLE);


                bluetoothAdapter.startDiscovery();
                IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
                registerReceiver(mReceiver, filter);




            }
        });

        checkifDevicehasBluetooth();
        enableBluetooth ();
        Set<BluetoothDevice> pairedDevices = getpairedDevicesfromSystem();
        deviceArrayList.addAll(pairedDevices);


        arrayAdapter = new BluetoothDeviceArrayAdapter(
                this,
                deviceArrayList,
                selectedBluetoothDevice);
        listView.setAdapter(arrayAdapter);

        System.out.println(selectedBluetoothDevice.getName());

    }

    private Set<BluetoothDevice> getpairedDevicesfromSystem() {
        return bluetoothAdapter.getBondedDevices();
    }

    private void checkifDevicehasBluetooth() {
        if (bluetoothAdapter == null) {
        Log.e(TAG,"Device doesn't support Bluetooth");
        }
    }


    private void enableBluetooth() {
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Don't forget to unregister the ACTION_FOUND receiver.
        unregisterReceiver(mReceiver);
    }



    public BluetoothAdapter getBluetoothAdapter() {
        return bluetoothAdapter;
    }


}
