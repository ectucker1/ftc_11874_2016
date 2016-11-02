package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by Ethan Tucker on 10/25/2016.
 */
public class Bot {

    private HardwareMap hardwareMap;

    private DcMotor leftMotor;
    private DcMotor rightMotor;

    public Bot(HardwareMap map) {
        this.hardwareMap = map;
        this.leftMotor = map.dcMotor.get("motor_left");
        this.rightMotor = map.dcMotor.get("motor_right");
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
}
