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
        telemetry.addData("Gyro Heading", bot.gyro.getHeading());
        telemetry.addData("Gyro X", bot.gyro.rawX());
        telemetry.addData("Gyro Y", bot.gyro.rawY());
        telemetry.addData("Gyro Z", bot.gyro.rawZ());
        //telemetry.addData("Red", bot.beaconSensor.red());
        //telemetry.addData("Blue", bot.beaconSensor.blue());
        //telemetry.addData("Green", bot.beaconSensor.green());
    }

}