package at.ac.univie.stefan.fast.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import at.ac.univie.stefan.fast.DataBase.AppDatabasePersonData;
import at.ac.univie.stefan.fast.DataBase.DataBaseCreator;
import at.ac.univie.stefan.fast.DataBase.PersonData;
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
    private Spinner spinnerage;

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
        spinnerage = (Spinner) findViewById(R.id.spinnerage);

        textViewselectedbluetoothDevice.setText(bluetoothdevicename);
        ArrayList <String> spinneritems = new ArrayList<String>();
        for (int i=16;i<71;i++) {
            spinneritems.add(i+"");
        }

        ArrayAdapter<String> arrayAdapterspinner = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,spinneritems);
        spinnerage.setAdapter(arrayAdapterspinner);

        buttonweiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String personname = editTextName.getText().toString();
                StationTrackingData.setPersonname(personname);
                int age = Integer.parseInt(spinnerage.getSelectedItem().toString());
                //207-0.7*age
                double maxhrindouble = 150-0.7*age;
                StationTrackingData.setMaxhr(((int) maxhrindouble));


                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        PersonData personData = new PersonData(personname);
                        DataBaseCreator.createnewDataBasePersonData(getApplicationContext());
                        AppDatabasePersonData appDatabasePersonData = DataBaseCreator.getAppDatabasePersonData();
                        long primarykey=appDatabasePersonData.personDataDao().insertPersonData(personData);
                        StationTrackingData.setPrimarykeypersondata(primarykey);
                        return null;
                    }
                }.execute();

                Intent intent = new Intent(getApplicationContext(),ConnectToMonitorActivity.class);
                intent.putExtra(ConnectToMonitorActivity.MACADRESSBLUETOOTHDEVICE, macaddressofbluetoothdevice);
                startActivity(intent);

            }
        });


    }
}
