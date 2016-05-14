package com.beugnet;

import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class UnitTests {

    private static final int CORNER_X = 2250;
    private static final int CORNER_Y = 10;
    private static final int WIDTH = 570;
    private static final int HEIGHT = 870;

    private void robo() throws AWTException, IOException {
        Robot robot = new Robot();
        Rectangle screenRect = new Rectangle(CORNER_X, CORNER_Y, WIDTH, HEIGHT);
        BufferedImage screenShot = robot.createScreenCapture(screenRect);
        ImageIO.write(screenShot, "PNG", new File("toto.png"));
        screenShot.getRGB(1, 2);
    }

    @Test
    public void testScreenshot() throws IOException, AWTException {
        BufferedImage screeShot = ImageIO.read(this.getClass().getResourceAsStream("test-input.png"));
        assertThat(screeShot).isNotNull();
    }
}
