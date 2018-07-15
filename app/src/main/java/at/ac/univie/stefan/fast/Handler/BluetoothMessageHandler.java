package at.ac.univie.stefan.fast.Handler;

import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import at.ac.univie.stefan.fast.StationTracking.StationTrackingData;

import static at.ac.univie.stefan.fast.Activities.ConnectToMonitorActivity.MESSAGEIDCONNECTION;
import static at.ac.univie.stefan.fast.Activities.ConnectToMonitorActivity.MESSAGEIDHRVALUE;
import static at.ac.univie.stefan.fast.Activities.ConnectToMonitorActivity.MESSAGEIDRRVALUE;

/**
 * Created by Stefan on 03.04.2018.
 */

public class BluetoothMessageHandler extends Handler {

    private Handler handler;
    private static BluetoothMessageHandler instance;

    private static TextView hr;
    private static TextView rr;
    private static TextView connectionstate;
    private static boolean textviewidhravailibel;
    private static boolean textviewidrravailible;
    private static boolean textviewidconnctionstateavailibel;

    private BluetoothMessageHandler() {



        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                switch (msg.what) {
                    case MESSAGEIDHRVALUE:
                        if (!textviewidhravailibel) break;
                        int hrvalue = (int) msg.obj;
                        hr.setText(""+hrvalue);
                        if (hrvalue> StationTrackingData.getMaxhr()) {
                            hr.setTextColor(Color.RED);
                        } else if (hrvalue>StationTrackingData.getMaxhr()-20) {
                            hr.setTextColor(Color.rgb(255,165,0));
                        } else hr.setTextColor(Color.DKGRAY);
                        break;
                    case MESSAGEIDRRVALUE:
                        if (!textviewidrravailible) break;
                        int rrvalue = (int) msg.obj;
                        rr.setText(""+rrvalue);
                        break;
                    case MESSAGEIDCONNECTION:
                        if (!textviewidconnctionstateavailibel) break;
                        boolean sensorcontact = (boolean) msg.obj;
                            if (sensorcontact) {
                                connectionstate.setText("verbunden");
                                connectionstate.setTextColor(Color.GREEN);
                            } else {
                                connectionstate.setText("getrennt");
                                connectionstate.setTextColor(Color.RED);
                            }

                }
            }
        };
    }

    public static BluetoothMessageHandler getInstance () {
        if (BluetoothMessageHandler.instance==null) {
            BluetoothMessageHandler.instance = new BluetoothMessageHandler();
        }
        return BluetoothMessageHandler.instance;
    }

    public Handler getHandler () {
        return handler;
    }

    public void setTextViews (View view, int textviewidhr, int textviewidrr, int textviewidconnectionstate) {
        if (textviewidhr==0) {
            textviewidhravailibel=false;
        } else {
            textviewidhravailibel=true;
            hr= (TextView) view.findViewById(textviewidhr);
        }

        if (textviewidhr==0) {
            textviewidrravailible=false;
        } else {
            textviewidrravailible=true;
            rr= (TextView) view.findViewById(textviewidrr);
        }

        if (textviewidconnectionstate==0) {
            textviewidconnctionstateavailibel=false;
        } else {
            textviewidconnctionstateavailibel=true;
            connectionstate= (TextView) view.findViewById(textviewidconnectionstate);

        }

    }

}
