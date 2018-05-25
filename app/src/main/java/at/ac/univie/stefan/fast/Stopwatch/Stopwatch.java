package at.ac.univie.stefan.fast.Stopwatch;

/**
 * Created by Stefan on 04.04.2018.
 */

public class Stopwatch {
    private double startTime;
    private double stopTime;
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

    public double getTimeElapsedinSec () {
        if (isRunning) {
            return (System.currentTimeMillis() - startTime) / 1000;
        }
        return (stopTime-startTime) / 1000;
    }

    public double getTimeElapsedinMilSec () {
        if (isRunning) {
            return System.currentTimeMillis() - startTime;
        }
        return stopTime - startTime;
    }

    public String getTimeinString () {
        double timeelapsedinsec = getTimeElapsedinSec();
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
