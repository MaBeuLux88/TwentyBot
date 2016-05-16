package com.beugnet;

import java.awt.*;
import java.awt.event.InputEvent;

public class TwentyBot extends Robot {
    public TwentyBot() throws AWTException {
    }

    public void startGame() {
        startNewGame();
        int invalideBoards = 0;
        while (invalideBoards < 20) {
            BoardGame board = new BoardGame(new ScreenShot());
            if (board.isValidBoardGame()) {
                invalideBoards = 0;
                makeMove(board.getGameZone());
            } else {
                invalideBoards++;
            }
        }
    }

    private void makeMove(Tile[][] gameZone) {
        GameBrain gameBrain = new GameBrain(gameZone);
//        gameBrain.get
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void startNewGame() {
        mouseMove(2716, 237);
        mousePress(InputEvent.BUTTON1_MASK);
        mouseRelease(InputEvent.BUTTON1_MASK);
        sleep(500);
    }
}
