package at.ac.univie.stefan.fast.Activities;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;

import at.ac.univie.stefan.fast.BluetoothAdapterSingleton;
import at.ac.univie.stefan.fast.DataBase.AppDatabase;
import at.ac.univie.stefan.fast.DataBase.DataBaseCreator;
import at.ac.univie.stefan.fast.DataBase.SensorData;
import at.ac.univie.stefan.fast.Fragments.StationMenueFragment;
import at.ac.univie.stefan.fast.BluetoothMessageHandler;
import at.ac.univie.stefan.fast.R;
import at.ac.univie.stefan.fast.StationTracking.StationTrackingData;
import at.ac.univie.stefan.fast.Stopwatch.StopWatchService;

public class ConnectToMonitorActivity extends AppCompatActivity {

    public static final UUID DESCRIPTOR_CCC = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");
    public static final int MESSAGEIDHRVALUE = 1;
    public static final int MESSAGEIDRRVALUE = 2;
    public static final int MESSAGEIDCONNECTION = 3;
    public static final String TAG = ConnectToMonitorActivity.class.getSimpleName();
    public static final String MACADRESSBLUETOOTHDEVICE = "MACADRESSE";
    public final UUID HR_MEASUREMENT = UUID.fromString("00002a37-0000-1000-8000-00805f9b34fb");
    public final UUID HR_SERVICE = UUID.fromString("0000180D-0000-1000-8000-00805f9b34fb");
    private Context context;
    private BluetoothDevice bluetoothDevice;
    private BluetoothAdapter bluetoothAdapter;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmentcontainer);
        context = getApplicationContext();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentcontainerid, new StationMenueFragment()).commit();

        Intent intent = getIntent();
        bluetoothAdapter = BluetoothAdapterSingleton.getBluetoothAdapter();
        bluetoothDevice = bluetoothAdapter.getRemoteDevice(intent.getStringExtra(MACADRESSBLUETOOTHDEVICE));

        //Start DataBaseConnection
        //DataBaseCreator.createnewDataBase(context);
        appDatabase=DataBaseCreator.getDataBase();

        bluetoothDevice.connectGatt(context, false, bluetoothGattCallback);
        Log.d(TAG, "connection established");

    }


    private BluetoothAdapter.LeScanCallback leScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
            // processDeviceDiscovered(device,rssi,scanRecord);
        }
    };
    private BluetoothGattCallback bluetoothGattCallback = new BluetoothGattCallback() {

        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            Log.d(TAG, "onConnectionStateChange");
            Log.d(TAG, "Status " + status + "newState " + newState + "");
            super.onConnectionStateChange(gatt, status, newState);
            if (newState == BluetoothGatt.STATE_CONNECTED && status == BluetoothGatt.GATT_SUCCESS) {
                gatt.discoverServices();
            } else if (newState == BluetoothGatt.STATE_DISCONNECTED) {
                Log.e(TAG, "disconnected from Belt");
                gatt.connect();
            }
        }

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            Log.d(TAG, "onServiceDiscovered");
            super.onServicesDiscovered(gatt, status);
            for (BluetoothGattService gattService : gatt.getServices()) {
                if (gattService.getUuid().equals(HR_SERVICE)) {
                    for (BluetoothGattCharacteristic characteristic : gattService.getCharacteristics()) {
                        if (characteristic.getUuid().equals(HR_MEASUREMENT)) {
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
            Log.d(TAG, "onCharacteristicRead");
            super.onCharacteristicRead(gatt, characteristic, status);
        }

        @Override
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            Log.d(TAG, "onCharacteristicWrite");
            super.onCharacteristicWrite(gatt, characteristic, status);
        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            super.onCharacteristicChanged(gatt, characteristic);
            if (characteristic.getUuid().equals(HR_MEASUREMENT)) {
                byte[] data = characteristic.getValue();
                int hrFormat = data[0] & 0x01;
                boolean sensorContact = true;
                final boolean contactSupported = !((data[0] & 0x06) == 0);
                if (contactSupported) {
                    sensorContact = ((data[0] & 0x06) >> 1) == 3;

                }
                int energyExpended = (data[0] & 0x08) >> 3;

                int rrPresent = (data[0] & 0x10) >> 4;
                final int hrValue = (hrFormat == 1 ? data[1] + (data[2] << 8) : data[1]) & (hrFormat == 1 ? 0x0000FFFF : 0x000000FF);
                Message newhrv = BluetoothMessageHandler.getInstance().getHandler().obtainMessage(MESSAGEIDHRVALUE, hrValue);
                newhrv.sendToTarget();

                if (!contactSupported && hrValue == 0) {
                    // note does this apply to all sensors, also 3rd party
                    sensorContact = false;
                }
                boolean sensorContactFinal = sensorContact;


                int offset = hrFormat + 2;
                int energy = 0;
                if (energyExpended == 1) {
                    energy = (data[offset] & 0xFF) + ((data[offset + 1] & 0xFF) << 8);
                    offset += 2;
                }
                int rrValue=0;
                final ArrayList<Integer> rrs = new ArrayList<>();
                if (rrPresent == 1) {
                    int len = data.length;
                    while (offset < len) {
                        rrValue = (int) ((data[offset] & 0xFF) + ((data[offset + 1] & 0xFF) << 8));
                        Message newrr = BluetoothMessageHandler.getInstance().getHandler().obtainMessage(MESSAGEIDRRVALUE, rrValue);
                        newrr.sendToTarget();
                        offset += 2;
                        rrs.add(rrValue);
                    }
                }
                Message connectionstate = BluetoothMessageHandler.getInstance().getHandler().obtainMessage(MESSAGEIDCONNECTION, sensorContactFinal);
                connectionstate.sendToTarget();


                if (StationTrackingData.isIsrecording()) {
                    System.out.println("Write to DataBase");
                    StopWatchService stopWatchService = StopWatchService.getInstance();
                    long timestamp = StopWatchService.getStopwatch().getTimeElapsedinSec();
                    appDatabase.sensorDataDao().insertSensorData(new SensorData(timestamp, StationTrackingData.getPersonname(), StationTrackingData.getActualStation(), hrValue, rrValue, sensorContactFinal));
                }


            }
        }

        @Override
        public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            Log.d(TAG, "onDescriptorRead");

            super.onDescriptorRead(gatt, descriptor, status);
        }

        @Override
        public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            Log.d(TAG, "onDescriptorWrtie");

            super.onDescriptorWrite(gatt, descriptor, status);
        }

        @Override
        public void onMtuChanged(BluetoothGatt gatt, int mtu, int status) {
            Log.d(TAG, "onDescriptorChanged");
            super.onMtuChanged(gatt, mtu, status);
        }
    };
    private ScanCallback scanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            super.onScanResult(callbackType, result);
            //processDeviceDiscovered(result.getDevice(),result.getRssi(),result.getScanRecord().getBytes());
        }

        @Override
        public void onScanFailed(int errorCode) {
            super.onScanFailed(errorCode);
        }
    };


    /*private void processDeviceDiscovered(final BluetoothDevice device, int rssi, byte[] scanRecord){
        Map<BluetoothService_Polar.AD_TYPE,byte[]> content = advertisementBytes2Map(scanRecord);
        if( content.containsKey(BluetoothService_Polar.AD_TYPE.GAP_ADTYPE_LOCAL_NAME_COMPLETE) ) {
            String name = new String(content.get(BluetoothService_Polar.AD_TYPE.GAP_ADTYPE_LOCAL_NAME_COMPLETE));
            if (name.startsWith("Polar ")) {
                String names[] = name.split(" ");
                if (names.length > 2) {
                    String deviceId = names[names.length-1];
                    if( deviceId.equals("12345678") ){ // TODO NOTE REPLACE with your device id
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            bluetoothAdapter.getBluetoothLeScanner().stopScan(scanCallback);
                        } else {
                            bluetoothAdapter.stopLeScan(leScanCallback);
                        }
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            device.connectGatt(context,false,bluetoothGattCallback, BluetoothDevice.TRANSPORT_LE);
                        } else {
                            device.connectGatt(context,false,bluetoothGattCallback);
                        }
                    }
                }
            }
        }
    } */



}

