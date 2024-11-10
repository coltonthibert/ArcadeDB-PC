package com.example.arcadedb.Scenes;

import com.example.arcadedb.AppResources;
import com.example.arcadedb.Panes.DatabasePane;
import javafx.scene.Scene;

/**
 * A JavaFX scene for the database screen.
 */
public class DatabaseScene extends Scene {
    /**
     * Constructs a DatabaseScene with the specified properties and initializes its components.
     */
    public DatabaseScene() {
        super(new DatabasePane(), AppResources.SCREEN_WIDTH, AppResources.SCREEN_HEIGHT);
        getStylesheets().add("css/tableStyles.css");

        //Refresh the table every time the scene is shown
        DatabasePane.refreshTable();

        //listen for the R key to be pressed to manually refresh the table
        setOnKeyPressed(e -> {
            if (e.getCode().toString().equals("R")) {
                DatabasePane.refreshTable();
            }
        });
    }
}
