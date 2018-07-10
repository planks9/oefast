package at.ac.univie.stefan.fast;

/**
 * Created by Stefan on 10.07.2018.
 */

public class StressCalculator {
    public static final int stresslevellow=0;
    public static final int stresslevelmedium=1;
    public static final int stresslevelhigh=2;

    public int calculateStress(double sdnn, double rmssd) {
        int stresslevel = stresslevelmedium;
        if ((sdnn < 50) && (rmssd<35)) {
            stresslevel = stresslevelhigh;
        }
        if ((sdnn>100) && (rmssd>75)) {
            stresslevel = stresslevellow;
        }

        return stresslevel;

    }
}
