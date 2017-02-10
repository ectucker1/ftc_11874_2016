package org.firstinspires.ftc.teamcode.math;

/**
 * Created by Ethan Tucker on 1/24/2017.
 */
public class AutonUtil {

    public static boolean onLine(AdvancedBot bot) {
        return bot.lineSensor.getLightDetected() > 0.7;
    }

    public static void driveToBeacon(boolean right) {

    }

    public static void hitBeacon(AdvancedBot bot) throws InterruptedException {
        if (bot.beaconSensor.red() > bot.beaconSensor.blue()) {
            bot.pusherLeft.setPosition(1.0);
        } else {
            bot.pusherRight.setPosition(1.0);
        }
        bot.sleep(500);
        bot.pusherLeft.setPosition(0.0);
        bot.pusherRight.setPosition(0.0);
    }

}
