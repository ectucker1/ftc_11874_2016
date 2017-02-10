package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.math.AdvancedBot;
import org.firstinspires.ftc.teamcode.math.AutonUtil;

/**
 * Created by Ethan Tucker on 1/5/2017.
 */
@Autonomous(name = "11874: Sensor Auton Blue", group = "Autonomous")
public class SensorAutonomousBlue extends LinearOpMode {

    private AdvancedBot bot;

    @Override
    public void runOpMode() throws InterruptedException {
        bot = new AdvancedBot(this);
        bot.setDriveMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        idle();
        //bot.calibrateGyro();
        bot.setDriveMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        bot.launchBalls();
        bot.sleep(500);

        bot.encoderDrive(0.9);
        bot.turnGyro(-90);
        bot.encoderDrive(1.2);
        bot.turnGyro(90);
        //bot.driveOn();
        //while(!onLine() && opModeIsActive());
        //bot.turnGyro(90);
        //bot.driveOn();
        //Greater than because light = less distance
        //bot.encoderDrive(-0.1);
        //bot.stopDrive();
        /*hitBeacon();
        bot.encoderDrive(-2);
        bot.turnGyro(0);
        bot.driveOn();
        while(!onLine() && opModeIsActive());
        bot.turnGyro(90);
        bot.driveOn();
        //Greater than because light = less distance
        while(bot.distance.getLightDetected() > 0.2 && opModeIsActive());
        bot.stopDrive();
        bot.encoderDrive(2);
        bot.turnGyro(315);
        bot.encoderDrive(-20);

        bot.stopAll();*/
    }
}
