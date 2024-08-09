package com.safjnest;

import java.util.HashSet;
import java.util.Set;

import com.safjnest.App.Direction;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyHandler {
    private final Set<KeyCode> pressedKeys = new HashSet<>();

    private final KeyCode upKey = KeyCode.W;
    private final KeyCode downKey = KeyCode.S;
    private final KeyCode leftKey = KeyCode.A;
    private final KeyCode rightKey = KeyCode.D;
    private final KeyCode okKey = KeyCode.ENTER;
    private final KeyCode escKey = KeyCode.ESCAPE;

    private final GameLoop loop;

    public KeyHandler(GameLoop loop) {
        this.loop = loop;
    }

    public void handleKeyPressed(KeyEvent e) {
        KeyCode code = e.getCode();

        if (!pressedKeys.contains(code)) {
            pressedKeys.add(code);

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
            } else if (code == escKey) {
                boolean prevPauseState = loop.isPaused();
                loop.pause(!prevPauseState);
            }
        }
    }

    public void handleKeyReleased(KeyEvent event) {
        KeyCode code = event.getCode();
        pressedKeys.remove(code);
    }
}