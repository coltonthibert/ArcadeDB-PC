package com.example.arcadedb.Panes;

import com.example.arcadedb.AppAnimations;
import com.example.arcadedb.AppResources;
import com.example.arcadedb.Database.Database;
import com.example.arcadedb.Main;
import com.example.arcadedb.Scenes.DatabaseScene;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the login pane of the application.
 */
public class LoginPane extends BorderPane {
    Database database;
    public boolean successLogin = true;
    TextField usernameField = new TextField();
    PasswordField passwordField = new PasswordField();
    TextField dbNameField = new TextField();
    TextField dbHostField = new TextField();

    public ImageView logo;
    public Button loginButton;
    public Text creditsText;
    public FlowPane loginInfo;
    private Boolean inMainMenu = false;

    /**
     * Constructs a new LoginPane.
     * Initializes the background, logo, credits, and login components.
     */
    public LoginPane() {
        initializeBackground();
        initializeLogo();
        initializeCredits();
        initializeLogin();

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                logIn();
                loadDatabaseScene();
            }
        });
    }

    /**
     * Initializes the background images.
     */
    public void initializeBackground(){
        // Background
        ImageView background1 = new ImageView(AppResources.APPLICATON_BACKGROUND);
        ImageView borderIMG = new ImageView("graphics/buttons/borderimg.png");
        borderIMG.setFitWidth(1024);
        borderIMG.setFitHeight(550);

        background1.setLayoutX(0);
        background1.setLayoutY(0);
        background1.setFitWidth(1024);
        background1.setFitHeight(550);

        // Add the background to the root
        getChildren().addAll(background1, borderIMG);
    }

    /**
     * Initializes the logo image.
     */
    public void initializeLogo(){
        // Logo
        logo = new ImageView("/graphics/logo.png");
        AppAnimations.fadeIn(Duration.seconds(5), logo).play();
        logo.setPreserveRatio(true);
        logo.setFitHeight(144);
        logo.setLayoutX(300);
        logo.setLayoutY(68);

        // Add the logo to the root
        getChildren().add(logo);
    }

    /**
     * Initializes the credits text.
     */
    public void initializeCredits(){
        // Credits text
        creditsText = new Text("CREATED BY COLE AND COLTON");
        creditsText.setFont(AppResources.CREDITS_FONT);
        creditsText.setLayoutX(10);
        creditsText.setLayoutY(540);
        creditsText.setFill(Color.WHITE);

        // Add the credits text to the root
        getChildren().add(creditsText);
    }

    /**
     * Initializes the login components.
     */
    public void initializeLogin() {
        // Login Box
        loginInfo = new FlowPane();
        loginInfo.setLayoutX(512);
        loginInfo.setLayoutY(450);

        // Username and password fields
        usernameField.setPrefWidth(200);
        Label usernameLabel = new Label("Username:");
        usernameLabel.setFont(AppResources.MAIN_FONT);
        usernameLabel.setTextFill(Color.WHITE);


        passwordField.setPrefWidth(200);
        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(AppResources.MAIN_FONT);
        passwordLabel.setTextFill(Color.WHITE);

        // Database name and host fields

        dbNameField.setPrefWidth(200);
        Label dbNameLabel = new Label("Database Name:");
        dbNameLabel.setFont(AppResources.MAIN_FONT);
        dbNameLabel.setTextFill(Color.WHITE);


        dbHostField.setPrefWidth(200);
        Label dbHostLabel = new Label("Database Host:");
        dbHostLabel.setFont(AppResources.MAIN_FONT);
        dbHostLabel.setTextFill(Color.WHITE);

        //Fill in fields with previous login info if it exists
        try {
            File file = new File("login.txt");
            Scanner scanner = new Scanner(file);
            String[] loginInfo = scanner.nextLine().split(",");
            dbNameField.setText(loginInfo[0]);
            dbHostField.setText(loginInfo[1]);
            usernameField.setText(loginInfo[2]);
            passwordField.setText(loginInfo[3]);
        } catch (Exception e) {
            System.out.println("No previous login info found");
        }

        // Set the number of columns for the fields
        passwordField.setPrefColumnCount(14);
        usernameField.setPrefColumnCount(14);
        dbNameField.setPrefColumnCount(14);
        dbHostField.setPrefColumnCount(14);

        // Login button
        loginButton = new Button();
        loginButton.setGraphic(new ImageView("/graphics/buttons/submitButtonPositive.png"));
        loginButton.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        loginButton.setScaleY(0.23);
        loginButton.setScaleX(0.23);
        loginButton.setTranslateY(-50);

        AppAnimations.iconPopIn(loginButton, 1f, 0.23f).play();

        // Add the fields to the login box
        loginInfo.getChildren().addAll(
                dbNameLabel, dbNameField,
                dbHostLabel, dbHostField,
                usernameLabel, usernameField,
                passwordLabel, passwordField,
                loginButton
        );
        loginInfo.setAlignment(Pos.CENTER);

        // Add the login box to the root
        getChildren().add(loginInfo);
    }

    /**
     * Saves login info to a file.
     */
    public void logIn(){
        // Get the login info from the fields
        String username = usernameField.getText();
        String password = passwordField.getText();
        String dbName = dbNameField.getText();
        String dbHost = dbHostField.getText();

        // Save the login info to a file
        BufferedWriter writer; // Create a writer to write to the file
        try {
            writer = new BufferedWriter(new FileWriter("login.txt")); // Create a new file
            writer.write(dbName + "," + dbHost + "," + username + "," + password);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadDatabaseScene() {
        getChildren().remove(loginInfo);
        ImageView loadingIcon = new ImageView("graphics/loadingicon.png");
        loadingIcon.setLayoutX(330);
        loadingIcon.setLayoutY(210);
        loadingIcon.setScaleY(0.25);
        loadingIcon.setScaleX(0.25);
        getChildren().add(loadingIcon);
        AppAnimations.logIn(loadingIcon, logo).play();
        if (successLogin) { // Login success
            Text welcomeText = new Text("WELCOME TO THE NEXT LEVEL");
            welcomeText.setLayoutX(330);
            welcomeText.setLayoutY(330);
            welcomeText.setFont(AppResources.MAIN_FONT);
            welcomeText.setFill(Color.WHITE);
            getChildren().add(welcomeText);
            SequentialTransition loginSuccessAnimation = AppAnimations.loginSuccess(loadingIcon, logo, welcomeText, creditsText);
            loginSuccessAnimation.play();
            loginSuccessAnimation.setOnFinished(event -> {
                System.out.println("Login Success");
                getChildren().remove(creditsText);

                Main.mainStage.setScene(new DatabaseScene());
            });
        }
    }

}

