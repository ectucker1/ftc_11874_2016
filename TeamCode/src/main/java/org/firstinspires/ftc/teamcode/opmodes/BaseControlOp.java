package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.teamcode.hardware.Bot;
import org.firstinspires.ftc.teamcode.math.BotMath;

/**
 * Created by Ethan Tucker on 10/25/2016.
 */
public class BaseControlOp extends OpMode{

    protected Bot bot;

    protected double pusherPos = 0.5;

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
            bot.getSlide().setPower(0.5);
        } else if(gamepad1.left_bumper) {
            bot.getSlide().setPower(-0.5);
        } else {
            bot.getSlide().setPower(0);
        }

        if(gamepad1.a) {
            pusherPos += 0.001;
            //bot.getPusher().setPosition(pusherPos);
        } else if(gamepad1.x) {
            //bot.getPusher().setPosition(0.0);
        } else if(gamepad1.b) {
            pusherPos -= 0.001;
            //bot.getPusher().setPosition(pusherPos);
        }
        pusherPos = Range.clip(pusherPos, 0.0, 1.0);
        bot.getPusher().setPosition(pusherPos);

        telemetry.addData("Right Motor", bot.getRightMotor().getPower());
        telemetry.addData("Left Motor", bot .getLeftMotor().getPower());
        telemetry.addData("Pusher Posiion", bot.getPusher().getPosition());
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {

    }

}
