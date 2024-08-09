package com.safjnest;

import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Painter {

    private static void paintPoint(Point point, GraphicsContext gc, double tileSize) {
        gc.fillRect(point.getX() * tileSize, point.getY() * tileSize, tileSize, tileSize);
        //gc.fillRoundRect(point.getX() * tileSize, point.getY() * tileSize, tileSize, tileSize, 4, 4);
    }

    private static void paintGrid(GraphicsContext gc, double width, double height) {
        gc.setFill(Grid.BACKGROUND_COLOR);
        gc.fillRect(0, 0, width, height);
    }

    private static void paintFood(GraphicsContext gc, List<Point> foods, double tileSize) {
        gc.setFill(Settings.FOOD_COLOR);
        for(Point food : foods) {
            paintPoint(food, gc, tileSize);
        }
    }

    private static void paintNextFood(Settings settings, GraphicsContext gc, Point food, double tileSize) {
        if(!settings.isNextFood()) return;

        gc.setFill(Settings.NEXT_FOOD_COLOR);
        paintPoint(food, gc, tileSize);
    }

    private static void paintSnake(GraphicsContext gc, Snake snake, double tileSize) {
        gc.setFill(Snake.COLOR);
        snake.getPoints().forEach(point -> paintPoint(point, gc, tileSize));
        if (!snake.isSafe()) {
            gc.setFill(Snake.DEAD_COLOR);
            paintPoint(snake.getHead(), gc, tileSize);
        }
    }

    private static void paintScore(GraphicsContext gc, int points, double width, double height) {
        gc.setFill(Color.WHITE);
        gc.fillText("Score : " + 100 * points, 10, height - 10);
    }

    public static void paint(Settings settings, Grid grid, GraphicsContext gc) {
        paintGrid(gc, grid.getWidth(), grid.getHeight());
        paintFood(gc, grid.getFood(), grid.getTileSize());
        paintNextFood(settings, gc, grid.getNextFood(), grid.getTileSize());
        paintSnake(gc, grid.getSnake(), grid.getTileSize());
        paintScore(gc, grid.getSnake().getPoints().size()-1, grid.getWidth(), grid.getHeight());
    }

    public static void paintGameOver(Grid grid, GraphicsContext gc) {
        gc.setFill(Color.AQUAMARINE);
        gc.fillText("Hit RETURN to reset.", 10, 20);
    }
}
