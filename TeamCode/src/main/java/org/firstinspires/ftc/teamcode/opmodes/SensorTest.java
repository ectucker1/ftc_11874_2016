package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Ethan Tucker on 11/26/2016.
 */
@TeleOp(name="11874: Sensor Test", group="11874")
public class SensorTest extends BaseControlOp {

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void loop() {
        super.loop();
        telemetry.addData("Gyro Rotation Fraction", bot.getGyro().getRotationFraction());
        telemetry.addData("Gyro X", bot.getGyro().rawX());
        telemetry.addData("Gyro Y", bot.getGyro().rawY());
        telemetry.addData("Gyro Z", bot.getGyro().rawZ());
        telemetry.addData("Red", bot.getBeaconSensor().red());
        telemetry.addData("Blue", bot.getBeaconSensor().blue());
        telemetry.addData("Green", bot.getBeaconSensor().green());
    }

}