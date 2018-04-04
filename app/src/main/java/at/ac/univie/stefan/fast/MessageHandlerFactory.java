package at.ac.univie.stefan.fast;

import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import static at.ac.univie.stefan.fast.ConnectToMonitor.MESSAGEIDCONNECTION;
import static at.ac.univie.stefan.fast.ConnectToMonitor.MESSAGEIDHRVALUE;
import static at.ac.univie.stefan.fast.ConnectToMonitor.MESSAGEIDRRVALUE;

/**
 * Created by Stefan on 03.04.2018.
 */

public class MessageHandlerFactory extends Handler {

    private Handler handler;
    private static MessageHandlerFactory instance;

    private static TextView hr;
    private static TextView rr;
    private static TextView connectionstate;
    private static boolean textviewidhravailibel;
    private static boolean textviewidrravailible;
    private static boolean textviewidconnctionstateavailibel;

    private MessageHandlerFactory () {



        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                switch (msg.what) {
                    case MESSAGEIDHRVALUE:
                        if (!textviewidhravailibel) break;
                        int hrvalue = (int) msg.obj;
                        System.out.println(hrvalue);
                        hr.setText(""+hrvalue);
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

    public static MessageHandlerFactory getInstance () {
        if (MessageHandlerFactory.instance==null) {
            System.out.println("new Handler created");
            MessageHandlerFactory.instance = new MessageHandlerFactory();
        }
        return MessageHandlerFactory.instance;
    }

    public Handler getHandler () {
        return handler;
    }

    public Handler getHandlerandsetViews (View view, int textviewidhr, int textviewidrr, int textviewidconnectionstate) {
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


        return handler;

    }

}
