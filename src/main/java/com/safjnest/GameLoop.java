package com.safjnest;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

public class GameLoop extends AnimationTimer {
    private int fps;
    private long updateTime;
    private long lastUpdate;
    private boolean paused;

    private Grid grid;
    private GraphicsContext context;

    public GameLoop(final Grid grid, final GraphicsContext context) {
        this.grid = grid;
        this.context = context;
        fps = 20;
        updateTime = 1_000_000_000 / fps;
        lastUpdate = 0;
        paused = false;
    }

    @Override
    public void handle(long now) {
        if (!isPaused() && (now - lastUpdate >= updateTime)) {
            update();
            lastUpdate = now;
        }
    }

    private void update() {
        grid.update();
        Painter.paint(grid, context);

        if (!grid.getSnake().isSafe()) {
            pause();
            Painter.paintGameOver(grid, context);
        }
    }

    public void pause() {
        paused = true;
    }

    public void unpause() {
        paused = false;
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public boolean isPaused() {
        return paused;
    }
    
}
