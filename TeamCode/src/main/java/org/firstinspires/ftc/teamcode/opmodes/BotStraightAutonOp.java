package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.math.AdvancedBot;

/**
 * Created by Ethan Tucker on 11/3/2016.
 */
@Autonomous(name = "11874: Straight Autonomous", group = "11874")
public class BotStraightAutonOp extends LinearOpMode {

    AdvancedBot bot;

    @Override
    public void runOpMode() throws InterruptedException {
        bot = new AdvancedBot(this);
        bot.setDriveMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        idle();
        //bot.calibrateGyro();
        bot.setDriveMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        bot.encoderDrive(0.1);
        bot.thrower.setPosition(0.0);
        bot.sleep(500);

        bot.turnGyro(13);
        bot.encoderDrive(0.9);

        bot.sleep(500);
        bot.encoderDrive(0.1);
    }

}
