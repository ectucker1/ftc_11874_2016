package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.hardware.Bot;
import org.firstinspires.ftc.teamcode.math.BotMath;

/**
 * Created by Ethan Tucker on 10/25/2016.
 */
public class BaseControlOp extends OpMode{

    protected Bot bot;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        bot = new Bot(hardwareMap);
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {

    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {

    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     * Drives the robot
     */
    @Override
    public void loop() {
        bot.getLeftMotor().setPower(BotMath.powerCurve(gamepad1.left_stick_y));
        bot.getRightMotor().setPower(BotMath.powerCurve(gamepad1.right_stick_y));

        if(gamepad1.right_bumper) {
            bot.getIntake().setPower(0.75);
        }

        telemetry.addData("Right Motor", bot.getRightMotor().getPower());
        telemetry.addData("Left Motor", bot .getLeftMotor().getPower());
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {

    }

}
