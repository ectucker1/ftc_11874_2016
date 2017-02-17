package org.firstinspires.ftc.teamcode.hardware;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.*;
import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;
import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerApp;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.math.BotMath;

/**
 * Created by Ethan Tucker on 10/25/2016.
 */
public class HardwareBot implements SensorEventListener {

    protected LinearOpMode mode;
    protected HardwareMap hardwareMap;

    public DcMotor leftMotor;
    public DcMotor rightMotor;

    public DcMotor slide;
    public DcMotor launcher;

    public GyroSensor gyro;
    public ModernRoboticsI2cRangeSensor distance;
    public ColorSensor beaconSensor;
    public OpticalDistanceSensor lineSensorLeft;
    public OpticalDistanceSensor lineSensorRight;

    private SensorManager androidSensorManager;
    private Sensor magneticFieldSensor;
    private Sensor accelerometer;

    public Servo pusherLeft;
    public Servo pusherRight;

    private double gyroOffset;

    private final float[] mAccelerometerReading = new float[3];
    private final float[] mMagnetometerReading = new float[3];

    private final float[] mRotationMatrix = new float[9];
    private final float[] mOrientationAngles = new float[3];

    public HardwareBot(LinearOpMode mode) {
        this.mode = mode;

        this.hardwareMap = mode.hardwareMap;
        this.leftMotor = hardwareMap.dcMotor.get("motor_left");
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        this.rightMotor = hardwareMap.dcMotor.get("motor_right");
        rightMotor.setDirection(DcMotor.Direction.FORWARD);

        this.slide = hardwareMap.dcMotor.get("slide");
        this.launcher = hardwareMap.dcMotor.get("launcher");

        this.pusherLeft = hardwareMap.servo.get("pusher_right");
        this.pusherRight = hardwareMap.servo.get("pusher_left");

        this.gyro = hardwareMap.gyroSensor.get("gyro");
        this.beaconSensor = hardwareMap.colorSensor.get("color");
        this.lineSensorLeft = hardwareMap.opticalDistanceSensor.get("lineLeft");
        this.lineSensorRight = hardwareMap.opticalDistanceSensor.get("lineRight");
        this.distance = new ModernRoboticsI2cRangeSensor(hardwareMap.i2cDeviceSynch.get("distance"));

        androidSensorManager = (SensorManager) FtcRobotControllerActivity.getContext().getSystemService(Context.SENSOR_SERVICE);
        magneticFieldSensor = androidSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        accelerometer = androidSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        androidSensorManager.registerListener(this, magneticFieldSensor, SensorManager.SENSOR_DELAY_FASTEST);
        androidSensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_FASTEST);

        calibrateGyro();
    }

    public void calibrateGyro() {
        gyroOffset = -gyro.getHeading();
    }

    public double gyroHeading() {
        return BotMath.clampDegrees(gyro.getHeading() + gyroOffset);
    }

    public double getDistanceCM() {
        return distance.getDistance(DistanceUnit.CM);
    }

    public void setDriveMode(DcMotor.RunMode mode) {
        leftMotor.setMode(mode);
        rightMotor.setMode(mode);
    }

    public void sleep(long millis) throws InterruptedException {
        Thread.sleep(millis);
    }

    public void destroy() {
        androidSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            System.arraycopy(event.values, 0, mAccelerometerReading,
                    0, mAccelerometerReading.length);
            updateOrientationAngles();
        }
        else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            System.arraycopy(event.values, 0, mMagnetometerReading,
                    0, mMagnetometerReading.length);
            updateOrientationAngles();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void updateOrientationAngles() {
        SensorManager.getRotationMatrix(mRotationMatrix, null,
                mAccelerometerReading, mMagnetometerReading);

        SensorManager.getOrientation(mRotationMatrix, mOrientationAngles);
    }

    public float[] getOrientation() {
        return mOrientationAngles;
    }

    public float getAzimuth() {
        return mOrientationAngles[0];
    }

    public float getPitch() {
        return mOrientationAngles[1];
    }

    public float getRoll() {
        return mOrientationAngles[2];
    }

}
