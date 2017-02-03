package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.*;
import org.firstinspires.ftc.teamcode.math.BotMath;

/**
 * Created by Ethan Tucker on 10/25/2016.
 */
public class HardwareBot {

    protected LinearOpMode mode;
    protected HardwareMap hardwareMap;

    public DcMotor leftMotor;
    public DcMotor rightMotor;

    public DcMotor slide;

    public GyroSensor gyro;
    public OpticalDistanceSensor distance;
    public ColorSensor beaconSensor;
    public OpticalDistanceSensor lineSensor;

    public Servo thrower;

    public Servo pusherLeft;
    public Servo pusherRight;

    private double gyroOffset;

    public HardwareBot(LinearOpMode mode) {
        this.mode = mode;

        this.hardwareMap = mode.hardwareMap;
        this.leftMotor = hardwareMap.dcMotor.get("motor_left");
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        this.rightMotor = hardwareMap.dcMotor.get("motor_right");
        rightMotor.setDirection(DcMotor.Direction.FORWARD);

        //this.slide = hardwareMap.dcMotor.get("slide");

        this.gyro = hardwareMap.gyroSensor.get("gyro");
        this.beaconSensor = hardwareMap.colorSensor.get("color");
        this.lineSensor = hardwareMap.opticalDistanceSensor.get("line");
        //this.distance = map.opticalDistanceSensor.get("distance");

        this.thrower = hardwareMap.servo.get("thrower");

        this.pusherLeft = hardwareMap.servo.get("pusher_right");
        this.pusherRight = hardwareMap.servo.get("pusher_left");

        calibrateGyro();
    }

    public void calibrateGyro() {
        gyroOffset = -gyro.getHeading();
    }

    public double gyroHeading() {
        return BotMath.clampDegrees(gyro.getHeading() + gyroOffset);
    }

    public void setDriveMode(DcMotor.RunMode mode) {
        leftMotor.setMode(mode);
        rightMotor.setMode(mode);
    }

    public void sleep(long millis) throws InterruptedException {
        Thread.sleep(millis);
    }

}
