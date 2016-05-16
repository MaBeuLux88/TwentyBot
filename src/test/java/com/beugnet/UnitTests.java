package com.beugnet;

import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class UnitTests {

    private static final int LINK_CODE = -11908790;
    private static final int CORNER_X = 2250;
    private static final int CORNER_Y = 10;
    private static final int WIDTH = 570;
    private static final int HEIGHT = 870;
    private static final int FIRST_TILE_CENTER_X = 45;
    private static final int FIRST_TILE_CENTER_Y = 229;
    private static final int SEPARATION_X = 80;
    private static final int SEPARATION_Y = 85;
    private static final int COLOR_SHIFT = 25;
    private static final int LINK_SHIFT_X = SEPARATION_X / 2;
    private static final int LINK_SHIFT_Y = SEPARATION_Y / 2;
    private Tile[][] gameZone = new Tile[8][7];
    private Map<Integer, Integer> colorMapping = new HashMap<>();

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

    @Test
    public void takeScreenShots() throws AWTException, InterruptedException, IOException {
        screenShotOnDisk();
    }

    @Test
    public void testScreenShot() throws IOException, AWTException {

        initMap();

        InputStream resourceAsStream = UnitTests.class.getClassLoader().getResourceAsStream("test-input.png");
        BufferedImage screenShot = ImageIO.read(resourceAsStream);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                int centerX = FIRST_TILE_CENTER_X + SEPARATION_X * j;
                int centerY = FIRST_TILE_CENTER_Y + SEPARATION_Y * i;
                int rgbTile = screenShot.getRGB(centerX - COLOR_SHIFT, centerY);
                int rgbLeft = screenShot.getRGB(centerX - LINK_SHIFT_X, centerY);
                int rgbRight = screenShot.getRGB(centerX + LINK_SHIFT_X, centerY);
                int rgbDown = screenShot.getRGB(centerX, centerY + LINK_SHIFT_Y);
                int rgbUp = screenShot.getRGB(centerX, centerY - LINK_SHIFT_Y);

                boolean hasLinkLeft = isLink(rgbLeft);
                boolean hasLinkRight = isLink(rgbRight);
                boolean hasLinkDown = isLink(rgbDown);
                boolean hasLinkUp = isLink(rgbUp);

                Tile tile = new Tile(colorMapping.get(rgbTile), hasLinkLeft, hasLinkRight, hasLinkDown, hasLinkUp);
                gameZone[i][j] = tile;
            }
        }

        Tile[][] expected = {
                {
                        new Tile(0, false, false, false, false),
                        new Tile(0, false, false, false, false),
                        new Tile(0, false, false, false, false),
                        new Tile(0, false, false, false, false),
                        new Tile(0, false, false, false, false),
                        new Tile(0, false, false, false, false),
                        new Tile(0, false, false, false, false)
                },
                {
                        new Tile(3, false, true, false, false),
                        new Tile(10, true, false, true, false),
                        new Tile(0, false, false, false, false),
                        new Tile(3, false, false, false, false),
                        new Tile(11, false, false, true, false),
                        new Tile(5, false, false, false, false),
                        new Tile(7, false, false, true, false)
                },
                {
                        new Tile(13, false, false, false, false),
                        new Tile(1, false, false, false, true),
                        new Tile(6, false, false, false, false),
                        new Tile(6, false, false, false, false),
                        new Tile(12, false, false, false, true),
                        new Tile(12, false, false, false, false),
                        new Tile(1, false, false, false, true)
                },
                {
                        new Tile(7, false, false, true, false),
                        new Tile(12, false, true, false, false),
                        new Tile(11, true, true, false, false),
                        new Tile(1, true, false, false, false),
                        new Tile(6, false, true, false, false),
                        new Tile(13, true, true, false, false),
                        new Tile(5, true, false, false, false)
                },
                {
                        new Tile(2, false, true, false, true),
                        new Tile(11, true, false, false, false),
                        new Tile(15, false, false, true, false),
                        new Tile(4, false, true, false, false),
                        new Tile(11, true, false, false, false),
                        new Tile(4, false, false, false, false),
                        new Tile(3, false, false, true, false)
                },
                {
                        new Tile(7, false, false, false, false),
                        new Tile(1, false, false, false, false),
                        new Tile(4, false, true, false, true),
                        new Tile(13, true, false, false, false),
                        new Tile(6, false, true, true, false),
                        new Tile(1, true, false, false, false),
                        new Tile(8, false, false, false, true)
                },
                {
                        new Tile(4, false, true, true, false),
                        new Tile(9, true, false, false, false),
                        new Tile(3, false, true, false, false),
                        new Tile(8, true, false, false, false),
                        new Tile(13, false, false, false, true),
                        new Tile(2, false, false, false, false),
                        new Tile(7, false, false, false, false)
                },
                {
                        new Tile(3, false, false, false, true),
                        new Tile(11, false, true, false, false),
                        new Tile(2, true, false, false, false),
                        new Tile(3, false, false, false, false),
                        new Tile(7, false, true, false, false),
                        new Tile(6, true, true, false, false),
                        new Tile(13, true, false, false, false)
                }
        };

        assertThat(gameZone).as("Game Zone").isEqualTo(expected);
    }

    @Test
    public void findMissingColors() throws IOException, AWTException {
        initMap();

        InputStream resourceAsStream = UnitTests.class.getClassLoader().getResourceAsStream("others.png");
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


    private boolean isLink(int rgbLeft) {
        return rgbLeft == LINK_CODE;
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
        colorMapping.put(-16721937, 14);
        colorMapping.put(-8093052, 15);
        colorMapping.put(-14605057, 16);
        colorMapping.put(-572929, 17);
        colorMapping.put(-19787, 18);
        colorMapping.put(-6235, 19);
    }
}
