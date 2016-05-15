package com.beugnet;

import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class UnitTests {

    private static final int CORNER_X = 2250;
    private static final int CORNER_Y = 10;
    private static final int WIDTH = 570;
    private static final int HEIGHT = 870;
    private Map<Integer, Integer> colorMapping = new HashMap<>();

    private void robo() throws AWTException, IOException {
        Robot robot = new Robot();
        Rectangle screenRect = new Rectangle(CORNER_X, CORNER_Y, WIDTH, HEIGHT);
        BufferedImage screenShot = robot.createScreenCapture(screenRect);
        ImageIO.write(screenShot, "PNG", new File("toto.png"));
        screenShot.getRGB(1, 2);
    }

    @Test
    public void testScreenshot() throws IOException, AWTException {

        initMap();

        InputStream resourceAsStream = UnitTests.class.getClassLoader().getResourceAsStream("test-input.png");
        BufferedImage screenShot = ImageIO.read(resourceAsStream);

        for (int i = 0; i < 8; i++) {
            System.out.println("\nLIGNE " + (i + 1));
            for (int j = 0; j < 7; j++) {
                int rgb = screenShot.getRGB(20 + 80 * j, 822 - 85 * i);
                System.out.print((colorMapping.get(rgb) != null ? colorMapping.get(rgb) : rgb )+ "\t");
            }
        }

        assertThat(screenShot).as("screenShot null").isNotNull();
    }

    private void initMap() {
        colorMapping.put(-2170914, 0);
        colorMapping.put(-100, 1);
        colorMapping.put(-56287, 2);
        colorMapping.put(-16714835, 3);
        colorMapping.put(-14054657, 4);
        colorMapping.put(-2185473, 5);
        colorMapping.put(-13505792, 6);
        colorMapping.put(-11331, 7);
        colorMapping.put(-6553353, 8);
        colorMapping.put(-18944, 9);
        colorMapping.put(-3750970, 10);
        colorMapping.put(-3212544, 11);
        colorMapping.put(-256, 12);
        colorMapping.put(-59269, 13);
        colorMapping.put(-8093052, 15);
    }
}
