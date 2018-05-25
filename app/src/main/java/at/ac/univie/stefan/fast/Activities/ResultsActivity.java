package at.ac.univie.stefan.fast.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import at.ac.univie.stefan.fast.DataBase.DataBaseCreator;
import at.ac.univie.stefan.fast.DataBase.PersonData;
import at.ac.univie.stefan.fast.R;

/**
 * Created by Stefan on 25.05.2018.
 */

public class ResultsActivity extends AppCompatActivity {

    public static final String PRIMARYKEYFORPERSON="primarykey";

    private TextView textViewResultsPersonName;
    private TextView textViewResultsStationOneZeit;
    private TextView textViewResultsStationOneMaxHR;
    private TextView textViewResultsStationOneAvgHR;
    private TextView textViewResultsStationTwoZeit;
    private TextView textViewResultsStationTwoMaxHR;
    private TextView textViewResultsStationTwoAvgHR;
    private TextView textViewResultsStationThreeZeit;
    private TextView textViewResultsStationThreeMaxHR;
    private TextView textViewResultsStationThreeAvgHR;
    private TextView textViewResultsStationFourZeit;
    private TextView textViewResultsStationFourMaxHR;
    private TextView textViewResultsStationFourAvgHR;
    private TextView textViewResultsStationFiveZeit;
    private TextView textViewResultsStationFiveMaxHR;
    private TextView textViewResultsStationFiveAvgHR;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);
        Intent intent = getIntent();
        final long primarykey = intent.getLongExtra(PRIMARYKEYFORPERSON,0);

        textViewResultsPersonName= findViewById(R.id.textViewResultsPersonName);
        textViewResultsStationOneZeit = findViewById(R.id.textViewResultsStationOneZeit);
        textViewResultsStationOneMaxHR = findViewById(R.id.textViewResultsStationOneMaxHR);
        textViewResultsStationOneAvgHR = findViewById(R.id.textViewResultsStationOneAvgHR);
        textViewResultsStationTwoZeit = findViewById(R.id.textViewResultsStationTwoZeit);
        textViewResultsStationTwoMaxHR = findViewById(R.id.textViewResultsStationTwoMaxHR);
        textViewResultsStationTwoAvgHR = findViewById(R.id.textViewResultsStationTwoAvgHR);
        textViewResultsStationThreeZeit = findViewById(R.id.textViewResultsStationThreeZeit);
        textViewResultsStationThreeMaxHR = findViewById(R.id.textViewResultsStationThreeMaxHR);
        textViewResultsStationThreeAvgHR = findViewById(R.id.textViewResultsStationThreeAvgHR);
        textViewResultsStationFourZeit = findViewById(R.id.textViewResultsStationFourZeit);
        textViewResultsStationFourMaxHR = findViewById(R.id.textViewResultsStationFourMaxHR);
        textViewResultsStationFourAvgHR = findViewById(R.id.textViewResultsStationFourAvgHR);
        textViewResultsStationFiveZeit = findViewById(R.id.textViewResultsStationFiveZeit);
        textViewResultsStationFiveMaxHR = findViewById(R.id.textViewResultsStationFiveMaxHR);
        textViewResultsStationFiveAvgHR = findViewById(R.id.textViewResultsStationFiveAvgHR);

        new AsyncTask<Void, Void, PersonData>() {
            @Override
            protected PersonData doInBackground(Void... voids) {
                return DataBaseCreator.getAppDatabasePersonData().personDataDao().getPersonbyID(primarykey);
            }

            @Override
            protected void onPostExecute(PersonData personData) {
                textViewResultsPersonName.setText(personData.getPersonname());
                textViewResultsStationOneZeit.setText(personData.getStationonetime());
                textViewResultsStationOneMaxHR.setText(personData.getStationonemaxhr()+"");
                textViewResultsStationOneAvgHR.setText(personData.getStationoneavhr()+"");
                textViewResultsStationTwoZeit.setText(personData.getStationtwotime());
                textViewResultsStationTwoMaxHR.setText(personData.getStationtwomaxhr()+"");
                textViewResultsStationTwoAvgHR.setText(personData.getStationtwoavhr()+"");
                textViewResultsStationThreeZeit.setText(personData.getStationthreetime());
                textViewResultsStationThreeMaxHR.setText(personData.getStationthreemaxhr()+"");
                textViewResultsStationThreeAvgHR.setText(personData.getStationthreeavhr()+"");
                textViewResultsStationFourZeit.setText(personData.getStationfourtime());
                textViewResultsStationFourMaxHR.setText(personData.getStationfourmaxhr()+"");
                textViewResultsStationFourAvgHR.setText(personData.getStationfouravhr()+"");
                textViewResultsStationFiveZeit.setText(personData.getStationfivetime());
                textViewResultsStationFiveMaxHR.setText(personData.getStationfivemaxhr()+"");
                textViewResultsStationFiveAvgHR.setText(personData.getStationfiveavhr()+"");


            }
        }.execute();


    }
}
