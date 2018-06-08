package at.ac.univie.stefan.fast.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

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
    private final int hrwarningsubtractive = 30;

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
                final int personalmaxhr = personData.getPersonalmaxhr();
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
                        ArrayList<DataPoint> stationonepointarraylist = new ArrayList<DataPoint>();
                        ArrayList<DataPoint> stationtwopointarraylist = new ArrayList<DataPoint>();
                        ArrayList<DataPoint> stationthreepointarraylist = new ArrayList<DataPoint>();
                        ArrayList<DataPoint> stationfourpointarraylist = new ArrayList<DataPoint>();
                        ArrayList<DataPoint> stationfivepointarraylist = new ArrayList<DataPoint>();
                        double stationonemaxtime = 0;
                        double stationtwomaxtime = 0;
                        double stationthreemaxtime = 0;
                        double stationfourmaxtime = 0;
                        double stationfivemaxtime = 0;


                        for (SensorData sensorData : sensorDataList) {
                            switch (sensorData.getStationname()) {
                                case StationTrackingData.STATIONONE:
                                    stationonepointarraylist.add(new DataPoint(sensorData.getTimestamp(), sensorData.getHeartrate()));
                                    if (stationonemaxtime < sensorData.getTimestamp()) {
                                        stationonemaxtime = sensorData.getTimestamp();
                                    }
                                    break;
                                case StationTrackingData.STATIONTWO:
                                    stationtwopointarraylist.add(new DataPoint(sensorData.getTimestamp(), sensorData.getHeartrate()));
                                    if (stationtwomaxtime < sensorData.getTimestamp()) {
                                        stationtwomaxtime = sensorData.getTimestamp();
                                    }
                                    break;
                                case StationTrackingData.STATIONTHREE:
                                    stationthreepointarraylist.add(new DataPoint(sensorData.getTimestamp(), sensorData.getHeartrate()));
                                    if (stationthreemaxtime < sensorData.getTimestamp()) {
                                        stationthreemaxtime = sensorData.getTimestamp();
                                    }
                                    break;
                                case StationTrackingData.STATIONFOUR:
                                    stationfourpointarraylist.add(new DataPoint(sensorData.getTimestamp(), sensorData.getHeartrate()));
                                    if (stationfourmaxtime < sensorData.getTimestamp()) {
                                        stationfourmaxtime = sensorData.getTimestamp();
                                    }
                                    break;
                                case StationTrackingData.STATIONFIVE:
                                    stationfivepointarraylist.add(new DataPoint(sensorData.getTimestamp(), sensorData.getHeartrate()));
                                    if (stationfivemaxtime < sensorData.getTimestamp()) {
                                        stationfivemaxtime = sensorData.getTimestamp();
                                    }
                                    break;
                            }
                        }

                        if (stationonepointarraylist.size() > 2) {
                            try {
                                //Set borders of graph
                                graphviewstationOne.getViewport().setXAxisBoundsManual(true);
                                graphviewstationOne.getViewport().setYAxisBoundsManual(true);
                                graphviewstationOne.getViewport().setMinX(0);
                                graphviewstationOne.getViewport().setMaxX(stationonemaxtime);
                                graphviewstationOne.getViewport().setMinY(0);
                                graphviewstationOne.getViewport().setMaxY(personalmaxhr + graphplotadditive);

                                //HR ThreshholdLine
                                LineGraphSeries<DataPoint> hrtreshhold = new LineGraphSeries<>(new DataPoint[] {
                                        new DataPoint(0,personalmaxhr),
                                        new DataPoint(stationonemaxtime,personalmaxhr),
                                });
                                hrtreshhold.setColor(Color.RED);
                                graphviewstationOne.addSeries(hrtreshhold);

                                //HR ThreshholdLine Warning
                                LineGraphSeries<DataPoint> hrtreshholdwarning = new LineGraphSeries<>(new DataPoint[] {
                                        new DataPoint(0,personalmaxhr - hrwarningsubtractive ),
                                        new DataPoint(stationonemaxtime,personalmaxhr - hrwarningsubtractive),
                                });
                                hrtreshholdwarning.setColor(Color.rgb(255,165,0));
                                graphviewstationOne.addSeries(hrtreshholdwarning);

                                //Plot HR-Data to Graph
                                DataPoint[] points = stationonepointarraylist.toArray(new DataPoint[stationonepointarraylist.size()]);
                                LineGraphSeries<DataPoint> series = new LineGraphSeries<>(points);
                                graphviewstationOne.addSeries(series);



                            } catch (Exception e) {
                                graphviewstationOne.setVisibility(View.GONE);
                                e.printStackTrace();
                            }
                        } else graphviewstationOne.setVisibility(View.GONE);

                        if (stationtwopointarraylist.size() > 2) {
                            try {
                                //Set borders of graph
                                graphviewstationTwo.getViewport().setXAxisBoundsManual(true);
                                graphviewstationTwo.getViewport().setYAxisBoundsManual(true);
                                graphviewstationTwo.getViewport().setMinX(0);
                                graphviewstationTwo.getViewport().setMaxX(stationtwomaxtime);
                                graphviewstationTwo.getViewport().setMinY(0);
                                graphviewstationTwo.getViewport().setMaxY(personalmaxhr + graphplotadditive);

                                //HR ThreshholdLine
                                LineGraphSeries<DataPoint> hrtreshhold = new LineGraphSeries<>(new DataPoint[] {
                                        new DataPoint(0,personalmaxhr),
                                        new DataPoint(stationtwomaxtime,personalmaxhr),
                                });
                                hrtreshhold.setColor(Color.RED);
                                graphviewstationTwo.addSeries(hrtreshhold);

                                //HR ThreshholdLine Warning
                                LineGraphSeries<DataPoint> hrtreshholdwarning = new LineGraphSeries<>(new DataPoint[] {
                                        new DataPoint(0,personalmaxhr - hrwarningsubtractive ),
                                        new DataPoint(stationtwomaxtime,personalmaxhr - hrwarningsubtractive),
                                });
                                hrtreshholdwarning.setColor(Color.rgb(255,165,0));
                                graphviewstationTwo.addSeries(hrtreshholdwarning);

                                //Plot HR-Data to Graph
                                DataPoint[] points = stationtwopointarraylist.toArray(new DataPoint[stationtwopointarraylist.size()]);
                                LineGraphSeries<DataPoint> series = new LineGraphSeries<>(points);
                                graphviewstationTwo.addSeries(series);
                            } catch (Exception e) {
                                graphviewstationTwo.setVisibility(View.GONE);
                                e.printStackTrace();
                            }
                        } else graphviewstationTwo.setVisibility(View.GONE);

                        if (stationthreepointarraylist.size() > 2) {
                            try {
                                //Set borders of graph
                                graphviewstationThree.getViewport().setXAxisBoundsManual(true);
                                graphviewstationThree.getViewport().setYAxisBoundsManual(true);
                                graphviewstationThree.getViewport().setMinX(0);
                                graphviewstationThree.getViewport().setMaxX(stationthreemaxtime);
                                graphviewstationThree.getViewport().setMinY(0);
                                graphviewstationThree.getViewport().setMaxY(personalmaxhr + graphplotadditive);

                                //HR ThreshholdLine
                                LineGraphSeries<DataPoint> hrtreshhold = new LineGraphSeries<>(new DataPoint[] {
                                        new DataPoint(0,personalmaxhr),
                                        new DataPoint(stationthreemaxtime,personalmaxhr),
                                });
                                hrtreshhold.setColor(Color.RED);
                                graphviewstationThree.addSeries(hrtreshhold);

                                //HR ThreshholdLine Warning
                                LineGraphSeries<DataPoint> hrtreshholdwarning = new LineGraphSeries<>(new DataPoint[] {
                                        new DataPoint(0,personalmaxhr - hrwarningsubtractive ),
                                        new DataPoint(stationthreemaxtime,personalmaxhr - hrwarningsubtractive),
                                });
                                hrtreshholdwarning.setColor(Color.rgb(255,165,0));
                                graphviewstationThree.addSeries(hrtreshholdwarning);

                                //Plot HR-Data to Graph
                                DataPoint[] points = stationthreepointarraylist.toArray(new DataPoint[stationthreepointarraylist.size()]);
                                LineGraphSeries<DataPoint> series = new LineGraphSeries<>(points);
                                graphviewstationThree.addSeries(series);
                            } catch (Exception e) {
                                graphviewstationThree.setVisibility(View.GONE);
                                e.printStackTrace();
                            }
                        } else graphviewstationThree.setVisibility(View.GONE);

                        if (stationfourpointarraylist.size() > 2) {
                            try {
                                //Set borders of graph
                                graphviewstationFour.getViewport().setXAxisBoundsManual(true);
                                graphviewstationFour.getViewport().setYAxisBoundsManual(true);
                                graphviewstationFour.getViewport().setMinX(0);
                                graphviewstationFour.getViewport().setMaxX(stationfourmaxtime);
                                graphviewstationFour.getViewport().setMinY(0);
                                graphviewstationFour.getViewport().setMaxY(personalmaxhr + graphplotadditive);

                                //HR ThreshholdLine
                                LineGraphSeries<DataPoint> hrtreshhold = new LineGraphSeries<>(new DataPoint[] {
                                        new DataPoint(0,personalmaxhr),
                                        new DataPoint(stationfourmaxtime,personalmaxhr),
                                });
                                hrtreshhold.setColor(Color.RED);
                                graphviewstationFour.addSeries(hrtreshhold);

                                //HR ThreshholdLine Warning
                                LineGraphSeries<DataPoint> hrtreshholdwarning = new LineGraphSeries<>(new DataPoint[] {
                                        new DataPoint(0,personalmaxhr - hrwarningsubtractive ),
                                        new DataPoint(stationfourmaxtime,personalmaxhr - hrwarningsubtractive),
                                });
                                hrtreshholdwarning.setColor(Color.rgb(255,165,0));
                                graphviewstationFour.addSeries(hrtreshholdwarning);

                                //Plot HR-Data to Graph
                                DataPoint[] points = stationfourpointarraylist.toArray(new DataPoint[stationfourpointarraylist.size()]);
                                LineGraphSeries<DataPoint> series = new LineGraphSeries<>(points);
                                graphviewstationFour.addSeries(series);
                            } catch (Exception e) {
                                graphviewstationFour.setVisibility(View.GONE);
                                e.printStackTrace();
                            }
                        } else graphviewstationFour.setVisibility(View.GONE);

                        if (stationfivepointarraylist.size() > 2) {
                            try {
                                //Set borders of graph
                                graphviewstationFive.getViewport().setXAxisBoundsManual(true);
                                graphviewstationFive.getViewport().setYAxisBoundsManual(true);
                                graphviewstationFive.getViewport().setMinX(0);
                                graphviewstationFive.getViewport().setMaxX(stationfivemaxtime);
                                graphviewstationFive.getViewport().setMinY(0);
                                graphviewstationFive.getViewport().setMaxY(personalmaxhr + graphplotadditive);

                                //HR ThreshholdLine
                                LineGraphSeries<DataPoint> hrtreshhold = new LineGraphSeries<>(new DataPoint[] {
                                        new DataPoint(0,personalmaxhr),
                                        new DataPoint(stationfivemaxtime,personalmaxhr),
                                });
                                hrtreshhold.setColor(Color.RED);
                                graphviewstationFive.addSeries(hrtreshhold);

                                //HR ThreshholdLine Warning
                                LineGraphSeries<DataPoint> hrtreshholdwarning = new LineGraphSeries<>(new DataPoint[] {
                                        new DataPoint(0,personalmaxhr - hrwarningsubtractive ),
                                        new DataPoint(stationfivemaxtime,personalmaxhr - hrwarningsubtractive),
                                });
                                hrtreshholdwarning.setColor(Color.rgb(255,165,0));
                                graphviewstationFive.addSeries(hrtreshholdwarning);

                                //Plot HR-Data to Graph
                                DataPoint[] points = stationfivepointarraylist.toArray(new DataPoint[stationfivepointarraylist.size()]);
                                LineGraphSeries<DataPoint> series = new LineGraphSeries<>(points);
                                graphviewstationFive.addSeries(series);
                            } catch (Exception e) {
                                graphviewstationFive.setVisibility(View.GONE);
                                e.printStackTrace();
                            }
                        } else graphviewstationFive.setVisibility(View.GONE);


                    }
                }.execute();


            }
        }.execute();


    }
}
