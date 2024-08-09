package com.safjnest;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

public class GameLoop extends AnimationTimer {
    private Settings settings;

    private int fps;
    private long updateTime;
    private long lastUpdate;
    private boolean paused;

    private Grid grid;
    private GraphicsContext context;

    public GameLoop(Settings settings, final Grid grid, final GraphicsContext context) {
        this.settings = settings;
        this.grid = grid;
        this.context = context;
        fps = settings.fps;
        updateTime = 1_000_000_000 / fps;
        lastUpdate = 0;
        paused = false;
    }

    @Override
    public void handle(long now) {
        if ((now - lastUpdate >= updateTime) && !isPaused()) {
            update();
            lastUpdate = now;
        }
    }

    private void update() {
        grid.update();
        Painter.paint(settings, grid, context);

        if (!grid.getSnake().isSafe()) {
            pause();
            Painter.paintGameOver(grid, context);
        }
    }

    public void updateSettings() {
        fps = settings.getFps();
        updateTime = 1_000_000_000 / fps;
    }

    public void pause() {
        paused = true;
    }

    public void pause(boolean pause) {
        this.paused = pause;
    }

    public void unpause() {
        paused = false;
    }

    public Settings getSettings() {
        return settings;
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public void setContext(GraphicsContext context) {
        this.context = context;
    }

    public boolean isPaused() {
        return paused;
    }
    
}
