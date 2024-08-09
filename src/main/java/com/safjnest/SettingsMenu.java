package com.safjnest;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SettingsMenu extends Application {
    private Settings settings;
    private GameLoop gameLoop;
    private KeyHandler keyHandler;

    public SettingsMenu(Settings settings, GameLoop gameLoop, KeyHandler keyHandler) {
        this.settings = settings;
        this.gameLoop = gameLoop;
        this.keyHandler = keyHandler;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Settings");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Snake speed setting
        Label speedLabel = new Label("Snake Speed:");
        GridPane.setConstraints(speedLabel, 0, 0);
        TextField speedField = new TextField(String.valueOf(settings.getFps()));
        GridPane.setConstraints(speedField, 1, 0);

        // Key settings
        Label upKeyLabel = new Label("Up Key:");
        GridPane.setConstraints(upKeyLabel, 0, 1);
        TextField upKeyField = new TextField(settings.getUpKey().getName());
        GridPane.setConstraints(upKeyField, 1, 1);

        Label downKeyLabel = new Label("Down Key:");
        GridPane.setConstraints(downKeyLabel, 0, 2);
        TextField downKeyField = new TextField(settings.getDownKey().getName());
        GridPane.setConstraints(downKeyField, 1, 2);

        Label leftKeyLabel = new Label("Left Key:");
        GridPane.setConstraints(leftKeyLabel, 0, 3);
        TextField leftKeyField = new TextField(settings.getLeftKey().getName());
        GridPane.setConstraints(leftKeyField, 1, 3);

        Label rightKeyLabel = new Label("Right Key:");
        GridPane.setConstraints(rightKeyLabel, 0, 4);
        TextField rightKeyField = new TextField(settings.getRightKey().getName());
        GridPane.setConstraints(rightKeyField, 1, 4);

        // Save button
        Button saveButton = new Button("Save");
        GridPane.setConstraints(saveButton, 1, 5);

        saveButton.setOnAction(e -> {
            try {
                int speed = Integer.parseInt(speedField.getText());
                settings.setFps(speed);

                settings.setUpKey(KeyCode.getKeyCode(upKeyField.getText().toUpperCase()));
                settings.setDownKey(KeyCode.getKeyCode(downKeyField.getText().toUpperCase()));
                settings.setLeftKey(KeyCode.getKeyCode(leftKeyField.getText().toUpperCase()));
                settings.setRightKey(KeyCode.getKeyCode(rightKeyField.getText().toUpperCase()));

                gameLoop.updateSettings();
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Invalid input for speed");
                alert.show();
            }
        });

        grid.getChildren().addAll(speedLabel, speedField, upKeyLabel, upKeyField, downKeyLabel, downKeyField,
                leftKeyLabel, leftKeyField, rightKeyLabel, rightKeyField, saveButton);

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}