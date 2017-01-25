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
        bot.leftMotor.setPower(BotMath.powerCurve(gamepad1.left_stick_y));
        bot.rightMotor.setPower(BotMath.powerCurve(gamepad1.right_stick_y));

        if(gamepad1.dpad_up) {
            //bot.slide.setPower(0.5);
        } else if(gamepad1.dpad_down) {
            //bot.slide.setPower(-0.5);
        } else {
            //bot.slide.setPower(0.0);
        }
        //bot.slide.setPower(BotMath.powerCurve(gamepad2.left_stick_y));

        if(gamepad1.y) {
            bot.thrower.setPosition(1.0);
        }

        if(gamepad1.x) {
            bot.pusherLeft.setPosition(0.0);
        }
        if(gamepad1.b) {
            bot.pusherRight.setPosition(1.0);
        }
        if(gamepad1.a) {
            bot.resetServos();
        }

        telemetry.addData("Right Motor", bot.rightMotor.getPower());
        telemetry.addData("Left Motor", bot .leftMotor.getPower());
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {

    }

}
