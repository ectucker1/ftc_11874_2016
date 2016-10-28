package org.firstinspires.ftc.teamcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Ethan Tucker on 10/27/2016.
 */
public class BotTest {

    @Test
    public void testBotSavesHardwareMap() {
        Bot bot = new Bot(null);
        assertEquals(null, bot.hardwareMap);
    }

}