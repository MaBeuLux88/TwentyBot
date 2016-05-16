package com.beugnet;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        try {
            new TwentyBot().startGame();
        } catch (AWTException e) {
            System.err.println("Error from the Robot.");
            e.printStackTrace();
        }
    }
}
