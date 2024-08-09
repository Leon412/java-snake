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
        Settings settings = new Settings();
        
        loop = new GameLoop(settings, new Grid(settings, null, null, null), null);

        setGrid();

        StackPane root = new StackPane();
        Canvas canvas = new Canvas(loop.getGrid().getWidth(), loop.getGrid().getHeight());
        context = canvas.getGraphicsContext2D();

        loop.setContext(context);

        Painter.paint(settings, loop.getGrid(), context);

        canvas.setFocusTraversable(true);

        KeyHandler keyHandler = new KeyHandler(loop);
        canvas.setOnKeyPressed(keyHandler::handleKeyPressed);
        canvas.setOnKeyReleased(keyHandler::handleKeyReleased);

        root.getChildren().add(canvas);
        Scene scene = new Scene(root);

        stage.setTitle("Snake");
        stage.getIcons().add(new Image("/snake-icon.png"));
        stage.setScene(scene);
        stage.show();

        loop.start();

        //Stage settingsStage = new Stage();
        //SettingsMenu settingsMenu = new SettingsMenu(settings, loop, keyHandler);
        //settingsMenu.start(settingsStage);
    }

    private static void setGrid() {
        loop.getGrid().setSnake(new Snake(loop.getSettings(), loop.getGrid()));

        loop.getGrid().setFood(loop.getGrid().getRandomPoints());

        if(loop.getSettings().isNextFood()) {
            loop.getGrid().setNextFood(loop.getGrid().getRandomPoint());
        }
    }

    public static void reset() {
        loop.unpause();

        setGrid();
    }

    public static void main(String[] args) {
        launch();
    }
}