package at.ac.univie.stefan.fast;

import android.bluetooth.BluetoothAdapter;

/**
 * Class to provide the whole module with the same bluetoothAdapter, so you do not need to reinstanciat
 */

public class BluetoothAdapterSingleton {

    private static BluetoothAdapter bluetoothAdapter;
    private BluetoothAdapterSingleton() {  }

    public static BluetoothAdapter getBluetoothAdapter () {
        if (bluetoothAdapter == null) {
            bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        }
        return bluetoothAdapter;
    }
}
