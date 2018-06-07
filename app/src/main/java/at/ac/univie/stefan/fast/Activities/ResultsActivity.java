package at.ac.univie.stefan.fast.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/*import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries; */

import com.softmoore.android.graphlib.Graph;
import com.softmoore.android.graphlib.GraphView;
import com.softmoore.android.graphlib.Point;

import java.util.ArrayList;
import java.util.List;

import at.ac.univie.stefan.fast.DataBase.DataBaseCreator;
import at.ac.univie.stefan.fast.DataBase.PersonData;
import at.ac.univie.stefan.fast.DataBase.SensorData;
import at.ac.univie.stefan.fast.R;
import at.ac.univie.stefan.fast.StationTracking.StationTrackingData;

/**
 * Created by Stefan on 25.05.2018.
 */

public class ResultsActivity extends AppCompatActivity {

    public static final String PRIMARYKEYFORPERSON = "primarykey";
    private final int graphplotadditive = 20;

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
    private GraphView graphviewstationOne;
    private GraphView graphviewstationTwo;
    private GraphView graphviewstationThree;
    private GraphView graphviewstationFour;
    private GraphView graphviewstationFive;

    private Button buttonResultsback;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);
        Intent intent = getIntent();
        final long primarykey = intent.getLongExtra(PRIMARYKEYFORPERSON, 0);

        textViewResultsPersonName = findViewById(R.id.textViewResultsPersonName);
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
        graphviewstationOne = findViewById(R.id.graphstationOne);
        graphviewstationTwo = findViewById(R.id.graphstationTwo);
        graphviewstationThree = findViewById(R.id.graphstationThree);
        graphviewstationFour = findViewById(R.id.graphstationFour);
        graphviewstationFive = findViewById(R.id.graphstationFive);
        buttonResultsback = (Button) findViewById(R.id.buttonResultsback);
        //To get back to previouse activity
        buttonResultsback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        new AsyncTask<Void, Void, PersonData>() {
            @Override
            protected PersonData doInBackground(Void... voids) {
                return DataBaseCreator.getAppDatabasePersonData().personDataDao().getPersonbyID(primarykey);
            }

            @Override
            protected void onPostExecute(PersonData personData) {
                final String personname = personData.getPersonname();
                final int stationonemaxhr = personData.getStationonemaxhr();
                final int stationtwomaxhr = personData.getStationtwomaxhr();
                final int stationthreemaxhr = personData.getStationthreemaxhr();
                final int stationfourmaxhr = personData.getStationfourmaxhr();
                final int stationfivemaxhr = personData.getStationfivemaxhr();
                textViewResultsPersonName.setText(personData.getPersonname());
                textViewResultsStationOneZeit.setText(personData.getStationonetime());
                textViewResultsStationOneMaxHR.setText(personData.getStationonemaxhr() + "");
                textViewResultsStationOneAvgHR.setText(personData.getStationoneavhr() + "");
                textViewResultsStationTwoZeit.setText(personData.getStationtwotime());
                textViewResultsStationTwoMaxHR.setText(personData.getStationtwomaxhr() + "");
                textViewResultsStationTwoAvgHR.setText(personData.getStationtwoavhr() + "");
                textViewResultsStationThreeZeit.setText(personData.getStationthreetime());
                textViewResultsStationThreeMaxHR.setText(personData.getStationthreemaxhr() + "");
                textViewResultsStationThreeAvgHR.setText(personData.getStationthreeavhr() + "");
                textViewResultsStationFourZeit.setText(personData.getStationfourtime());
                textViewResultsStationFourMaxHR.setText(personData.getStationfourmaxhr() + "");
                textViewResultsStationFourAvgHR.setText(personData.getStationfouravhr() + "");
                textViewResultsStationFiveZeit.setText(personData.getStationfivetime());
                textViewResultsStationFiveMaxHR.setText(personData.getStationfivemaxhr() + "");
                textViewResultsStationFiveAvgHR.setText(personData.getStationfiveavhr() + "");

                new AsyncTask<Void, Void, List<SensorData>>() {
                    @Override
                    protected List<SensorData> doInBackground(Void... voids) {
                        return DataBaseCreator.getDataBase().sensorDataDao().findbyPersonName(personname);
                    }

                    @Override
                    protected void onPostExecute(List<SensorData> sensorDataList) {
                        ArrayList<Point> stationonepointarraylist = new ArrayList<Point>();
                        ArrayList<Point> stationtwopointarraylist = new ArrayList<Point>();
                        ArrayList<Point> stationthreepointarraylist = new ArrayList<Point>();
                        ArrayList<Point> stationfourpointarraylist = new ArrayList<Point>();
                        ArrayList<Point> stationfivepointarraylist = new ArrayList<Point>();
                        double stationonemaxtime = 0;
                        double stationtwomaxtime = 0;
                        double stationthreemaxtime = 0;
                        double stationfourmaxtime = 0;
                        double stationfivemaxtime = 0;


                        for (SensorData sensorData : sensorDataList) {
                            switch (sensorData.getStationname()) {
                                case StationTrackingData.STATIONONE:
                                    stationonepointarraylist.add(new Point(sensorData.getTimestamp(), sensorData.getHeartrate()));
                                    if (stationonemaxtime < sensorData.getTimestamp()) {
                                        stationonemaxtime = sensorData.getTimestamp();
                                    }
                                    break;
                                case StationTrackingData.STATIONTWO:
                                    stationtwopointarraylist.add(new Point(sensorData.getTimestamp(), sensorData.getHeartrate()));
                                    if (stationtwomaxtime < sensorData.getTimestamp()) {
                                        stationtwomaxtime = sensorData.getTimestamp();
                                    }
                                    break;
                                case StationTrackingData.STATIONTHREE:
                                    stationthreepointarraylist.add(new Point(sensorData.getTimestamp(), sensorData.getHeartrate()));
                                    if (stationthreemaxtime < sensorData.getTimestamp()) {
                                        stationthreemaxtime = sensorData.getTimestamp();
                                    }
                                    break;
                                case StationTrackingData.STATIONFOUR:
                                    stationfourpointarraylist.add(new Point(sensorData.getTimestamp(), sensorData.getHeartrate()));
                                    if (stationfourmaxtime < sensorData.getTimestamp()) {
                                        stationfourmaxtime = sensorData.getTimestamp();
                                    }
                                    break;
                                case StationTrackingData.STATIONFIVE:
                                    stationfivepointarraylist.add(new Point(sensorData.getTimestamp(), sensorData.getHeartrate()));
                                    if (stationfivemaxtime < sensorData.getTimestamp()) {
                                        stationfivemaxtime = sensorData.getTimestamp();
                                    }
                                    break;
                            }
                        }

                        if (stationonepointarraylist.size() > 2) {
                            Point [] points = new Point[stationonepointarraylist.size()];
                            points = (Point[]) stationonepointarraylist.toArray();
                            Graph graph = new Graph.Builder()
                                    .setWorldCoordinates(0,stationonemaxtime + graphplotadditive, 0, stationonemaxhr + graphplotadditive)
                                    .addLineGraph(points)
                                    .build();
                            graphviewstationOne.setGraph(graph);
                        } else graphviewstationOne.setVisibility(View.GONE);

                        if (stationtwopointarraylist.size() > 2) {
                            Point [] points = new Point[stationtwopointarraylist.size()];
                            points = (Point[]) stationtwopointarraylist.toArray();
                            Graph graph = new Graph.Builder()
                                    .setWorldCoordinates(0,stationtwomaxtime + graphplotadditive, 0, stationtwomaxhr + graphplotadditive)
                                    .addLineGraph(points)
                                    .build();
                            graphviewstationTwo.setGraph(graph);
                        } else graphviewstationTwo.setVisibility(View.GONE);

                        if (stationthreepointarraylist.size() > 2) {
                            Point [] points = new Point[stationthreepointarraylist.size()];
                            points = (Point[]) stationthreepointarraylist.toArray();
                            Graph graph = new Graph.Builder()
                                    .setWorldCoordinates(0,stationthreemaxtime + graphplotadditive, 0, stationthreemaxhr + graphplotadditive)
                                    .addLineGraph(points)
                                    .build();
                            graphviewstationThree.setGraph(graph);
                        } else graphviewstationThree.setVisibility(View.GONE);

                        if (stationfourpointarraylist.size() > 2) {
                            Point [] points = new Point[stationfourpointarraylist.size()];
                            points = (Point[]) stationfourpointarraylist.toArray();
                            Graph graph = new Graph.Builder()
                                    .setWorldCoordinates(0,stationfourmaxtime + graphplotadditive, 0, stationfourmaxhr + graphplotadditive)
                                    .addLineGraph(points)
                                    .build();
                            graphviewstationFour.setGraph(graph);
                        } else graphviewstationFour.setVisibility(View.GONE);

                        if (stationfivepointarraylist.size() > 2) {
                            Point [] points = new Point[stationfivepointarraylist.size()];
                            points = (Point[]) stationfivepointarraylist.toArray();
                            Graph graph = new Graph.Builder()
                                    .setWorldCoordinates(0,stationfivemaxtime + graphplotadditive, 0, stationfivemaxhr + graphplotadditive)
                                    .addLineGraph(points)
                                    .build();
                            graphviewstationFive.setGraph(graph);
                        } else graphviewstationFive.setVisibility(View.GONE);


                    }
                }.execute();


            }
        }.execute();


    }
}
