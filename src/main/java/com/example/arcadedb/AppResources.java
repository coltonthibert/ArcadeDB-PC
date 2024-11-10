package com.example.arcadedb;

import javafx.scene.image.Image;
import javafx.scene.text.Font;

public class AppResources {
    public static final int SCREEN_WIDTH = 1024;
    public static final int SCREEN_HEIGHT = 550;

    public static final String APPLICATION_TITLE = "ArcadeDB";
    public static final Image APPLICATION_ICON = new Image(AppResources.class.getResource("/graphics/dbicon.png").toExternalForm());

    public static Image APPLICATON_BACKGROUND = new Image(AppResources.class.getResource("/graphics/background1.gif").toExternalForm());
    public static Image APPLICATON_BACKGROUND2 = new Image(AppResources.class.getResource("/graphics/background2.png").toExternalForm());


    public static final Font MAIN_FONT = Font.loadFont(Main.class.getResourceAsStream("/fonts/grishenko_nbp.ttf"), 18);

    public static final Font CREDITS_FONT = Font.loadFont(Main.class.getResourceAsStream("/fonts/grishenko_nbp.ttf"), 11);
    public static final Font OPTIONS_FONT = Font.loadFont(Main.class.getResourceAsStream("/fonts/grishenko_nbp.ttf"), 16);

    public static Font getAppFont(int size) {
        return Font.loadFont(Main.class.getResourceAsStream("/fonts/grishenko_nbp.ttf"), size);
    }
}
