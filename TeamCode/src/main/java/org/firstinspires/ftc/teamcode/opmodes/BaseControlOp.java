package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.math.AdvancedBot;
import org.firstinspires.ftc.teamcode.math.BotMath;

/**
 * Created by Ethan Tucker on 10/25/2016.
 */
public class BaseControlOp extends LinearOpMode {

    protected AdvancedBot bot;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void runOpMode() {
        bot = new AdvancedBot(this);

        waitForStart();

        while (opModeIsActive()) {
            bot.leftMotor.setPower(BotMath.powerCurve(gamepad1.left_stick_y));
            bot.rightMotor.setPower(BotMath.powerCurve(gamepad1.right_stick_y));

            if (gamepad1.dpad_up) {
                //bot.slide.setPower(0.5);
            } else if (gamepad1.dpad_down) {
                //bot.slide.setPower(-0.5);
            } else {
                //bot.slide.setPower(0.0);
            }
            //bot.slide.setPower(BotMath.powerCurve(gamepad2.left_stick_y));

            if (gamepad1.y) {
                bot.thrower.setPosition(1.0);
            }

            if (gamepad1.x) {
                bot.pusherLeft.setPosition(0.0);
            }
            if (gamepad1.b) {
                bot.pusherRight.setPosition(1.0);
            }
            if (gamepad1.a) {
                bot.resetServos();
            }

            telemetry.addData("Right Motor", bot.rightMotor.getPower());
            telemetry.addData("Left Motor", bot.leftMotor.getPower());
            telemetry.addData("Beacon Red", bot.beaconSensor.red());
            telemetry.addData("Beacon Blue", bot.beaconSensor.blue());
            telemetry.addData("Beacon Green", bot.beaconSensor.green());
            telemetry.addData("Line Light", bot.lineSensor.getRawLightDetected());
            telemetry.addData("Gyro Heading", bot.gyroHeading());
            telemetry.update();
        }
    }

}
