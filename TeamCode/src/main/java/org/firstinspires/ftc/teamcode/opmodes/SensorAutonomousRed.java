package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.math.AdvancedBot;
import org.firstinspires.ftc.teamcode.math.AutonUtil;

/**
 * Created by Ethan Tucker on 1/5/2017.
 */
@Autonomous(name = "11874: Sensor Auton Red", group = "Autonomous")
public class SensorAutonomousRed extends LinearOpMode {

    private AdvancedBot bot;

    @Override
    public void runOpMode() throws InterruptedException {
        bot = new AdvancedBot(this);
        bot.setDriveMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        idle();
        bot.calibrateGyro();
        bot.setDriveMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        bot.launchBalls();
        bot.encoderDrive(-0.6);
        bot.encoderTurn(-0.35);
        bot.encoderDrive(-0.6);
        bot.encoderTurn(0.35);
        bot.encoderDrive(-0.37);
        bot.encoderTurn(-0.43);

        bot.sleep(5000);

        while(bot.getDistanceCM() > 5 && opModeIsActive()) {
            bot.lineFollow();
        }
        AutonUtil.hitBeacon(bot);

        bot.encoderDrive(0.1);
        bot.encoderTurn(-0.35);
        bot.encoderDrive(-0.2);
        bot.encoderTurn(0.35);

        while(bot.getDistanceCM() > 5 && opModeIsActive()) {
            bot.lineFollow();
        }
        AutonUtil.hitBeacon(bot);
    }
}
