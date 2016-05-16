package com.beugnet;

import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardGameTests {

    @Test
    public void shouldBuildGameTiles() throws IOException {
        InputStream resourceAsStream = UtilitiesForDev.class.getClassLoader().getResourceAsStream("test-input.png");
        BufferedImage image = ImageIO.read(resourceAsStream);
        BoardGame boardGame = new BoardGame(new ScreenShot(image));
        Tile[][] gameZone = boardGame.getGameZone();

        int[][] expected = {
                {0, 0, 0, 0, 0, 0, 0},
                {3, 10, 0, 3, 11, 5, 7},
                {13, 1, 6, 6, 12, 12, 1},
                {7, 12, 11, 1, 6, 13, 5},
                {2, 11, 15, 4, 11, 4, 3},
                {7, 1, 4, 13, 6, 1, 8},
                {4, 9, 3, 8, 13, 2, 7,},
                {3, 11, 2, 3, 7, 6, 13}
        };

        Tile tile = gameZone[5][2];
        assertThat(tile.hasLinkLeft()).isFalse();
        assertThat(tile.hasLinkRight()).isTrue();
        assertThat(tile.hasLinkUp()).isTrue();
        assertThat(tile.hasLinkDown()).isFalse();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++)
                assertThat(expected[i][j]).isEqualTo(gameZone[i][j].getValue());
        }
        assertThat(boardGame.isValidBoardGame()).isTrue();
    }
}
