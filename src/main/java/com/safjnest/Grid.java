package com.safjnest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javafx.scene.paint.Color;

public class Grid {
    public static final Color BACKGROUND_COLOR = Color.BLACK;

    private final Random random;

    private int rows;
    private int cols;
    private double tileSize;

    private Settings settings;

    private Snake snake;
    private List<Point> food;
    private Point nextFood;

    public Grid(Settings settings, Snake snake, List<Point> food, Point nextFood) {
        this.settings = settings;
        this.random = new Random();
        this.rows = 20;
        this.cols = 20;
        this.tileSize = 30f;

        this.snake = snake;
        this.food = food;
        this.nextFood = nextFood;
    }

    public double getWidth() {
        return rows * tileSize;
    }

    public double getHeight() {
        return cols * tileSize;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public Point getRandomPoint() {
        List<Point> availablePoints = new ArrayList<>();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Point point = new Point(row, col);
                if (!snake.getPoints().contains(point)) {
                    availablePoints.add(point);
                }
            }
        }

        return availablePoints.isEmpty() ? null : availablePoints.get(random.nextInt(availablePoints.size()));
    }

    public List<Point> getRandomPoints() {
        List<Point> availablePoints = new ArrayList<>();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Point point = new Point(row, col);
                if (!snake.getPoints().contains(point)) {
                    availablePoints.add(point);
                }
            }
        }

        if (availablePoints.size() < settings.getFoodCount()) {
            throw new IllegalArgumentException("Not enough available points to return " + settings.getFoodCount() + " points");
        }

        if (settings.getFoodCount() == 1) {
            List<Point> singlePointList = new ArrayList<>();
            singlePointList.add(availablePoints.get(random.nextInt(availablePoints.size())));
            return singlePointList;
        }

        Collections.shuffle(availablePoints, random);

        return availablePoints.subList(0, settings.getFoodCount());
    }
    
    public Point wrap(Point point) {
        return new Point(Math.floorMod(point.getX(), rows), Math.floorMod(point.getY(), cols));
    }

    public double getTileSize() {
        return tileSize;
    }

    public Snake getSnake() {
        return snake;
    }

    public List<Point> getFood() {
        return food;
    }

    public Point getNextFood() {
        return nextFood;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public void setFood(List<Point> food) {
        this.food = food;
    }

    public void setNextFood(Point food) {
        this.nextFood = food;
    }

    private void spawnFood(int i) {
        if(settings.isNextFood()) {
            food.set(i, nextFood);
            nextFood = getRandomPoint();
        }
        else {
            food.remove(i);
            food.add(getRandomPoint());
        }
    }

    public void update() {
        for(int i = 0; i < food.size(); i++) {
            if(food.get(i).equals(snake.getHead())) {
                snake.grow();
                spawnFood(i);
                return;
            }
        }
        snake.move();
    }

}
