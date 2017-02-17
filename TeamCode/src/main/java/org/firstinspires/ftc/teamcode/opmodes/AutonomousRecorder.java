package org.firstinspires.ftc.teamcode.opmodes;

import android.util.Log;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.exception.RobotCoreException;
import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;
import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerApp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ethan Tucker on 2/16/2017.
 */
@TeleOp(name = "11874: AutonRecorder", group = "11874")
public class AutonomousRecorder extends BaseControlOp {

    private List<byte[]> gamepadStates = new ArrayList<byte[]>();

    @Override
    public void control() throws InterruptedException {
        super.control();
        try {
            gamepadStates.add(gamepad1.toByteArray());
            gamepadStates.add(gamepad2.toByteArray());
            Log.i("Autonrecorder", "Added gamepad state " + gamepadStates.size());
        } catch (RobotCoreException e) {
            e.printStackTrace();
            Log.w("Autonrecorder", "Error converting to byte[]: " + e.toString());
        }
        bot.sleep(50);
        write();
    }

    @Override
    public void runOpMode() throws InterruptedException {
        super.runOpMode();
        Log.i("Autonrecorder", "Recording complete");
    }

    public void write() {
        try {
            File file = new File(FtcRobotControllerActivity.getContext().getFilesDir(), "autonomous.dat");
            FileOutputStream fo = new FileOutputStream(file);
            ObjectOutputStream stream = new ObjectOutputStream(fo);
            stream.writeObject(gamepadStates);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("Autonrecorder", "Wrote gamepad data" + gamepadStates.size());
    }

}
