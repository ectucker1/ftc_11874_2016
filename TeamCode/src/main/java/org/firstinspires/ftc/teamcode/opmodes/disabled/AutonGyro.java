package org.firstinspires.ftc.teamcode.opmodes.disabled;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.GyroSensor;
import org.firstinspires.ftc.teamcode.hardware.GyroPIDController;
import org.firstinspires.ftc.teamcode.math.AdvancedBot;

/**
 * Created by STACK0V3RFL0W on 12/1/2016.
 */
@Autonomous(name = "11874: Chris's Strange Gyro Code", group = "11874")
@Disabled
public class AutonGyro extends LinearOpMode {

    public static double CurrentX = 0f;
    public static double CurrentY = 0f;
    public static double CurrentZ = 0f;
    public static double StartingGyroValue = 0f;

    private double loopCount = 0;

    @Override
    public void runOpMode() throws InterruptedException {

        AdvancedBot robot = new AdvancedBot(this);
        GyroSensor gyro = null;

        try {
            gyro = hardwareMap.gyroSensor.get("gyro");
            //((ModernRoboticsI2cGyro)gyro).setHeadingMode(ModernRoboticsI2cGyro.HeadingMode.HEADING_CARTESIAN);
        } catch (Exception ex) {
        }

        if (gyro == null) {
            telemetry.addData("Gyro failed to load check hardware map", gyro.toString());

        }
        GyroPIDController pidController = new GyroPIDController(gyro);

        pidController.initialize();
        //  pidController.setDirection(DcMotor.Direction.REVERSE);
        //  pidController.calibrate();
        //   pidController.setTarget(0);

        long target = System.currentTimeMillis() + 2000;

        while (opModeIsActive()) {
            while (System.currentTimeMillis() < target) {
                pidController.setTargetAngle(90);
                pidController.calculateMotorPowers();
                telemetry.addData("Starting Gyro:", pidController.getTarget());
                telemetry.addData("Starting Gyro:", gyro.getDeviceName());

                telemetry.addData("leftPower", pidController.getLeftPower());
                telemetry.addData("rightPower", pidController.getRightPower());
                telemetry.addData("GyroHeading:", gyro.getHeading());
                robot.leftMotor.setPower(pidController.getLeftPower());
                robot.rightMotor.setPower(pidController.getRightPower());
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

