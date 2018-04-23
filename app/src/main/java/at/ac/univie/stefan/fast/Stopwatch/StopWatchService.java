package at.ac.univie.stefan.fast.Stopwatch;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

/**
 * Created by Stefan on 09.04.2018.
 */

public class StopWatchService extends Handler {

    public static final int MSG_START_TIMER = 0;
    public static final int MSG_STOP_TIMER = 1;
    public static final int MSG_UPDATE_TIMER = 2;
    public static final int REFRESH_RATE_OF_UI_WATCH=500;
    private static Stopwatch stopwatch;
    private TextView textViewtimedisplay;
    private static StopWatchService instance;

    private StopWatchService () {
        stopwatch = new Stopwatch();
    }

    public static StopWatchService getInstance () {
        if (instance==null) {
            instance = new StopWatchService();
        }

        return instance;
    }

    public static Stopwatch getStopwatch () {
        return stopwatch;
    }

    public void setTextViewtimedisplay(TextView textViewtimedisplay) {
        this.textViewtimedisplay = textViewtimedisplay;
    }




    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what) {
            case MSG_START_TIMER:
                stopwatch.start(); //start timer
                this.sendEmptyMessage(MSG_UPDATE_TIMER);
                break;

            case MSG_UPDATE_TIMER: {
                textViewtimedisplay.setText(stopwatch.getTimeinString());

                this.sendEmptyMessageDelayed(MSG_UPDATE_TIMER, REFRESH_RATE_OF_UI_WATCH);
            }
            break;                                  //though the timer is still running
            case MSG_STOP_TIMER: {
                this.removeMessages(MSG_UPDATE_TIMER); // no more updates.
                stopwatch.stop();//stop timer
                long timeelapsedinsec = stopwatch.getTimeElapsedinSec();
                int mins = (int) timeelapsedinsec / 60;
                int secs = (int) timeelapsedinsec - mins * 60;
                if ((mins<10) && (secs<10)) {
                    textViewtimedisplay.setText("0" + mins + ":0" + secs);
                }
                else if ((mins<10) && (secs>9)) {
                    textViewtimedisplay.setText("0" + mins + ":" + secs);
                }
                else if ((mins>9) && (secs<10)) {
                    textViewtimedisplay.setText(mins + ":0" + secs);
                }
                else if ((mins>9) && (secs>9)) {
                    textViewtimedisplay.setText(mins + ":" + secs);
                }
            }
            break;

            default:
                break;
        }
    }

    public void stopTimer () {
        this.sendEmptyMessage(MSG_STOP_TIMER);
    }

    public void startTimer () {
        this.sendEmptyMessage(MSG_START_TIMER);
    }


}
