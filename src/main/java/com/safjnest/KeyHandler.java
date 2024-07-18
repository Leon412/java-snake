package com.safjnest;

import com.safjnest.App.Direction;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyHandler implements EventHandler<KeyEvent> {
    private final GameLoop loop;

    private final KeyCode upKey = KeyCode.W;
    private final KeyCode downKey = KeyCode.S;
    private final KeyCode leftKey = KeyCode.A;
    private final KeyCode rightKey = KeyCode.D;
    private final KeyCode okKey = KeyCode.ENTER;

    public KeyHandler(GameLoop loop) {
        this.loop = loop;
    }

    @Override
    public void handle(KeyEvent e) {
        KeyCode code = e.getCode();
        if (code == upKey) {
            loop.getGrid().getSnake().setMove(Direction.UP);
        } else if (code == downKey) {
            loop.getGrid().getSnake().setMove(Direction.DOWN);
        } else if (code == leftKey) {
            loop.getGrid().getSnake().setMove(Direction.LEFT);
        } else if (code == rightKey) {
            loop.getGrid().getSnake().setMove(Direction.RIGHT);
        } else if (code == okKey) {
            if(loop.isPaused()) {
                App.reset();
            }
        }
    }
}