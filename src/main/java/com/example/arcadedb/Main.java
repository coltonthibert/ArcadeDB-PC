package com.example.arcadedb;

import com.example.arcadedb.Database.Database;
import com.example.arcadedb.Scenes.DatabaseScene;
import com.example.arcadedb.Scenes.LoginScene;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    // Create a static instance of the main stage
    public static Stage mainStage;
    // Create a static instance of the login scene
    public static LoginScene loginScene = new LoginScene();

    @Override
    public void start(Stage stage) throws IOException {
        // Initialize the JavaFX components
        mainStage = stage;

        // Set the title, scene, and show the stage
        mainStage.setTitle("ArcadeDB");
        mainStage.setResizable(false);
        mainStage.getIcons().add(AppResources.APPLICATION_ICON);
        mainStage.setScene(loginScene);
        mainStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
