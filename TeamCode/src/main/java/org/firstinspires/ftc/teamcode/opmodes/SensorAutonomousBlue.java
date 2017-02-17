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
        bot.calibrateGyro();
        bot.setDriveMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
    }
}
