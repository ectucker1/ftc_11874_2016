package org.firstinspires.ftc.teamcode.math;

import android.test.suitebuilder.annotation.SmallTest;

import static junit.framework.Assert.*;

/**
 * Created by Ethan Tucker on 11/1/2016.
 */
public class BotMathTest {

    @SmallTest
    public void testPowerCurve() {
        assertEquals(BotMath.powerCurve(1), 1, 0.01);
        assertEquals(BotMath.powerCurve(0), 0, 0.01);
        assertEquals(BotMath.powerCurve(-1), -1, 0.01);
    }

}