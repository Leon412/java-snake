package com.safjnest;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Settings {
    public static final Color BACKGROUND_COLOR = Color.BLACK;
    public static final Color SNAKE_COLOR = Color.WHITE;
    public static final Color DEAD_COLOR = Color.RED;
    public static final Color FOOD_COLOR = Color.BROWN;
    public static final Color NEXT_FOOD_COLOR = Color.ROSYBROWN;

    public static final int rows = 20;
    public static final int cols = 30;
    public static final double tileSize = 15;

    private KeyCode upKey = KeyCode.W;
    private KeyCode downKey = KeyCode.S;
    private KeyCode leftKey = KeyCode.A;
    private KeyCode rightKey = KeyCode.D;
    private KeyCode okKey = KeyCode.ENTER;
    private KeyCode escKey = KeyCode.ESCAPE;

    public int fps = 20;

    public boolean wrap = true;
    public boolean nextFood = true;
    public int foodCount = 1;

    public static Color getBackgroundColor() {
        return BACKGROUND_COLOR;
    }
    public static Color getSnakeColor() {
        return SNAKE_COLOR;
    }
    public static Color getDeadColor() {
        return DEAD_COLOR;
    }
    public static Color getFoodColor() {
        return FOOD_COLOR;
    }
    public static int getRows() {
        return rows;
    }
    public static int getCols() {
        return cols;
    }
    public static double getTilesize() {
        return tileSize;
    }
    public KeyCode getUpKey() {
        return upKey;
    }
    public void setUpKey(KeyCode upKey) {
        this.upKey = upKey;
    }
    public KeyCode getDownKey() {
        return downKey;
    }
    public void setDownKey(KeyCode downKey) {
        this.downKey = downKey;
    }
    public KeyCode getLeftKey() {
        return leftKey;
    }
    public void setLeftKey(KeyCode leftKey) {
        this.leftKey = leftKey;
    }
    public KeyCode getRightKey() {
        return rightKey;
    }
    public void setRightKey(KeyCode rightKey) {
        this.rightKey = rightKey;
    }
    public KeyCode getOkKey() {
        return okKey;
    }
    public void setOkKey(KeyCode okKey) {
        this.okKey = okKey;
    }
    public KeyCode getEscKey() {
        return escKey;
    }
    public void setEscKey(KeyCode escKey) {
        this.escKey = escKey;
    }
    public int getFps() {
        return fps;
    }
    public void setFps(int fps) {
        this.fps = fps;
    }
    public boolean isWrap() {
        return wrap;
    }
    public void setWrap(boolean wrap) {
        this.wrap = wrap;
    }
    public boolean isNextFood() {
        return nextFood;
    }
    public void setNextFood(boolean nextFood) {
        this.nextFood = nextFood;
    }
    public int getFoodCount() {
        return foodCount;
    }
    public void setFoodCount(int foodCount) {
        this.foodCount = foodCount;
    }

    
}
