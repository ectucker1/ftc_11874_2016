package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.hardware.Bot;

/**
 * Created by Ethan Tucker on 1/5/2017.
 */
public class SensorAutonomousRed extends LinearOpMode {

    Bot bot;

    double gyroOffset = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        bot = new Bot(hardwareMap);
        bot.getGyro().calibrate();
        gyroOffset = -bot.getGyro().getHeading();

        waitForStart();

        bot.getRightMotor().setPower(0.75);
        bot.getLeftMotor().setPower(0.75);
        Thread.sleep(500);

        turnGyro(90);

        bot.getRightMotor().setPower(0.75);
        bot.getLeftMotor().setPower(0.75);
        Thread.sleep(5000);

        turnGyro(0);

        bot.getRightMotor().setPower(0.75);
        bot.getLeftMotor().setPower(0.75);
        while(!onLine());

        turnGyro(90);

        bot.getRightMotor().setPower(0.75);
        bot.getLeftMotor().setPower(0.75);
        //Greater than because light = less distance
        while(bot.getDistance().getLightDetected() > 0.2);
        bot.stopDrive();

        hitBeacon();

        bot.getRightMotor().setPower(-0.75);
        bot.getLeftMotor().setPower(-0.75);
        Thread.sleep(500);

        turnGyro(0);

        bot.getRightMotor().setPower(0.75);
        bot.getLeftMotor().setPower(0.75);
        while(!onLine());

        turnGyro(90);

        bot.getRightMotor().setPower(0.75);
        bot.getLeftMotor().setPower(0.75);
        //Greater than because light = less distance
        while(bot.getDistance().getLightDetected() > 0.2);
        bot.stopDrive();

        bot.getRightMotor().setPower(-0.75);
        bot.getLeftMotor().setPower(-0.75);
        Thread.sleep(500);

        turnGyro(315);

        bot.getRightMotor().setPower(0.75);
        bot.getLeftMotor().setPower(0.75);
        Thread.sleep(5000);
        
        bot.stopAll();
    }

    private void turnGyro(int degrees) {
        bot.getRightMotor().setPower(0.3);
        bot.getLeftMotor().setPower(-0.3);
        while(bot.getGyro().getHeading() + gyroOffset > 2);
        bot.stopDrive();
    }

    private boolean onLine() {
        return (bot.getLineSensor().red() > 200
            && bot.getLineSensor().blue() > 200
            && bot.getLineSensor().green() > 200);
    }

    private void hitBeacon() throws InterruptedException {
        if(bot.getBeaconSensor().red() > bot.getBeaconSensor().blue()) {
            bot.getPusher().setPosition(0.3);
        } else {
            bot.getPusher().setPosition(0.7);
        }
        Thread.sleep(500);
        bot.getPusher().setPosition(0.5);
    }

}
