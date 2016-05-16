package com.beugnet;

import org.testng.annotations.Test;

import java.awt.image.BufferedImage;

import static org.assertj.core.api.Assertions.assertThat;

public class ScreenShotTests {

    @Test
    public void shouldAlwaysHaveAScreenShot() {
        ScreenShot screenShot = new ScreenShot();
        BufferedImage image = screenShot.getImage();
        assertThat(image).isNotNull();
    }

}
