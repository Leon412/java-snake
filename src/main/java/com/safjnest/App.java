package com.safjnest;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    private static GraphicsContext context;
    private static GameLoop loop;

    public enum Direction {
        UP, DOWN, RIGHT, LEFT
    }

    @Override
    public void start(Stage stage) throws IOException {
        Grid grid = new Grid(null, null);

        Snake snake = new Snake(grid);
        grid.setSnake(snake);

        Food food = new Food(grid.getRandomPoint());
        grid.setFood(food);
        
        StackPane root = new StackPane();
        Canvas canvas = new Canvas(grid.getWidth(), grid.getHeight());
        context = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);

        loop = new GameLoop(grid, context);

        canvas.setOnKeyPressed(new KeyHandler(loop));

        Painter.paint(grid, context);
        root.getChildren().add(canvas);

        Scene scene = new Scene(root);

        stage.setTitle("Snake");
        stage.getIcons().add(new Image("/snake-icon.png"));
        stage.setScene(scene);
        stage.show();

        loop.start();
    }

    public static void reset() {
        loop.unpause();
        loop.setGrid(new Grid(null, null));

        Snake snake = new Snake(loop.getGrid());
        loop.getGrid().setSnake(snake);

        Food food = new Food(loop.getGrid().getRandomPoint());
        loop.getGrid().setFood(food);
    }

    public static void main(String[] args) {
        launch();
    }
}