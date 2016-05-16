package com.beugnet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class UtilitiesForDev {

    private static final int CORNER_X = 2250;
    private static final int CORNER_Y = 10;
    private static final int WIDTH = 570;
    private static final int HEIGHT = 870;
    private static final int FIRST_TILE_CENTER_X = 45;
    private static final int FIRST_TILE_CENTER_Y = 229;
    private static final int SEPARATION_X = 80;
    private static final int SEPARATION_Y = 85;
    private static final int COLOR_SHIFT = 25;

    private void screenShotOnDisk() throws AWTException, IOException, InterruptedException {
        Robot robot = new Robot();
        Rectangle screenRect = new Rectangle(CORNER_X, CORNER_Y, WIDTH, HEIGHT);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMddhhmmss");

        for (int i = 0; i < 10000; i++) {
            BufferedImage screenShot = robot.createScreenCapture(screenRect);
            String date = simpleDateFormat.format(new Date());
            ImageIO.write(screenShot, "PNG", new File("screenShot-" + date + ".png"));
            Thread.sleep(1000);
        }
    }

//    @Test
    public void takeScreenShots() throws AWTException, InterruptedException, IOException {
        screenShotOnDisk();
    }


//    @Test
    public void findMissingColors() throws IOException, AWTException {
        Map<Integer, Integer> colorMapping = new BoardGame(new ScreenShot()).getColorMapping();

        InputStream resourceAsStream = UtilitiesForDev.class.getClassLoader().getResourceAsStream("others.png");
        BufferedImage screenShot = ImageIO.read(resourceAsStream);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                int centerX = FIRST_TILE_CENTER_X + SEPARATION_X * j;
                int centerY = FIRST_TILE_CENTER_Y + SEPARATION_Y * i;
                int rgbTile = screenShot.getRGB(centerX - COLOR_SHIFT, centerY);
                if (colorMapping.get(rgbTile) == null)
                    System.out.println(i + " " + j + " : " + rgbTile);
            }
        }
    }

//    @Test
    public void startGameWithMouseClic() throws AWTException, InterruptedException {
        Robot robot = new Robot();
        firstMove(robot);
    }

    private void startNewGame(Robot robot) {
        robot.mouseMove(2716, 237);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    private void firstMove(Robot robot) throws InterruptedException {
        robot.mouseMove(2536, 662);
        Thread.sleep(200);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        Thread.sleep(200);
        robot.mouseMove(2620, 662);
        Thread.sleep(200);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

}
