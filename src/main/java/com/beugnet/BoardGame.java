package com.beugnet;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class BoardGame {

    private static final int LINK_CODE = -11908790;
    private static final int FIRST_TILE_CENTER_X = 45;
    private static final int FIRST_TILE_CENTER_Y = 229;
    private static final int SEPARATION_X = 80;
    private static final int SEPARATION_Y = 85;
    private static final int COLOR_SHIFT = 25;
    private static final int LINK_SHIFT_X = SEPARATION_X / 2;
    private static final int LINK_SHIFT_Y = SEPARATION_Y / 2;
    private Tile[][] gameZone = new Tile[8][7];
    private Map<Integer, Integer> colorMapping = new HashMap<>();

    public BoardGame(ScreenShot screenShot) {
        initMap();
        BufferedImage image = screenShot.getImage();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                int centerX = FIRST_TILE_CENTER_X + SEPARATION_X * j;
                int centerY = FIRST_TILE_CENTER_Y + SEPARATION_Y * i;
                Coordinate center = new Coordinate(centerX, centerY);

                int rgbTile = image.getRGB(centerX - COLOR_SHIFT, centerY);
                int rgbLeft = image.getRGB(centerX - LINK_SHIFT_X, centerY);
                int rgbRight = image.getRGB(centerX + LINK_SHIFT_X, centerY);
                int rgbDown = image.getRGB(centerX, centerY + LINK_SHIFT_Y);
                int rgbUp = image.getRGB(centerX, centerY - LINK_SHIFT_Y);

                boolean hasLinkLeft = isLink(rgbLeft);
                boolean hasLinkRight = isLink(rgbRight);
                boolean hasLinkDown = isLink(rgbDown);
                boolean hasLinkUp = isLink(rgbUp);

                Tile tile = new Tile(center, colorMapping.get(rgbTile), hasLinkLeft, hasLinkRight, hasLinkDown, hasLinkUp);
                gameZone[i][j] = tile;
            }
        }
    }

    public Map<Integer, Integer> getColorMapping() {
        return colorMapping;
    }

    public Tile[][] getGameZone() {
        return gameZone;
    }

    public boolean isValidBoardGame() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                if (gameZone[i][j].getValue() == null)
                    return false;
            }
        }
        return true;
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
