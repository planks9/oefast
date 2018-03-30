package at.ac.univie.stefan.fast;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Stefan on 23.03.2018.
 */

public class BluetoothServiceTwo {
    public final UUID HR_MEASUREMENT = UUID.fromString("00002a37-0000-1000-8000-00805f9b34fb");
    public final UUID HR_SERVICE = UUID.fromString("0000180D-0000-1000-8000-00805f9b34fb");
    public static final UUID DESCRIPTOR_CCC = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");

    public static final String TAG=BluetoothServiceTwo.class.getSimpleName();

    BluetoothDevice bluetoothDevice;
    BluetoothAdapter bluetoothAdapter;
    Context context;


    /*BluetoothGattCallback bluetoothGattCallback = new BluetoothGattCallback() {
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            super.onConnectionStateChange(gatt, status, newState);
            if (newState == BluetoothGatt.STATE_CONNECTED && status == BluetoothGatt.GATT_SUCCESS) {
                Log.d(TAG,"reconnected");
                gatt.discoverServices();
            } else if(newState == BluetoothGatt.STATE_DISCONNECTED){
                Log.e(TAG,"Connection lost");
                           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                               bluetoothAdapter.getBluetoothLeScanner().startScan(scanCallback);
                           } else {
                               bluetoothAdapter.startLeScan(leScanCallback);
                           }
            }
        }

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            Log.d("Callback","serviceDiscovered");
            super.onServicesDiscovered(gatt, status);
            for (BluetoothGattService gattService : gatt.getServices()) {
                if( gattService.getUuid().equals(HR_SERVICE) ){
                    for (BluetoothGattCharacteristic characteristic : gattService.getCharacteristics()) {
                        if( characteristic.getUuid().equals(HR_MEASUREMENT) ){
                            BluetoothGattDescriptor descriptor = characteristic.getDescriptor(DESCRIPTOR_CCC);
                            gatt.setCharacteristicNotification(characteristic, true);
                            descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                            gatt.writeDescriptor(descriptor);
                        }
                    }
                }
            }
        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicRead(gatt, characteristic, status);
            Log.d("Callback", "OnCharactersticRead");
        }

        @Override
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicWrite(gatt, characteristic, status);
        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            Log.d("Callback", "OnCharactersticChanged");
            super.onCharacteristicChanged(gatt, characteristic);
            if (characteristic.getUuid().equals(HR_MEASUREMENT)) {
                byte[] data = characteristic.getValue();
                int hrFormat = data[0] & 0x01;
                boolean sensorContact = true;
                final boolean contactSupported = !((data[0] & 0x06) == 0);
                if( contactSupported ) {
                    sensorContact = ((data[0] & 0x06) >> 1) == 3;
                }
                int energyExpended = (data[0] & 0x08) >> 3;
                int rrPresent = (data[0] & 0x10) >> 4;
                final int hrValue = (hrFormat == 1 ? data[1] + (data[2] << 8) : data[1]) & (hrFormat == 1 ? 0x0000FFFF : 0x000000FF);
                System.out.println(hrValue);
                if( !contactSupported && hrValue == 0 ){
                    // note does this apply to all sensors, also 3rd party
                    sensorContact = false;
                }
                final boolean sensorContactFinal = sensorContact;
                int offset = hrFormat + 2;
                int energy = 0;
                if (energyExpended == 1) {
                    energy = (data[offset] & 0xFF) + ((data[offset + 1] & 0xFF) << 8);
                    offset += 2;
                }
                final ArrayList<Integer> rrs = new ArrayList<>();
                if (rrPresent == 1) {
                    int len = data.length;
                    while (offset < len) {
                        int rrValue = (int) ((data[offset] & 0xFF) + ((data[offset + 1] & 0xFF) << 8));
                        offset += 2;
                        rrs.add(rrValue);
                    }
                }
            }
        }

        @Override
        public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            super.onDescriptorRead(gatt, descriptor, status);
        }

        @Override
        public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            super.onDescriptorWrite(gatt, descriptor, status);
        }

        @Override
        public void onMtuChanged(BluetoothGatt gatt, int mtu, int status) {
            super.onMtuChanged(gatt, mtu, status);
        }
    };

    public void connecttoxy () {
        BluetoothGatt bluetoothGatt = bluetoothDevice.connectGatt(context, false, bluetoothGattCallback );
        Log.d(TAG,"connection established");

    }*/

}

