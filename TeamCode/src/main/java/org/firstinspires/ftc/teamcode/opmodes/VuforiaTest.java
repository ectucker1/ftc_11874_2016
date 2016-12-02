package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.hardware.VuforiaField;

/**
 * Created by Ethan Tucker on 11/26/2016.
 */
@TeleOp(name="11874: Vuforia Test", group="11874")
public class VuforiaTest extends BaseControlOp {

    VuforiaField field;

    @Override
    public void init() {
        super.init();
        field = new VuforiaField();
    }

    @Override
    public void loop() {
        super.loop();
        field.update();
        telemetry.addData("Bot Location", field.getRobotLocation());
        telemetry.addData("Bot Rotation", field.getRobotOrientation());
    }

}