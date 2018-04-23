package at.ac.univie.stefan.fast.Stopwatch;

/**
 * Created by Stefan on 04.04.2018.
 */

public class Stopwatch {
    private long startTime;
    private long stopTime;
    private boolean isRunning;

    public Stopwatch () {
        startTime=0;
        stopTime=0;
        isRunning=false;
    }

    public void start() {
        startTime = System.currentTimeMillis();
        isRunning = true;
    }

    public void stop () {
        stopTime = System.currentTimeMillis();
        isRunning = false;
    }

    public long getTimeElapsedinSec () {
        if (isRunning) {
            return (System.currentTimeMillis() - startTime) / 1000;
        }
        return (stopTime-startTime) / 1000;
    }

    public long getTimeElapsedinMilSec () {
        if (isRunning) {
            return System.currentTimeMillis() - startTime;
        }
        return stopTime - startTime;
    }

    public String getTimeinString () {
        long timeelapsedinsec = getTimeElapsedinSec();
        int mins = (int) timeelapsedinsec / 60;
        int secs = (int) timeelapsedinsec - mins * 60;
        String back = "";
        if ((mins<10) && (secs<10)) {
            back="0" + mins + ":0" + secs;
        }
        else if ((mins<10) && (secs>9)) {
            back="0" + mins + ":" + secs;
        }
        else if ((mins>9) && (secs<10)) {
            back=mins + ":0" + secs;
        }
        else if ((mins>9) && (secs>9)) {
            back=mins + ":" + secs;
        }

        return back;
    }
}
