package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.hardware.Bot;
import org.firstinspires.ftc.teamcode.hardware.VuforiaField;

/**
 * Created by Ethan Tucker on 11/3/2016.
 */
@Autonomous(name="11874: RedAutonomous", group="11874")
public class BotRedAutonOp extends LinearOpMode {

    Bot bot;

    VuforiaField field;

    @Override
    public void runOpMode() throws InterruptedException {
        bot = new Bot(hardwareMap);
        field = new VuforiaField();
        waitForStart();
    }

}
