package at.ac.univie.stefan.fast;

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
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.UUID;

public class ConnectToMonitor extends AppCompatActivity {

    public final UUID HR_MEASUREMENT = UUID.fromString("00002a37-0000-1000-8000-00805f9b34fb");
    public final UUID HR_SERVICE = UUID.fromString("0000180D-0000-1000-8000-00805f9b34fb");
    public static final UUID DESCRIPTOR_CCC = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");

    public static final int MESSAGEIDHRVALUE=1;
    public static final int MESSAGEIDRRVALUE=2;

    public static final String TAG = ConnectToMonitor.class.getSimpleName();

    public static final String MACADRESSBLUETOOTHDEVICE = "MACADRESSE";

    private Context context;
    private BluetoothDevice bluetoothDevice;
    private BluetoothAdapter bluetoothAdapter;
    private TextView textViewhr;
    private TextView textViewrrvalue;
    private Handler uihandler;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_to_monitor);
        context = getApplicationContext();

        Intent intent=getIntent();
        bluetoothAdapter = BluetoothAdapterFactory.getInstance().getBluetoothAdapter();
        bluetoothDevice = bluetoothAdapter.getRemoteDevice(intent.getStringExtra(MACADRESSBLUETOOTHDEVICE));

        textViewhr = (TextView) findViewById(R.id.texthr);
        textViewhr.setText("waiting...");
        textViewrrvalue = (TextView) findViewById(R.id.textrrvalue);
        uihandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                int value = (int) msg.obj;
                switch (msg.what) {
                    case MESSAGEIDHRVALUE:
                        textViewhr.setText(""+value);
                        break;
                    case MESSAGEIDRRVALUE:
                        textViewrrvalue.setText(""+value);
                        break;
                }

            }
        };


        BluetoothGatt bluetoothGatt = bluetoothDevice.connectGatt(context, false, bluetoothGattCallback );
        Log.d(TAG,"connection established");









    }

    private  BluetoothAdapter.LeScanCallback leScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
           // processDeviceDiscovered(device,rssi,scanRecord);
        }
    };

    private BluetoothGattCallback bluetoothGattCallback = new BluetoothGattCallback() {

        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            Log.d(TAG,"onConnectionStateChange");
            Log.d(TAG,"Status"+status+"newState"+newState+"");
            super.onConnectionStateChange(gatt, status, newState);
            if (newState == BluetoothGatt.STATE_CONNECTED && status == BluetoothGatt.GATT_SUCCESS) {
                gatt.discoverServices();
            } else if(newState == BluetoothGatt.STATE_DISCONNECTED){
                Log.e(TAG,"disconnected from Belt");
                gatt.connect();
            }
        }

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            Log.d(TAG,"onServiceDiscovered");
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
            Log.d(TAG,"onCharacteristicRead");
            super.onCharacteristicRead(gatt, characteristic, status);
        }

        @Override
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            Log.d(TAG,"onCharacteristicWrite");
            super.onCharacteristicWrite(gatt, characteristic, status);
        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            Log.d(TAG,"charatersiticchanged");
            super.onCharacteristicChanged(gatt, characteristic);
            if (characteristic.getUuid().equals(HR_MEASUREMENT)) {
                byte[] data = characteristic.getValue();
                int hrFormat = data[0] & 0x01;
                System.out.println("HR Format: "+hrFormat);
                boolean sensorContact = true;
                final boolean contactSupported = !((data[0] & 0x06) == 0);
                if( contactSupported ) {
                    sensorContact = ((data[0] & 0x06) >> 1) == 3;
                    System.out.println("SensorContact: "+sensorContact);
                }
                int energyExpended = (data[0] & 0x08) >> 3;
                System.out.println("EnergyExpended: "+energyExpended);
                int rrPresent = (data[0] & 0x10) >> 4;
                System.out.println("RRPresent: "+rrPresent);
                final int hrValue = (hrFormat == 1 ? data[1] + (data[2] << 8) : data[1]) & (hrFormat == 1 ? 0x0000FFFF : 0x000000FF);
                Message newhrv = uihandler.obtainMessage(MESSAGEIDHRVALUE,hrValue);
                newhrv.sendToTarget();

                if( !contactSupported && hrValue == 0 ){
                    // note does this apply to all sensors, also 3rd party
                    sensorContact = false;
                }
                final boolean sensorContactFinal = sensorContact;
                System.out.println("SensorContactFinal: "+sensorContactFinal);
                int offset = hrFormat + 2;
                int energy = 0;
                if (energyExpended == 1) {
                    energy = (data[offset] & 0xFF) + ((data[offset + 1] & 0xFF) << 8);
                    offset += 2;
                }
                System.out.println("Energy: "+energy);
                final ArrayList<Integer> rrs = new ArrayList<>();
                if (rrPresent == 1) {
                    int len = data.length;
                    while (offset < len) {
                        int rrValue = (int) ((data[offset] & 0xFF) + ((data[offset + 1] & 0xFF) << 8));
                        Message newrr = uihandler.obtainMessage(MESSAGEIDRRVALUE,rrValue);
                        newrr.sendToTarget();
                        offset += 2;
                        rrs.add(rrValue);
                    }
                }
                System.out.println();
            }
        }

        @Override
        public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            Log.d(TAG,"onDescriptorRead");

            super.onDescriptorRead(gatt, descriptor, status);
        }

        @Override
        public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            Log.d(TAG,"onDescriptorWrtie");

            super.onDescriptorWrite(gatt, descriptor, status);
        }

        @Override
        public void onMtuChanged(BluetoothGatt gatt, int mtu, int status) {
            Log.d(TAG,"onDescriptorChanged");
            super.onMtuChanged(gatt, mtu, status);
        }
    };


    /*private void processDeviceDiscovered(final BluetoothDevice device, int rssi, byte[] scanRecord){
        Map<BluetoothService.AD_TYPE,byte[]> content = advertisementBytes2Map(scanRecord);
        if( content.containsKey(BluetoothService.AD_TYPE.GAP_ADTYPE_LOCAL_NAME_COMPLETE) ) {
            String name = new String(content.get(BluetoothService.AD_TYPE.GAP_ADTYPE_LOCAL_NAME_COMPLETE));
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
}

