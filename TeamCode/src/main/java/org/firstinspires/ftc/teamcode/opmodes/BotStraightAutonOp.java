package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.hardware.Bot;
import org.firstinspires.ftc.teamcode.hardware.VuforiaField;

/**
 * Created by Ethan Tucker on 11/3/2016.
 */
@Autonomous(name="11874: Straight Autonomous", group="11874")
public class BotStraightAutonOp extends LinearOpMode {

    Bot bot;

    @Override
    public void runOpMode() throws InterruptedException {
        bot = new Bot(hardwareMap);
        waitForStart();

        bot.encoderDrive(20);
    }

}
