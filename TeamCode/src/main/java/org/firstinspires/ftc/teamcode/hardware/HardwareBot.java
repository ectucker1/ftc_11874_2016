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
    private Sensor phoneGyro;
    private static final float EPSILON = 0.000000001f;
    private static final float NS2S = 1.0f / 1000000000.0f;
    public final float[] rotation = new float[4];
    private float timestamp;

    public Servo pusherLeft;
    public Servo pusherRight;

    public Servo intake;

    private double gyroOffset;

    public HardwareBot(LinearOpMode mode) {
        this.mode = mode;

        this.hardwareMap = mode.hardwareMap;
        this.leftMotor = hardwareMap.dcMotor.get("motor_left");
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        this.rightMotor = hardwareMap.dcMotor.get("motor_right");
        rightMotor.setDirection(DcMotor.Direction.FORWARD);

        this.slide = hardwareMap.dcMotor.get("slide");
        this.launcher = hardwareMap.dcMotor.get("launcher");
        launcher.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        this.pusherLeft = hardwareMap.servo.get("pusher_right");
        this.pusherRight = hardwareMap.servo.get("pusher_left");
        this.intake = hardwareMap.servo.get("intake");

        this.gyro = hardwareMap.gyroSensor.get("gyro");
        this.beaconSensor = hardwareMap.colorSensor.get("color");
        this.lineSensorLeft = hardwareMap.opticalDistanceSensor.get("lineLeft");
        this.lineSensorRight = hardwareMap.opticalDistanceSensor.get("lineRight");
        this.distance = new ModernRoboticsI2cRangeSensor(hardwareMap.i2cDeviceSynch.get("distance"));

        androidSensorManager = (SensorManager) FtcRobotControllerActivity.getContext().getSystemService(Context.SENSOR_SERVICE);
        phoneGyro = androidSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        androidSensorManager.registerListener(this, phoneGyro, SensorManager.SENSOR_DELAY_FASTEST);

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

    //Copied straight from https://developer.android.com/guide/topics/sensors/sensors_motion.html
    //I have no idea what it does
    public void onSensorChanged(SensorEvent event) {
        // This timestep's delta rotation to be multiplied by the current rotation
        // after computing it from the gyro sample data.
        if (timestamp != 0) {
            final float dT = (event.timestamp - timestamp) * NS2S;
            // Axis of the rotation sample, not normalized yet.
            float axisX = event.values[0];
            float axisY = event.values[1];
            float axisZ = event.values[2];

            // Calculate the angular speed of the sample
            float omegaMagnitude = (float) Math.sqrt(axisX*axisX + axisY*axisY + axisZ*axisZ);

            // Normalize the rotation vector if it's big enough to get the axis
            // (that is, EPSILON should represent your maximum allowable margin of error)
            if (omegaMagnitude > EPSILON) {
                axisX /= omegaMagnitude;
                axisY /= omegaMagnitude;
                axisZ /= omegaMagnitude;
            }

            // Integrate around this axis with the angular speed by the timestep
            // in order to get a delta rotation from this sample over the timestep
            // We will convert this axis-angle representation of the delta rotation
            // into a quaternion before turning it into the rotation matrix.
            float thetaOverTwo = omegaMagnitude * dT / 2.0f;
            float sinThetaOverTwo = (float)Math.sin(thetaOverTwo);
            float cosThetaOverTwo = (float)Math.cos(thetaOverTwo);
            rotation[0] = sinThetaOverTwo * axisX;
            rotation[1] = sinThetaOverTwo * axisY;
            rotation[2] = sinThetaOverTwo * axisZ;
            rotation[3] = cosThetaOverTwo;
        }
        timestamp = event.timestamp;
        float[] deltaRotationMatrix = new float[9];
        SensorManager.getRotationMatrixFromVector(deltaRotationMatrix, rotation);
        // User code should concatenate the delta rotation we computed with the current rotation
        // in order to get the updated rotation.
        // rotationCurrent = rotationCurrent * deltaRotationMatrix;
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
