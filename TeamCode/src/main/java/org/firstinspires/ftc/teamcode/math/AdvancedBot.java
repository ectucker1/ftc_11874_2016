package org.firstinspires.ftc.teamcode.math;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.hardware.HardwareBot;

/**
 * Created by Ethan Tucker on 1/24/2017.
 */
public class AdvancedBot extends HardwareBot {

    public static final double DRIVE_SPEED = 0.75;
    public static final double TURN_SPEED = 0.05;
    public static final double FULL_SPEED = 1.0;

    public static final double UNITS_ROTATION = 1440;

    public static final int GYRO_THRESHOLD = 2;
    public static final double LINE_THRESHOLD = 0.3;

    public static final double PUSHER_LEFT_INIT = 1.0;
    public static final double PUSHER_RIGHT_INIT = 0.0;
    public static final double PUSHER_LEFT_FORWARD = 0.5;
    public static final double PUSHER_RIGHT_FORWARD = 0.5;

    public AdvancedBot(LinearOpMode mode) {
        super(mode);
        resetServos();
        setDriveMode(DcMotor.RunMode.RUN_USING_ENCODER);

        calibrateGyro();
    }

    public void driveOn() {
        leftMotor.setPower(DRIVE_SPEED);
        rightMotor.setPower(DRIVE_SPEED);
    }

    public void stopDrive() {
        this.leftMotor.setPower(0);
        this.rightMotor.setPower(0);
    }

    public void stopAll() {
        this.leftMotor.setPower(0);
        this.rightMotor.setPower(0);
        //TODO More motors
    }

    public void resetServos() {
        pusherRight.setPosition(PUSHER_RIGHT_INIT);
        pusherLeft.setPosition(PUSHER_LEFT_INIT);
    }

    public void encoderDrive(double rotations) {
        encoderDrive(rotations, rotations);
    }

    public void encoderDrive(double leftRotations, double rightRotations) {
        // Determine new target position, and pass to motor controller
        int newLeftTarget = leftMotor.getCurrentPosition() - (int) (leftRotations * UNITS_ROTATION);
        int newRightTarget = rightMotor.getCurrentPosition() - (int) (rightRotations * UNITS_ROTATION);
        leftMotor.setTargetPosition(newLeftTarget);
        rightMotor.setTargetPosition(newRightTarget);

        // Turn On RUN_TO_POSITION
        setDriveMode(DcMotor.RunMode.RUN_TO_POSITION);

        // reset the timeout time and start motion.
        leftMotor.setPower(Math.abs(DRIVE_SPEED));
        rightMotor.setPower(Math.abs(DRIVE_SPEED));

        // keep looping while we are still active, and there is time left, and both motors are running.
        while ((leftMotor.isBusy() && rightMotor.isBusy()) && mode.opModeIsActive()) {
            mode.telemetry.addData("Right encoder", rightMotor.getCurrentPosition());
            mode.telemetry.addData("Right target", rightMotor.getTargetPosition());
            mode.updateTelemetry(mode.telemetry);
        }

        // Stop all motion;
        stopDrive();

        // Turn off RUN_TO_POSITION
        setDriveMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void encoderTurn(double rotations) {
        encoderDrive(rotations, -rotations);
    }

    public void turnGyro(double rotation) {
        turnGyroTo(gyroHeading() + rotation);
    }

    public void turnGyroTo(double rotation) {
        if (rotation > 0) {
            rightMotor.setPower(TURN_SPEED);
            leftMotor.setPower(-TURN_SPEED);
        } else {
            rightMotor.setPower(-TURN_SPEED);
            leftMotor.setPower(TURN_SPEED);
        }
        while (Math.abs(gyroHeading() - BotMath.clampDegrees(rotation)) > GYRO_THRESHOLD
                && mode.opModeIsActive()) {
            mode.telemetry.addData("Gyro Heading", gyro.getHeading());
            mode.telemetry.addData("Gyro Target", BotMath.clampDegrees(rotation));
            mode.updateTelemetry(mode.telemetry);
        }
        stopDrive();
    }

    public void lineFollow() {
        double steering = (lineSensorLeft.getLightDetected() - lineSensorRight.getLightDetected()) * 0.10;
        double power = 0.1;

        if(steering >= 0) {
            leftMotor.setPower(power - steering < 0 ? 0 : power - steering);
            rightMotor.setPower(power + steering < 0 ? 0 : power + steering);
        } else {
            leftMotor.setPower(power + Math.abs(steering) < 0 ? 0 : power + Math.abs(steering));
            rightMotor.setPower(power - Math.abs(steering) < 0 ? 0 : power - Math.abs(steering));
        }
    }

    public void launchBalls() throws InterruptedException {
        launcher.setPower(FULL_SPEED);
        sleep(2000);
        launcher.setPower(0.0);
    }

}
