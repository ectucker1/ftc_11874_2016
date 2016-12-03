package org.firstinspires.ftc.teamcode.opmodes;

import android.hardware.SensorManager;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.GyroSensor;

import org.firstinspires.ftc.teamcode.hardware.Bot;
import org.firstinspires.ftc.teamcode.hardware.GyroPIDController;

import java.util.Date;
import java.util.Timer;

/**
 * Created by STACK0V3RFL0W on 12/1/2016.
 */
@Autonomous(name="Pushbot: Auto Drive By PID Gyro", group="Autonomous")
public class AutonGyro extends LinearOpMode {

    public static double CurrentX = 0f;
    public static double CurrentY = 0f;
    public static double CurrentZ = 0f;
    public static double StartingGyroValue = 0f;

    private double loopCount = 0;

    @Override
    public void runOpMode() throws InterruptedException {

        Bot robot = new Bot(hardwareMap);
        GyroSensor gyro = null;

        try {
            gyro = hardwareMap.gyroSensor.get("gyro");
            //((ModernRoboticsI2cGyro)gyro).setHeadingMode(ModernRoboticsI2cGyro.HeadingMode.HEADING_CARTESIAN);
        } catch (Exception ex) {
        }

        if (gyro == null) {
            // gyro = new NullGyro();
        }
        GyroPIDController pidController = new GyroPIDController(gyro);
        pidController.initialize();
        //  pidController.setDirection(DcMotor.Direction.REVERSE);
        //  pidController.calibrate();
        //   pidController.setTarget(0);

        Date d = java.util.Calendar.getInstance().getTime();
        d.setTime(15000);
        while (opModeIsActive())
        {

            Date c = java.util.Calendar.getInstance().getTime();
            while (c.before(d))
            {

                pidController.setTargetAngle(0);

                telemetry.addData("Starting Gyro:", pidController.getTarget());
                telemetry.addData("Starting Gyro:", gyro.getDeviceName());

                telemetry.addData("leftPower", pidController.getLeftPower());
                telemetry.addData("rightPower", pidController.getRightPower());
                telemetry.addData("GyroHeading:", gyro.getHeading());
                robot.getLeftMotor().setPower(pidController.getLeftPower());
                robot.getRightMotor().setPower(pidController.getRightPower());
                telemetry.addData("Gyro", gyro.getHeading());
                telemetry.addData("Gyro", gyro.getHeading());
                telemetry.addData("Accelerometer X:", CurrentX);
                telemetry.addData("Accelerometer Y:", CurrentY);
                telemetry.addData("Accelerometer Z:", CurrentZ);

                loopCount++;
                telemetry.addData("Cycles", loopCount);

            }
        }
    }
}

