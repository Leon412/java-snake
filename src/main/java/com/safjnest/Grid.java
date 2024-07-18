package com.safjnest;

import java.util.Random;

import javafx.scene.paint.Color;

public class Grid {
    public static final Color BACKGROUND_COLOR = Color.BLACK;

    private int rows;
    public int getRows() {
        return rows;
    }

    private int cols;
    public int getCols() {
        return cols;
    }

    private double tileSize;

    private Snake snake;
    private Food food;

    public Grid(Snake snake, Food food) {
        this.rows = 30;
        this.cols = 20;
        this.tileSize = 15f;

        this.snake = snake;
        this.food = food;
    }

    public double getWidth() {
        return this.rows * this.tileSize;
    }

    public double getHeight() {
        return this.cols * this.tileSize;
    }

    public Point getRandomPoint() {
        Random random = new Random();
        Point point;
        do {
            point = new Point(random.nextInt(rows), random.nextInt(cols));
        } while (snake.getPoints().contains(point));
        return point;
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

    public Food getFood() {
        return food;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public void update() {
        if(food.getPoint().equals(snake.getHead())) {
            this.snake.grow();
            food.setPoint(getRandomPoint());
        }
        else {
            this.snake.move();
        }
    }

}
