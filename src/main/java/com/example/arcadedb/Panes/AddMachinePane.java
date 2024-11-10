package com.example.arcadedb.Panes;

import com.example.arcadedb.AppAnimations;
import com.example.arcadedb.AppResources;
import com.example.arcadedb.Beans.Machine;
import com.example.arcadedb.Database.Database;
import com.example.arcadedb.Tables.MachineTable;
import javafx.animation.Animation;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

/**
 * Represents the pane where the user can add a machine to the database.
 */
public class AddMachinePane extends Pane {
    private static AddMachinePane instance = null;

    public TextField machineNameField;
    public TextField machineDescriptionField;
    public TextField machineGenreField;
    public TextField machinePublisherField;

    public static Text alert;
    public ImageView machineIcon;

    /**
     * Constructs a new AddMachinePane.
     * Initializes the background, top label, labels, text fields, and button.
     */
    public AddMachinePane() {
        ImageView background1 = new ImageView("/graphics/addmachineui1.png");
        background1.setPreserveRatio(true);
        background1.setLayoutY(50);

        Rectangle background = new Rectangle(0, 52, 800, 400);
        background.setFill(new Color(0.2, 0.2, 0.5, 0.5));
        getChildren().addAll(background1);
        initializeSidePreview();
        initializeLabels();
        initializeFields();
        initializeButton();

    }

    /**
     * Initializes the side Preview.
     * This is the side area with the image of the arcade machine and the stats
     */
    public void initializeSidePreview() {
        machineIcon = new ImageView("/graphics/arcade_icon.png");
        machineIcon.setPreserveRatio(true);
        machineIcon.setFitWidth(190);
        machineIcon.setLayoutX(520);
        machineIcon.setLayoutY(70);

        getChildren().add(machineIcon);
    }

    /**
     * Initializes the labels.
     * These are the labels that describe what information the user should input in the text fields.
     */
    private void initializeLabels() {
        Text machineNameLabel = createLabel("MACHINE NAME:", 140, 100);
        Text machineGenreLabel = createLabel("GENRE:", 70, 150);
        Text machinePublisherLabel = createLabel("PUBLISHER:", 260, 150);
        Text machineDescriptionLabel = createLabel("DESCRIPTION:", 150, 200);

        alert = createLabel("Please fill out all fields", 20, 360);
        alert.setOpacity(0);

        getChildren().addAll(machineNameLabel, machineDescriptionLabel,
                machineGenreLabel, machinePublisherLabel, alert);
    }

    /**
     * Creates a label with the given parameters.
     * @param text The text of the label.
     * @param x The x position of the label.
     * @param y The y position of the label.
     * @return The label.
     */
    private Text createLabel(String text, double x, double y) {
        Text label = new Text(text);
        label.setLayoutX(x);
        label.setLayoutY(y);
        label.setFont(AppResources.getAppFont(18));
        label.setFill(Color.WHITE);
        return label;
    }

    /**
     * Initializes the text fields.
     * These are the fields where the user inputs the information for the new machine.
     */
    private void initializeFields() {
        machineNameField = createField(15, 110, 400);
        machineNameField.setAlignment(Pos.CENTER);

        machineGenreField = createField(15, 160, 190);
        machineGenreField.setAlignment(Pos.CENTER);

        machinePublisherField = createField(225, 160, 190);
        machinePublisherField.setAlignment(Pos.CENTER);

        machineDescriptionField = createField(15, 210, 400);
        machineDescriptionField.setAlignment(Pos.TOP_LEFT);
        machineDescriptionField.setPrefHeight(130);

        getChildren().addAll(machineNameField, machineDescriptionField,
                machineGenreField, machinePublisherField);
    }

    /**
     * Creates a text field with the given parameters.
     * @param x The x position of the text field.
     * @param y The y position of the text field.
     * @param width The width of the text field.
     * @return The text field.
     */
    private TextField createField(double x, double y, double width) {
        TextField field = new TextField();
        field.setLayoutX(x);
        field.setLayoutY(y);
        field.setPrefWidth(width);
        return field;
    }

    /**
     * Initializes the "Add Machine!" button.
     */
    private void initializeButton() {
        Button addMachineButton = new Button();
        addMachineButton.setGraphic(new ImageView("/graphics/buttons/submitButtonPositive.png"));
        addMachineButton.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        addMachineButton.setScaleY(0.30);
        addMachineButton.setScaleX(0.30);
        addMachineButton.setTranslateY(-50);
        addMachineButton.setLayoutX(270);
        addMachineButton.setLayoutY(350);
        addMachineButton.setPrefWidth(200);


        //When the button is clicked, call the addMachineToDatabase method
        addMachineButton.setOnAction(e -> {
            addMachineToDatabase();
        });

        getChildren().add(addMachineButton);
    }

    /**
     * Adds a machine to the database.
     * This method is called when the user clicks the "Add Machine!" button.
     * It gets the information from the text fields and creates a new machine object.
     * It then adds the machine to the database and refreshes the table in the database pane.
     */
    public static void addMachineToDatabase() {
        //Create a boolean to check if the text fields are empty
        boolean valid = false;
        //Get the instance of the machine table
        MachineTable machineTable = MachineTable.getInstance();

        //Create an array of all the text fields
        TextField[] fields = {AddMachinePane.getInstance().machineNameField,
                AddMachinePane.getInstance().machineDescriptionField,
                AddMachinePane.getInstance().machineGenreField,
                AddMachinePane.getInstance().machinePublisherField};

        //Check if any of the text fields are empty
        for (TextField field : fields) {
            if (field.getText().isEmpty()) {
                alert.setText("Please fill out all fields");
                AppAnimations.fadeInOut(Duration.millis(500), alert, 1).play();
            }else {
                valid = true;
            }
        }

        //If all the text fields are filled out, add the machine to the database
        if (valid){
            //Create a new machine object with the information from the text fields
            Machine machine = new Machine(0, "", "", "", "");
            machine.setMachineName(AddMachinePane.getInstance().machineNameField.getText());
            machine.setDescription(AddMachinePane.getInstance().machineDescriptionField.getText());
            machine.setGenre(AddMachinePane.getInstance().machineGenreField.getText());
            machine.setPublisher(AddMachinePane.getInstance().machinePublisherField.getText());

            //Clear the text in all the text fields
            AddMachinePane.getInstance().machineNameField.setText("");
            AddMachinePane.getInstance().machineDescriptionField.setText("");
            AddMachinePane.getInstance().machineGenreField.setText("");
            AddMachinePane.getInstance().machinePublisherField.setText("");

            //Add the machine to the database
            machineTable.createMachine(machine);

            alert.setText("Machine added successfully!");
            AppAnimations.fadeInOut(Duration.millis(200), alert, 2).play();

            //Refresh the table in the database pane so that it updates with the new machine
            DatabasePane.refreshTable();
        }

        //Set the boolean to false so that the alert text will appear if the user clicks the button again
        valid = false;

    }

    /**
     * Gets the instance of the AddMachinePane.
     * @return The instance of the AddMachinePane.
     */
    public static AddMachinePane getInstance() {
        if (instance == null) {
            instance = new AddMachinePane();
        }
        return instance;
    }
}
