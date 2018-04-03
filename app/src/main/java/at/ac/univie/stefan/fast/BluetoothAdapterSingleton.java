package at.ac.univie.stefan.fast;

import android.bluetooth.BluetoothAdapter;

/**
 * Created by Stefan on 24.03.2018.
 */

public class BluetoothAdapterSingleton {

    private BluetoothAdapter bluetoothAdapter;
    private static BluetoothAdapterSingleton instance;

    private BluetoothAdapterSingleton() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    public static BluetoothAdapterSingleton getInstance() {
        if (BluetoothAdapterSingleton.instance==null) {
            BluetoothAdapterSingleton.instance = new BluetoothAdapterSingleton();
        }
         return BluetoothAdapterSingleton.instance;
        }


    public BluetoothAdapter getBluetoothAdapter () {
        return this.bluetoothAdapter;
    }
}
