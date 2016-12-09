package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.hardware.Bot;
import org.firstinspires.ftc.teamcode.hardware.VuforiaField;

/**
 * Created by Ethan Tucker on 11/3/2016.
 */
@Autonomous(name="11874: BlueAutonomous", group="11874")
public class BotBlueAutonOp extends LinearOpMode {

    Bot bot;

    VuforiaField field;

    @Override
    public void runOpMode() throws InterruptedException {
        bot = new Bot(hardwareMap);
        waitForStart();

        //Turn right
        bot.getLeftMotor().setPower(.50);

        //Go forward
        bot.getRightMotor().setPower(0.75);
        bot.getLeftMotor().setPower(0.75);

        Thread.sleep(5000);

        //Turn right
        bot.getLeftMotor().setPower(.50);
        bot.getRightMotor().setPower(0);

        Thread.sleep(500);

        //Go forward a bit more
        bot.getLeftMotor().setPower(.75);
        bot.getRightMotor().setPower(.75);

        bot.stopAll();

        //Figure out color
    }

}
