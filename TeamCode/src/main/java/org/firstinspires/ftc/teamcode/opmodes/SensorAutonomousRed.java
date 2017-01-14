package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.hardware.Bot;

/**
 * Created by Ethan Tucker on 1/5/2017.
 */
@Autonomous(name="11874: Sensor Auton Red", group="Autonomous")
public class SensorAutonomousRed extends LinearOpMode {

    private Bot bot;

    @Override
    public void runOpMode() throws InterruptedException {
        bot = new Bot(hardwareMap);
        bot.setDriveMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        idle();
        bot.calibrateGyro();
        bot.setDriveMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        throwBalls();

        bot.encoderDrive(2);
        bot.turnGyro(90);
        bot.encoderDrive(20);
        bot.turnGyro(0);
        bot.driveOn();
        while(!onLine());
        bot.turnGyro(90);
        bot.driveOn();
        //Greater than because light = less distance
        while(bot.distance.getLightDetected() > 0.2);
        bot.stopDrive();
        hitBeacon();
        bot.encoderDrive(-2);
        bot.turnGyro(0);
        bot.driveOn();
        while(!onLine());
        bot.turnGyro(90);
        bot.driveOn();
        //Greater than because light = less distance
        while(bot.distance.getLightDetected() > 0.2);
        bot.stopDrive();
        bot.encoderDrive(-2);
        bot.turnGyro(315);
        bot.encoderDrive(20);

        bot.stopAll();
    }

    private boolean onLine() {
        return (bot.lineSensor.red() > 200
            && bot.lineSensor.blue() > 200
            && bot.lineSensor.green() > 200);
    }

    private void hitBeacon() throws InterruptedException {
        if(bot.beaconSensor.red() > bot.beaconSensor.blue()) {
            //TODO New beacon pusher
        } else {

        }
        bot.sleep(500);
    }

    private void throwBalls() {

    }

}
