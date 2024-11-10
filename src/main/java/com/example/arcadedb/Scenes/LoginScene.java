package com.example.arcadedb.Scenes;

import com.example.arcadedb.AppResources;
import com.example.arcadedb.Panes.LoginPane;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

/**
 * A JavaFX scene for the login screen.
 */
public class LoginScene extends Scene {

    /**
     * Constructs a LoginScene with the specified properties and initializes its components.
     */
    public LoginScene() {
        super(new LoginPane(), AppResources.SCREEN_WIDTH, AppResources.SCREEN_HEIGHT, Color.BLACK);

    }
}
