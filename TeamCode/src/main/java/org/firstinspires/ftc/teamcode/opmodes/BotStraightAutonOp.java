package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.hardware.Bot;
import org.firstinspires.ftc.teamcode.hardware.VuforiaField;

/**
 * Created by Ethan Tucker on 11/3/2016.
 */
@Autonomous(name="11874: RedAutonomous", group="11874")
public class BotStraightAutonOp extends LinearOpMode {

    Bot bot;

    @Override
    public void runOpMode() throws InterruptedException {
        bot = new Bot(hardwareMap);
        waitForStart();

        bot.getRightMotor().setPower(-0.78);
        bot.getLeftMotor().setPower(-0.75);

        Thread.sleep(2000);

        bot.getRightMotor().setPower(0);
        bot.getLeftMotor().setPower(0);
    }

}
