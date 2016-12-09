package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.*;
import org.firstinspires.ftc.teamcode.hardware.nullware.NullDcMotor;

/**
 * Created by Ethan Tucker on 10/25/2016.
 */
public class Bot {

    private HardwareMap hardwareMap;

    private DcMotor leftMotor;
    private DcMotor rightMotor;

    private DcMotor intake;

    private DcMotor slide;

    private Servo pusher;

    private GyroSensor gyro;

    private ColorSensor color;

    public Bot(HardwareMap map) {
        this.hardwareMap = map;
        this.leftMotor = map.dcMotor.get("motor_left");
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        this.rightMotor = map.dcMotor.get("motor_right");
        this.pusher = map.servo.get("pusher");
        this.slide = map.dcMotor.get("slide");
        //this.intake = map.dcMotor.get("intake");
        this.intake = new NullDcMotor();

        this.gyro = map.gyroSensor.get("gyro");
        this.color = map.colorSensor.get("color");
    }

    public DcMotor getLeftMotor() {
        return leftMotor;
    }

    public void setLeftMotor(DcMotor leftMotor) {
        this.leftMotor = leftMotor;
    }

    public DcMotor getRightMotor() {
        return rightMotor;
    }

    public void setRightMotor(DcMotor rightMotor) {
        this.rightMotor = rightMotor;
    }

    public GyroSensor getGyro() {
        return gyro;
    }

    public void setGyro(GyroSensor gyro) {
        this.gyro = gyro;
    }

    public DcMotor getIntake() {
        return intake;
    }

    public void setIntake(DcMotor intake) {
        this.intake = intake;
    }

    public void stopDrive() {
        this.leftMotor.setPower(0);
        this.rightMotor.setPower(0);
    }

    public void stopAll() {
        this.leftMotor.setPower(0);
        this.rightMotor.setPower(0);
        this.intake.setPower(0);
    }

    public ColorSensor getColor() {
        return color;
    }

    public void setColor(ColorSensor color) {
        this.color = color;
    }

    public Servo getPusher() {
        return pusher;
    }

    public DcMotor getSlide() {
        return slide;
    }
}
