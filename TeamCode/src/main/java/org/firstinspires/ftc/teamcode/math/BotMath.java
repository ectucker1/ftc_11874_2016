package org.firstinspires.ftc.teamcode.math;

/**
 * Created by Ethan Tucker on 11/1/2016.
 */
public class BotMath {

    /**
     * Curves a value with a cubic equation
     * Intended to be used with controller input
     *
     * @param x The value to curve.
     * @return The curved value.
     */
    public static double powerCurve(double x) {
        if(Math.abs(x) < 0.1) {
            return 0;
        }
        return (0.598958 * Math.pow(x, 3)) - (4.43184 * Math.pow(10, -16) * Math.pow(x, 2)) + (0.201042 * x);
    }

}
