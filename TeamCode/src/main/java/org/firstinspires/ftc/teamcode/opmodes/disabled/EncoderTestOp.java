package org.firstinspires.ftc.teamcode.opmodes.disabled;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.math.AdvancedBot;

/**
 * Created by Ethan Tucker on 1/10/2017.
 */
@Autonomous(name = "11874: Encoder Test Op", group = "11874")
@Disabled
public class EncoderTestOp extends LinearOpMode {

    AdvancedBot bot;

    @Override
    public void runOpMode() throws InterruptedException {
        bot = new AdvancedBot(this);
        bot.setDriveMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        idle();
        bot.setDriveMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bot.calibrateGyro();

        waitForStart();

        bot.encoderDrive(1);
        bot.turnGyro(90);
        bot.encoderDrive(1);
        bot.stopDrive();
    }

}