package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.hardware.Bot;

/**
 * Created by Ethan Tucker on 11/3/2016.
 */
public class BotAutonOp extends LinearOpMode {

    Bot bot;

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        bot = new Bot(hardwareMap);
    }

}
