package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.util.RobotLog;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.MatrixF;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ethan Tucker on 11/26/2016.
 */
public class VuforiaField {

    private VectorF lastLocation = null;
    private double lastOrientation = 0;

    private VuforiaLocalizer vuforia;

    private List<VuforiaTrackable> allTrackables;

    public VuforiaField() {
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(com.qualcomm.ftcrobotcontroller.R.id.cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "ARutqJb/////AAAAGbYFu5q8DkJ8qSveP/lilQMw13Pxc3Ts6NBk30BlF2wl5l4NxnHx7xbaCHgmdf+D54HxMeF+EE9LR9IIYzUvlMDAcFthc0TZDnw8+rnn+KueFwGyR2oy5hW2rxui1BhpcIEKLJDcvpgpxdCsn1sfLAFtUKM8b+QwPvvgfixjrORxz+lCtsiHaKSfp7Kj/plS52GZkZZGNNu8twcM5MBBsIO9I0I3mXscD8xtkFHJa5Ki1DUr3TnfNqUsgkNJsewv0wgTQQU8UELOr6UyQbdVbRbeh63B7gQjyxrvBTBh/QkKsl18SKf9I+HN0VSkBNI/QbTYtC0uuz3bAVljPXTATh8H5YBQK+2ZmWtM9/TWjTOB";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        VuforiaTrackables fieldTrackables = this.vuforia.loadTrackablesFromAsset("FTC_2016-17");
        VuforiaTrackable wheels = fieldTrackables.get(0);
        wheels.setName("Wheels");

        VuforiaTrackable tools  = fieldTrackables.get(1);
        tools.setName("Tools");

        VuforiaTrackable legos = fieldTrackables.get(2);
        legos.setName("Legos");

        VuforiaTrackable gears = fieldTrackables.get(3);
        gears.setName("Gears");

        /** For convenience, gather together all the trackable objects in one easily-iterable collection */
        List<VuforiaTrackable> allTrackables = new ArrayList<VuforiaTrackable>();
        allTrackables.addAll(fieldTrackables);

        float mmPerInch        = 25.4f;
        float mmBotWidth       = 18 * mmPerInch;            // ... or whatever is right for your robot
        float mmFTCFieldWidth  = (12 * 12 - 2) * mmPerInch;   // the FTC field is ~11'10" center-to-center of the glass panels

        OpenGLMatrix wheelsLocation = OpenGLMatrix
                /* Then we translate the target off to the RED WALL. Our translation here
                is a negative translation in X.*/
                .translation(-mmFTCFieldWidth/6 * 2.5f, 0, 0)
                .multiplied(Orientation.getRotationMatrix(
                        /* First, in the fixed (field) coordinate system, we rotate 90deg in X, then 90 in Z */
                        AxesReference.EXTRINSIC, AxesOrder.XZX,
                        AngleUnit.DEGREES, 90, 0, 0));
        wheels.setLocation(wheelsLocation);


        OpenGLMatrix legosLocation = OpenGLMatrix
                /* Then we translate the target off to the Blue Audience wall.
                Our translation here is a positive translation in Y.*/
                .translation(-mmFTCFieldWidth/6 * 4.5f, 0, 0)
                .multiplied(Orientation.getRotationMatrix(
                        /* First, in the fixed (field) coordinate system, we rotate 90deg in X */
                        AxesReference.EXTRINSIC, AxesOrder.XZX,
                        AngleUnit.DEGREES, 90, 0, 0));
        legos.setLocation(legosLocation);

        OpenGLMatrix toolsLocation = OpenGLMatrix
                /* Then we translate the target off to the Blue Audience wall.
                Our translation here is a positive translation in Y.*/
                .translation(0, mmFTCFieldWidth/6 * 1.5f, 0)
                .multiplied(Orientation.getRotationMatrix(
                        /* First, in the fixed (field) coordinate system, we rotate 90deg in X */
                        AxesReference.EXTRINSIC, AxesOrder.XZX,
                        AngleUnit.DEGREES, 90, 90, 0));
        tools.setLocation(toolsLocation);

        OpenGLMatrix gearsLocation = OpenGLMatrix
                /* Then we translate the target off to the Blue Audience wall.
                Our translation here is a positive translation in Y.*/
                .translation(0, mmFTCFieldWidth/6 * 3.5f, 0)
                .multiplied(Orientation.getRotationMatrix(
                        /* First, in the fixed (field) coordinate system, we rotate 90deg in X */
                        AxesReference.EXTRINSIC, AxesOrder.XZX,
                        AngleUnit.DEGREES, 90, 90, 0));
        gears.setLocation(gearsLocation);

        OpenGLMatrix phoneLocationOnRobot = OpenGLMatrix
                .translation(12.5f * mmPerInch, -15.75f * mmPerInch, 7.75f * mmPerInch)
                .multiplied(Orientation.getRotationMatrix(
                        AxesReference.EXTRINSIC, AxesOrder.YZY,
                        AngleUnit.DEGREES, -90, 180, 0));

        ((VuforiaTrackableDefaultListener)wheels.getListener()).setPhoneInformation(phoneLocationOnRobot, parameters.cameraDirection);
        ((VuforiaTrackableDefaultListener)tools.getListener()).setPhoneInformation(phoneLocationOnRobot, parameters.cameraDirection);
        ((VuforiaTrackableDefaultListener)legos.getListener()).setPhoneInformation(phoneLocationOnRobot, parameters.cameraDirection);
        ((VuforiaTrackableDefaultListener)gears.getListener()).setPhoneInformation(phoneLocationOnRobot, parameters.cameraDirection);

        fieldTrackables.activate();
    }

    public void update() {
        for (VuforiaTrackable trackable : allTrackables) {
            System.out.println(trackable.getName() +
                    (((VuforiaTrackableDefaultListener) trackable.getListener()).isVisible() ? "Visible" : "Not Visible"));

            OpenGLMatrix robotLocationTransform = ((VuforiaTrackableDefaultListener) trackable.getListener()).getUpdatedRobotLocation();
            if (robotLocationTransform != null) {
                lastLocation = robotLocationTransform.getTranslation();
                Orientation rot = Orientation.getOrientation(robotLocationTransform, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);

                lastOrientation = rot.thirdAngle;
                if (lastOrientation < 0) {
                    lastOrientation = 360 + lastOrientation;
                }
            }
        }
    }

    public VectorF getRobotLocation() {
        return lastLocation;
    }

    public double getRobotOrientation() {
        return lastOrientation;
    }

}
