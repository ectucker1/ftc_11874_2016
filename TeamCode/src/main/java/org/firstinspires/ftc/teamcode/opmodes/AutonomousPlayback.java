package org.firstinspires.ftc.teamcode.opmodes;

import android.util.Log;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.exception.RobotCoreException;
import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ethan Tucker on 2/16/2017.
 */
@Autonomous(name = "11874: AutonPlayback", group = "11874")
public class AutonomousPlayback extends BaseControlOp {

    int index = 0;
    List<byte[]> gamepadStates;

    @Override
    public void runOpMode() throws InterruptedException {
        gamepadStates = load();
        Log.i("Autonplayback", "Loaded gamepad data" + gamepadStates.size());
        super.runOpMode();
    }

    @Override
    public void control() throws InterruptedException {
        try {
            if(index < gamepadStates.size()) {
                gamepad1.fromByteArray(gamepadStates.get(index));
                index++;
                gamepad2.fromByteArray(gamepadStates.get(index));
                index++;
            }
        } catch (RobotCoreException e) {
            e.printStackTrace();
            Log.w("Autonplayback", "Error converting byte[] to gamepad: " + e.toString());
        }
        super.control();
        bot.sleep(50);
    }

    public List<byte[]> load() {
        List<byte[]> record = null;
        try {
            File file = new File(FtcRobotControllerActivity.getContext().getFilesDir(), "autonomous.dat");
            FileInputStream fi = new FileInputStream(file);
            ObjectInputStream stream = new ObjectInputStream(fi);
            record = (List<byte[]>)stream.readObject();
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return record;
    }

}