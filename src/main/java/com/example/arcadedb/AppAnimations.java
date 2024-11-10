package com.example.arcadedb;

import javafx.animation.*;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * A class containing static methods for creating animations in the app.
 */
public class AppAnimations {
    /**
     * Creates a fade out animation for the specified node.
     * @param duration The duration of the animation.
     * @param node The node to fade out.
     * @return
     */
    public static FadeTransition fadeIn(Duration duration, Node node){
        FadeTransition fadeTransition = new FadeTransition(duration, node);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        return fadeTransition;
    }

    public static SequentialTransition fadeInOut(Duration duration, Node node, int pauseDuration){
        FadeTransition fadeTransition = new FadeTransition(duration, node);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(pauseDuration));
        FadeTransition fadeOutTransition = new FadeTransition(duration, node);
        fadeOutTransition.setFromValue(1);
        fadeOutTransition.setToValue(0);
        SequentialTransition sequentialTransition = new SequentialTransition(fadeTransition, pauseTransition, fadeOutTransition);
        return sequentialTransition;
    }

    /**
     * A set of animations that occur when the user logs in.
     * @param loadingIcon The loading icon.
     * @param logo The logo of the app.
     */
    public static ParallelTransition logIn(Node loadingIcon, Node logo) {
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1), loadingIcon);
        rotateTransition.setByAngle(-360);
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        rotateTransition.setCycleCount(Timeline.INDEFINITE);
        // move logo up
        TranslateTransition moveLogo = new TranslateTransition(Duration.seconds(0.5), logo);
        moveLogo.setFromY(0);
        moveLogo.setToY(25);
        // move loadingIcon down
        TranslateTransition moveLoadingIcon = new TranslateTransition(Duration.seconds(0.5), loadingIcon);
        moveLogo.setFromY(0);
        moveLogo.setToY(-45);
        // fadeIn Logo
        FadeTransition fadeLoadingIcon = new FadeTransition(Duration.seconds(0.5), loadingIcon);
        fadeLoadingIcon.setFromValue(0);
        fadeLoadingIcon.setToValue(1);

        ParallelTransition parallelTransition = new ParallelTransition(fadeLoadingIcon, rotateTransition, moveLoadingIcon, moveLogo);
        return parallelTransition;
    }

    /**
     * A series of animations that occur when the user successfully logs in.
     * @param loadingIcon The loading icon.
     * @param logo The logo of the app.
     * @param welcomeText The welcome text that appears after the user logs in.
     * @param creditsText The credits text that fades out after the user logs in.
     * @return
     */
    public static SequentialTransition loginSuccess(Node loadingIcon, Node logo, Node welcomeText, Node creditsText) {
        PauseTransition wait1 = new PauseTransition(Duration.seconds(0.9));
        FadeTransition fadeOutLoadingIcon = new FadeTransition(Duration.seconds(0.5), loadingIcon);
        fadeOutLoadingIcon.setFromValue(1);
        fadeOutLoadingIcon.setToValue(0);
        FadeTransition fadeInWelcomeText = new FadeTransition(Duration.seconds(0.8), welcomeText);
        fadeInWelcomeText.setFromValue(0);
        fadeInWelcomeText.setToValue(1);
        FadeTransition fadeOutCredits = new FadeTransition(Duration.seconds(0.8), creditsText);
        fadeOutCredits.setFromValue(1);
        fadeOutCredits.setToValue(0);
        PauseTransition wait2 = new PauseTransition(Duration.seconds(0.8));

        FadeTransition fadeOutWelcomeText = new FadeTransition(Duration.seconds(1.0), welcomeText);
        fadeOutWelcomeText.setFromValue(1);
        fadeOutWelcomeText.setToValue(0);

        SequentialTransition sequentialTransition = new SequentialTransition(wait1, fadeOutLoadingIcon, fadeOutCredits, fadeInWelcomeText, wait2, fadeOutWelcomeText);

        return sequentialTransition;
    }

    /**
     * A series of animations that occur to initialize the main menu/database scene.
     * @param databaseUI The database UI or TableView.
     * @param logo The logo of the app.
     * @param option1 The first option in the main menu.
     * @param option2 The second option in the main menu.
     * @param option3 The third option in the main menu.
     * @param option4 The fourth option in the main menu.
     * @return
     */
    public static ParallelTransition mainScreenStart(Node databaseUI, Node logo, Node option1, Node option2, Node option3, Node option4) {
        ScaleTransition scaleLogo = new ScaleTransition(Duration.seconds(0.8), logo);
        scaleLogo.setFromX(1);
        scaleLogo.setFromY(1);
        scaleLogo.setToX(0.7);
        scaleLogo.setToY(0.7);

        TranslateTransition moveLogo = new TranslateTransition(Duration.seconds(0.8), logo);
        moveLogo.setFromX(0);
        moveLogo.setToX(-355);
        moveLogo.setFromY(0);
        moveLogo.setToY(-60);

        TranslateTransition moveDatabase = new TranslateTransition(Duration.seconds(0.8), databaseUI);
        moveDatabase.setFromY(100);
        moveDatabase.setToY(0);

        FadeTransition fadeDatabase = new FadeTransition(Duration.seconds(0.8), databaseUI);
        fadeDatabase.setFromValue(0);
        fadeDatabase.setToValue(1);

        ParallelTransition parallelTransition = new ParallelTransition(scaleLogo, moveLogo, fadeDatabase, moveDatabase);
        parallelTransition.setCycleCount(1);
        return parallelTransition;
    }


    public static ParallelTransition moveOption(Node optionBG, Node optionICON, Node optionText, Float time) {
        TranslateTransition moveInBG = new TranslateTransition(Duration.seconds(time), optionBG);
        moveInBG.setFromX(-355);
        moveInBG.setToX(0);

        TranslateTransition moveInIcon = new TranslateTransition(Duration.seconds(time), optionICON);
        moveInIcon.setFromX(-355);
        moveInIcon.setToX(0);

        TranslateTransition moveInText = new TranslateTransition(Duration.seconds(time), optionText);
        moveInText.setFromX(-355);
        moveInText.setToX(0);

        ParallelTransition parallelTransition = new ParallelTransition(moveInBG, moveInIcon, moveInText);
        parallelTransition.setCycleCount(1);
        return parallelTransition;
    }
    public static ParallelTransition iconPopIn(Node icon, Float duration, Float size) {
        ScaleTransition scaleIn = new ScaleTransition(Duration.seconds(duration), icon);
        scaleIn.setFromX(0.5);
        scaleIn.setFromY(0.5);
        scaleIn.setToX(size);
        scaleIn.setToY(size);

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(duration), icon);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);


        ParallelTransition parallelTransition = new ParallelTransition(scaleIn, fadeIn);
        return parallelTransition;
    }

    public static TranslateTransition buttonHover(Node buttonText) {
        TranslateTransition bounceUp = new TranslateTransition(Duration.seconds(0.2), buttonText);
        bounceUp.setFromY(0);
        bounceUp.setToY(-6);
        return bounceUp;
    }

    public static TranslateTransition buttonHoverOff(Node buttonText) {
        TranslateTransition bounceUp = new TranslateTransition(Duration.seconds(0.2), buttonText);
        bounceUp.setFromY(-6);
        bounceUp.setToY(0);
        return bounceUp;
    }

    public static SequentialTransition buttonClick(Node buttonText) {
        ScaleTransition up = new ScaleTransition(Duration.seconds(0.07), buttonText);
        up.setFromX(1);
        up.setFromY(1);
        up.setToX(1.1);
        up.setToY(1.1);

        ScaleTransition down = new ScaleTransition(Duration.seconds(0.1), buttonText);
        down.setFromX(1.1);
        down.setFromY(1.1);
        down.setToX(1);
        down.setToY(1);

        SequentialTransition sequentialTransition = new SequentialTransition(up, down);
        return sequentialTransition;
    }

    public static RotateTransition settingsTurnIn(Node gearIcon) {
        RotateTransition rotate = new RotateTransition(Duration.seconds(0.06), gearIcon);
        rotate.setFromAngle(1);
        rotate.setToAngle(30);

        return rotate;
    }

    public static RotateTransition settingsTurnOut(Node gearIcon) {
        RotateTransition rotateBack = new RotateTransition(Duration.seconds(0.06), gearIcon);
        rotateBack.setFromAngle(30);
        rotateBack.setToAngle(1);

        return rotateBack;
    }
}
