package com.example.arcadedb.Panes;

import com.example.arcadedb.AppAnimations;
import com.example.arcadedb.AppResources;
import com.example.arcadedb.Beans.Machine;
import com.example.arcadedb.Main;
import com.example.arcadedb.Tables.MachineTable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * The SettingsPane class is a Pane that displays the settings.
 * It contains the static background checkbox, export to file button, clear database button, and PHPMyAdmin button.
 */
public class SettingsPane extends Pane {
    public CheckBox staticBackground;
    public Button logOut = new Button("Log Out");
    public Button clearDatabase;
    public Button exportToFile;
    public Button myPHPAdmin;
    private static SettingsPane instance = null;

    /**
     * Constructs a new SettingsPane.
     * Initializes the background, top label, labels, text fields, and button.
     */
    public SettingsPane() {
        ImageView background1 = new ImageView("/graphics/settingsui.png");
        background1.setPreserveRatio(true);
        background1.setLayoutY(50);

        getChildren().addAll(background1);
        initializeSettings();
    }

    /**
     * Initializes the user display.
     * Creates the user icon and username display.
     */
    public void initializeUserDisplay() {
        ImageView userDisplay = new ImageView("/graphics/usericon.png");
        userDisplay.setPreserveRatio(true);
        userDisplay.setLayoutY(60);
        userDisplay.setLayoutX(10);

        // Username Display //
        Text usernameDisplay = new Text("USERNAME");
        usernameDisplay.setText(getUsername());


        usernameDisplay.setFont(AppResources.getAppFont(20));
        usernameDisplay.setFill(Color.WHITE);
        usernameDisplay.setLayoutY(85);
        usernameDisplay.setLayoutX(85);


        getChildren().addAll(userDisplay, usernameDisplay);
    }

    /**
     * Initializes the settings.
     * Creates the static background checkbox, export to file button, and clear database button.
     */

    private void initializeSettings() {
        initializeLogOutButton();
        initializeUserDisplay();

        staticBackground = createCheckBox("Static Background", 10, 150);
        staticBackground.hoverProperty().addListener((obs, oldVal, newValue) -> {
            if (newValue) {
                AppAnimations.buttonHover(staticBackground).play();
            } else {
                AppAnimations.buttonHoverOff(staticBackground).play();
            }
        });

        exportToFile = createButton("Export to File", 30, 190);
        exportToFile.setOnAction(e -> {
            exportToFileFunction();
            AppAnimations.buttonClick(exportToFile).play();
        });
        exportToFile.hoverProperty().addListener((obs, oldVal, newValue) -> {
            if (newValue) {
                AppAnimations.buttonHover(exportToFile).play();
            } else {
                AppAnimations.buttonHoverOff(exportToFile).play();
            }
        });

        myPHPAdmin = createButton("Open MyPHPAdmin", 30, 240);
        myPHPAdmin.setOnAction(e -> {
            openPHPMyAdminFunction();
            AppAnimations.buttonClick(myPHPAdmin).play();
        });
        myPHPAdmin.hoverProperty().addListener((obs, oldVal, newValue) -> {
            if (newValue) {
                AppAnimations.buttonHover(myPHPAdmin).play();
            } else {
                AppAnimations.buttonHoverOff(myPHPAdmin).play();
            }
        });

        clearDatabase = createButton("Clear Database", 30, 390);
        clearDatabase.setTextFill(Color.RED);
        clearDatabase.setOnAction(e -> {
            clearDatabaseFunction();
            AppAnimations.buttonClick(clearDatabase).play();
        });
        clearDatabase.hoverProperty().addListener((obs, oldVal, newValue) -> {
            if (newValue) {
                AppAnimations.buttonHover(clearDatabase).play();
            } else {
                AppAnimations.buttonHoverOff(clearDatabase).play();
            }
        });

        getChildren().addAll(staticBackground, logOut, clearDatabase, exportToFile, myPHPAdmin);
    }

    /**
     * Initializes the log out button.
     */
    public void initializeLogOutButton(){
        logOut.setText("Log Out");
        logOut.setFont(AppResources.getAppFont(15));
        logOut.setTextFill(Color.WHITE);

        logOut.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        logOut.setLayoutX(85);
        logOut.setLayoutY(115);

        logOut.setOnAction(e -> {
            Main.mainStage.setScene(Main.loginScene);
            DatabasePane.refreshTable();
        });
    }

    /**
     * Creates a check box with the given text, x position, and y position.
     * @param text
     * @param x
     * @param y
     * @return
     */
    private CheckBox createCheckBox(String text, double x, double y) {
        CheckBox checkBox = new CheckBox(text.toUpperCase());
        checkBox.setLayoutX(x);
        checkBox.setLayoutY(y);
        checkBox.setFont(AppResources.getAppFont(18));
        checkBox.setTextFill(Color.WHITE);
        return checkBox;
    }

    /**
     * Creates a button with the given text, x position, and y position.
     * @param text The text to display on the button.
     * @param x The x position of the button.
     * @param y The y position of the button.
     * @return The button.
     */
    private Button createButton(String text, double x, double y) {
        Button button = new Button(text.toUpperCase());
        button.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setFont(AppResources.getAppFont(18));
        button.setTextFill(Color.WHITE);
        return button;
    }

    public static String getUsername(){
        String filename = "login.txt";

        //Read from the login.txt file, return the username which is the third item seperated by commas
        try {
            File file = new File(filename); // Create a new file
            Scanner scanner = new Scanner(file); // Create a new scanner

            String line = scanner.nextLine(); // Read the first line
            String[] items = line.split(","); // Split the line by commas
            return items[2]; // Return the third item
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //If the file is not found, return "User"
        return "User";
    }

    //================== SETTINGS FUNCTIONALITY ==================//

    public void setStaticBackground(ImageView background) {
        // If the static background checkbox is selected, set the background to the static background
        if (staticBackground.isSelected()) {
            background.setImage(AppResources.APPLICATON_BACKGROUND2);
        } else {
            background.setImage(AppResources.APPLICATON_BACKGROUND);
        }

    }
    public static void exportToFileFunction() {
        // Get current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HHmmss");
        String formattedDateTime = currentDateTime.format(formatter);

        // Create a new file with the formatted date and time in the filename
        String filename = "ArcadeDB_Export_" + formattedDateTime + ".txt";

        // Create a FileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        fileChooser.setInitialFileName(filename);
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text file (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        Stage stage = Main.mainStage;
        File file = fileChooser.showSaveDialog(stage);

        // If the user clicks save, export the database to the file
        if (file != null) {
            PrintWriter writer = null; // Initialize the writer
            try {
                writer = new PrintWriter(file); // Create a new PrintWriter
                for (Machine machine : MachineTable.getInstance().getAllMachines()) { // Write each machine to the file
                    writer.println(machine.getId() + "," + machine.getMachineName() + "," + machine.getDescription() + "," + machine.getPublisher() + "," + machine.getGenre());
                }
                writer.close();

                // Show an alert to the user that the file was saved successfully
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Export to File");
                alert.setHeaderText("Exported to file: " + file.getName() + "\nat " + file.getPath().replace(file.getName(), ""));
                alert.setContentText("File saved successfully!");

                // Add a button to open the file location
                ButtonType buttonTypeOpen = new ButtonType("Open File Location");
                alert.getButtonTypes().add(buttonTypeOpen);
                alert.showAndWait().ifPresent(type -> {
                    if (type == buttonTypeOpen) {
                        try {
                            Desktop.getDesktop().open(file.getParentFile()); // Open the file location
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

            } catch (FileNotFoundException e) {
                // Show an alert to the user that the file could not be saved
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Export to File");
                alert.setHeaderText("File Export Failed");
                alert.setContentText("Export to file failed: " + e.getMessage());
                alert.show();
            } finally { // Close the writer to prevent memory leaks
                if (writer != null) {
                    writer.close();
                }
            }
        }
    }

    public void openPHPMyAdminFunction(){
        //Open the default browser to the PHPMyAdmin page
        try {
            Desktop.getDesktop().browse(URI.create("http://phpmyadmin.scweb.ca"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearDatabaseFunction(){
        //Clear the database
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Clear Database");
        alert.setHeaderText("Clear Database");
        alert.setContentText("Are you sure you want to clear the database? THIS CANNOT BE UNDONE!");
        //Add yes and no buttons to the alert
        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No");
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        //If the user clicks yes, clear the database
        alert.showAndWait().ifPresent(type -> {
            if (type == buttonTypeYes) {
                MachineTable.getInstance().clearMachineTable();
                DatabasePane.refreshTable();
            }
        });
    }

    /**
     * Gets the instance of the SettingsPane
     * @return The instance of the SettingsPane.
     */
    public static SettingsPane getInstance() {
        if (instance == null) {
            instance = new SettingsPane();
        }
        return instance;
    }
}
