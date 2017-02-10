package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.math.AdvancedBot;
import org.firstinspires.ftc.teamcode.math.BotMath;

/**
 * Created by Ethan Tucker on 10/25/2016.
 */
public class BaseControlOp extends LinearOpMode {

    protected AdvancedBot bot;

    private boolean bumperDown;
    protected int invert = 1;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void runOpMode() {
        bot = new AdvancedBot(this);

        waitForStart();

        while (opModeIsActive()) {

            bot.leftMotor.setPower(BotMath.powerCurve(gamepad1.right_stick_y) * invert);
            bot.rightMotor.setPower(BotMath.powerCurve(gamepad1.left_stick_y) * invert);

            bot.slide.setPower(BotMath.powerCurve(gamepad2.left_stick_y));

            if (gamepad1.y) {
                bot.launcher.setPower(AdvancedBot.DRIVE_SPEED);
            } else {
                bot.launcher.setPower(0.0);
            }

            if (gamepad1.x) {
                bot.pusherLeft.setPosition(AdvancedBot.PUSHER_LEFT_FORWARD);
            }
            if (gamepad1.b) {
                bot.pusherRight.setPosition(AdvancedBot.PUSHER_RIGHT_FORWARD);
            }
            if (gamepad1.a) {
                bot.resetServos();
            }

            if (gamepad1.right_bumper) {
                if(!bumperDown) {
                    invert = invert * -1;
                }
                bumperDown = true;
            } else {
                bumperDown = false;
            }

            telemetry.addData("Left Motor", bot.leftMotor.getPower());
            telemetry.addData("Right Motor", bot.rightMotor.getPower());
            telemetry.addData("Slide Motor", bot.slide.getPower());
            telemetry.addData("Left Pusher Position", bot.pusherLeft.getPosition());
            telemetry.addData("Right Pusher Position", bot.pusherRight.getPosition());
            telemetry.addData("Beacon Red", bot.beaconSensor.red());
            telemetry.addData("Beacon Blue", bot.beaconSensor.blue());
            telemetry.addData("Beacon Green", bot.beaconSensor.green());
            telemetry.addData("Line Light", bot.lineSensor.getRawLightDetected());
            telemetry.addData("Gyro Heading", bot.gyroHeading());
            telemetry.addData("Controls", invert == 1 ? "Normal" : "Inverted");
            telemetry.update();
        }
    }

}
