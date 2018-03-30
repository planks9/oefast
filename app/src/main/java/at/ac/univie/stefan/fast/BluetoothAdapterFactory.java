package at.ac.univie.stefan.fast;

import android.bluetooth.BluetoothAdapter;

/**
 * Created by Stefan on 24.03.2018.
 */

public class BluetoothAdapterFactory {

    private BluetoothAdapter bluetoothAdapter;
    private static BluetoothAdapterFactory instance;

    private BluetoothAdapterFactory () {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    public static BluetoothAdapterFactory getInstance() {
        if (BluetoothAdapterFactory.instance==null) {
            BluetoothAdapterFactory.instance = new BluetoothAdapterFactory();
        }
         return BluetoothAdapterFactory.instance;
        }


    public BluetoothAdapter getBluetoothAdapter () {
        return this.bluetoothAdapter;
    }
}
