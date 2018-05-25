package at.ac.univie.stefan.fast.Stopwatch;

import android.graphics.Color;
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
    public static final int MSG_TimeLimitisNEAR = 3;
    public static final int MSG_TimeOver = 4;
    public static final int MSG_RESET_Color = 5;
    public static final int REFRESH_RATE_OF_UI_WATCH=500;

    private static Stopwatch stopwatch;
    private TextView textViewtimedisplay;
    private static StopWatchService instance;


    private double timelimit=1000;

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
                if (stopwatch.getTimeElapsedinSec()>timelimit) this.sendEmptyMessage(MSG_TimeOver);
                else if (stopwatch.getTimeElapsedinSec()>timelimit - 30) this.sendEmptyMessage(MSG_TimeLimitisNEAR);

                this.sendEmptyMessageDelayed(MSG_UPDATE_TIMER, REFRESH_RATE_OF_UI_WATCH);
            }
            break;                                  //though the timer is still running
            case MSG_STOP_TIMER: {
                this.removeMessages(MSG_UPDATE_TIMER); // no more updates.
                stopwatch.stop();//stop timer
                double timeelapsedinsec = stopwatch.getTimeElapsedinSec();
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

            case MSG_TimeLimitisNEAR: {
                textViewtimedisplay.setTextColor(Color.rgb(255,165,0));
                break;
            }

            case MSG_TimeOver: {
                textViewtimedisplay.setTextColor(Color.RED);
                break;
            }

            case MSG_RESET_Color: {
                textViewtimedisplay.setTextColor(Color.BLACK);
            }

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

    public void setTimelimit(String timelimitinMinutes) {
        double timelimitinminutes = Double.parseDouble(timelimitinMinutes);
        this.timelimit = (long) (timelimitinminutes * 60);
        System.out.println(this.timelimit);
    }


}
