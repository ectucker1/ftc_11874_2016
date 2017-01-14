package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.*;
import org.firstinspires.ftc.teamcode.hardware.nullware.NullDcMotor;

/**
 * Created by Ethan Tucker on 10/25/2016.
 */
public class Bot {

    public static final double DRIVE_SPEED = 0.05;
    public static final double TURN_SPEED = 0.01;

    public static final double UNITS_ROTATION = 1440;

    public static final int GYRO_THRESHOLD = 2;

    private LinearOpMode mode;
    private boolean linear = false;

    public HardwareMap hardwareMap;

    public DcMotor leftMotor;
    public DcMotor rightMotor;

    public GyroSensor gyro;
    public OpticalDistanceSensor distance;
    public ColorSensor beaconSensor;
    public ColorSensor lineSensor;

    private double gyroOffset;

    public Bot(LinearOpMode mode) {
        this(mode.hardwareMap);
        this.mode = mode;
        linear = true;
    }

    public Bot(HardwareMap map) {
        this.hardwareMap = map;
        this.leftMotor = hardwareMap.dcMotor.get("motor_left");
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        this.rightMotor = hardwareMap.dcMotor.get("motor_right");
        rightMotor.setDirection(DcMotor.Direction.REVERSE);

        this.gyro = hardwareMap.gyroSensor.get("gyro");
        //this.beaconSensor = map.colorSensor.get("color");
        //this.lineSensor = map.colorSensor.get("line");
        //this.distance = map.opticalDistanceSensor.get("distance");

        setDriveMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void calibrateGyro() throws InterruptedException {
        gyro.calibrate();
        Thread.sleep(5000);
        gyroOffset = -gyro.getHeading();
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

    public void setDriveMode(DcMotor.RunMode mode) {
        leftMotor.setMode(mode);
        rightMotor.setMode(mode);
    }

    public void driveOn() {
        leftMotor.setPower(DRIVE_SPEED);
        rightMotor.setPower(DRIVE_SPEED);
    }

    public void encoderDrive(double leftRotations, double rightRotations) {
        if(linear) {
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
        } else {
            throw new IllegalStateException("Encoder driving can only be used when constructed from LinearOpMode.");
        }
    }

    public void encoderDrive(double rotations) {
        encoderDrive(rotations, rotations);
    }

    public void turnGyro(int rotation) {
        int startHeading = gyro.getHeading();
        if(linear) {
            rightMotor.setPower(TURN_SPEED);
            leftMotor.setPower(-TURN_SPEED);
            while (Math.abs(gyro.getHeading() - startHeading - rotation) > GYRO_THRESHOLD
                    && mode.opModeIsActive()) ;
            stopDrive();
        } else {
            throw new IllegalStateException("Gyro turning can only be used when constructed from LinearOpMode.");
        }
    }

    public void sleep(long millis) throws InterruptedException {
        Thread.sleep(millis);
    }

}
