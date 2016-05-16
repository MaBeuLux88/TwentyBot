package com.beugnet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ScreenShot {

    private static final int CORNER_X = 2250;
    private static final int CORNER_Y = 10;
    private static final int WIDTH = 570;
    private static final int HEIGHT = 870;

    private BufferedImage image;

    public ScreenShot(BufferedImage image) {
        this.image = image;
    }

    public ScreenShot() {
        this.image = takeScreenShot();
    }

    public BufferedImage getImage() {
        return image;
    }

    private BufferedImage takeScreenShot() {
        try {
            Robot robot = new Robot();
            Rectangle screenRect = new Rectangle(CORNER_X, CORNER_Y, WIDTH, HEIGHT);
            return robot.createScreenCapture(screenRect);
        } catch (AWTException e) {
            System.err.println("Error when trying to take a screen shot!");
            System.exit(-1);
        }
        return null;
    }
}
