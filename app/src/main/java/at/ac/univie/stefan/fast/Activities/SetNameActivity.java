package at.ac.univie.stefan.fast.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import at.ac.univie.stefan.fast.R;
import at.ac.univie.stefan.fast.StationTracking.StationTrackingData;

/**
 * Created by Stefan on 15.04.2018.
 */

public class SetNameActivity extends AppCompatActivity {

    public static final String BLUETOOTHDEVICENAME = "bluetoothdevicename";

    private TextView textViewselectedbluetoothDevice;
    private EditText editTextName;
    private Button buttonweiter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setname);

        Intent intentrecieved = getIntent();
        String bluetoothdevicename = intentrecieved.getStringExtra(BLUETOOTHDEVICENAME);
        final String macaddressofbluetoothdevice = intentrecieved.getStringExtra(ConnectToMonitorActivity.MACADRESSBLUETOOTHDEVICE);

        textViewselectedbluetoothDevice = (TextView) findViewById(R.id.textViewSetNameselectedBluetoothDevice);
        editTextName = (EditText) findViewById(R.id.editTextName);
        buttonweiter = (Button) findViewById(R.id.buttonSetNameWeiter);

        textViewselectedbluetoothDevice.setText(bluetoothdevicename);
        buttonweiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String personname = editTextName.getText().toString();
                StationTrackingData.setPersonname(personname);
                Intent intent = new Intent(getApplicationContext(),ConnectToMonitorActivity.class);
                intent.putExtra(ConnectToMonitorActivity.MACADRESSBLUETOOTHDEVICE, macaddressofbluetoothdevice);
                startActivity(intent);

            }
        });


    }
}
