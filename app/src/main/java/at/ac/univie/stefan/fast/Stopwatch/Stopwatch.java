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
}
