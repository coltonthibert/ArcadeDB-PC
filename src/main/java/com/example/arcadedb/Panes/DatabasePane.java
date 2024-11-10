package com.example.arcadedb.Panes;

import com.example.arcadedb.Alerts.DeleteSelectedItemAlert;
import com.example.arcadedb.Alerts.UpdateSelectedItemAlert;
import com.example.arcadedb.AppAnimations;
import com.example.arcadedb.AppResources;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import com.example.arcadedb.Beans.Machine;
import com.example.arcadedb.Tables.MachineTable;
import javafx.animation.ParallelTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * DatabasePane class represents a custom Pane that is used to display the database of machines.
 * It includes functionalities to display a table of machines, add, remove, and update machines,
 * along with various UI elements for navigation and interaction.
 */
public class DatabasePane extends BorderPane {

    public ImageView logo;
    public ImageView background1;

    public ImageView option1BG;
    public ImageView option2BG;
    public ImageView option3BG;
    public ImageView option4BG;
    public Button button_machine;
    public Button button_machineAdd;
    public Button button_machineRemove;
    public Button button_machineUpdate;
    public Button settingsButton;

    FlowPane sideButtons;

    public static TableView tableView = new TableView();
    public AddMachinePane addMachinePane = AddMachinePane.getInstance();
    public SettingsPane settingsPane = SettingsPane.getInstance();

    /**
     * Constructor for DatabasePane initializing the UI components and animations.
     */
    public DatabasePane(){
        initializeBackground();
        initializeLogo();
        initializeDatabaseUI();
        initializeTable();

        initializeAddMachinePane();
        initializeSettingsPane();

        ParallelTransition startUpAni = AppAnimations.mainScreenStart(tableView, logo, option1BG, option2BG, option3BG, option4BG);
        startUpAni.setOnFinished(event -> {
            System.out.println("Finished");
        });
        startUpAni.play();
    }

    /**
     * Initializes the background of the pane with images.
     */
    public void initializeBackground(){
        // Background
        background1 = new ImageView("/graphics/background1.gif");
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
     * Initializes the logo displayed in the pane.
     */
    public void initializeLogo(){
        // Logo
        logo = new ImageView("/graphics/logo.png");
        logo.setPreserveRatio(true);
        logo.setFitHeight(144);
        logo.setLayoutX(300);
        logo.setLayoutY(68);

        // Add the logo to the root
        getChildren().add(logo);
    }

    /**
     * Initializes the table displaying machine information.
     */
    public void initializeTable(){

        MachineTable machineTable = MachineTable.getInstance();
        TableColumn<Machine, String> column1_Name =
                new TableColumn<>("Machine Name");
        column1_Name.setCellValueFactory(
                e-> new SimpleStringProperty(e.getValue().getMachineName())
        );

        TableColumn<Machine, String> column2_Description =
                new TableColumn<>("Description");
        column2_Description.setCellValueFactory(
                e-> new SimpleStringProperty(e.getValue().getDescription())
        );

        TableColumn<Machine, String> column3_Publisher =
                new TableColumn<>("Publisher");
        column3_Publisher.setCellValueFactory(
                e-> new SimpleStringProperty(e.getValue().getPublisher())
        );

        TableColumn<Machine, String> colum4_Genre =
                new TableColumn<>("Genre");
        colum4_Genre.setCellValueFactory(
                e-> new SimpleStringProperty(e.getValue().getGenre())
        );

        tableView.getColumns().addAll(column1_Name, column2_Description, column3_Publisher, colum4_Genre);
        tableView.getItems().addAll(machineTable.getAllMachines());

        tableView.setOpacity(0);
        tableView.setMaxHeight(400);
        tableView.setMaxWidth(775);
        setBottom(tableView);
        setAlignment(tableView, Pos.BOTTOM_RIGHT);

        tableView.getSelectionModel().selectedItemProperty().addListener(observable -> {
            option3BG.setImage(new Image("graphics/buttons/option1.png"));
            option4BG.setImage(new Image("graphics/buttons/option1.png"));
        });


    }

    /**
     * Initializes the layout for the AddMachinePane for adding new machines.
     */
    public void initializeAddMachinePane() {
        addMachinePane.setLayoutX(250);
        addMachinePane.setLayoutY(100);
    }

    public void initializeSettingsPane() {
        settingsPane.setLayoutX(250);
        settingsPane.setLayoutY(100);

        settingsPane.staticBackground.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                settingsPane.setStaticBackground(background1);
            } else {
                settingsPane.setStaticBackground(background1);
            }
        });
    }

    /**
     * Refreshes the table displaying machines.
     */
    public static void refreshTable(){
        MachineTable machineTable = MachineTable.getInstance();
        tableView.getItems().clear();
        tableView.getItems().addAll(machineTable.getAllMachines());
    }

    /**
     * Initializes various UI elements related to managing machines, as well as button logic.
     */
    public void initializeDatabaseUI(){
// Initialize Option1 (Machines) UI components
        option1BG = new ImageView("graphics/buttons/option1.png");
        option1BG.setPreserveRatio(true);
        option1BG.setFitHeight(40);
        option1BG.setLayoutY(190);

        // Create and configure text for Option1
        Text option1Text = new Text("MACHINES");
        option1Text.setFont(AppResources.OPTIONS_FONT);
        option1Text.setLayoutX(50);
        option1Text.setLayoutY(215);
        option1Text.setFill(Color.WHITE);

        // Create and configure icon image for Option1
        ImageView iconOption1 = new ImageView("graphics/buttons/icon_machines.png");
        iconOption1.setPreserveRatio(true);
        iconOption1.setFitHeight(70);
        iconOption1.setLayoutY(155);
        iconOption1.setLayoutX(5);

        // Initialize Option2 (Add Machines) UI components
        option2BG = new ImageView("graphics/buttons/option1.png");
        option2BG.setPreserveRatio(true);
        option2BG.setFitHeight(40);
        option2BG.setLayoutY(270);

        // Create and configure text for Option2
        Text option2Text = new Text("ADD MACHINES");
        option2Text.setFont(AppResources.OPTIONS_FONT);
        option2Text.setLayoutX(50);
        option2Text.setLayoutY(295);
        option2Text.setFill(Color.WHITE);

        // Create and configure icon image for Option2
        ImageView iconOption2 = new ImageView("graphics/buttons/icon_add.png");
        iconOption2.setPreserveRatio(true);
        iconOption2.setFitHeight(70);
        iconOption2.setLayoutY(235);
        iconOption2.setLayoutX(5);

        // Initialize Option3 (Delete Machines) UI components
        option3BG = new ImageView("graphics/buttons/optionGrey.png");
        option3BG.setPreserveRatio(true);
        option3BG.setFitHeight(40);
        option3BG.setLayoutY(350);

        // Create and configure text for Option3
        Text option3Text = new Text("REMOVE MACHINE");
        option3Text.setFont(AppResources.OPTIONS_FONT);
        option3Text.setLayoutX(50);
        option3Text.setLayoutY(375);
        option3Text.setFill(Color.WHITE);

        // Create and configure icon image for Option3
        ImageView iconOption3 = new ImageView("graphics/buttons/icon_remove.png");
        iconOption3.setPreserveRatio(true);
        iconOption3.setFitHeight(70);
        iconOption3.setLayoutY(315);
        iconOption3.setLayoutX(5);

        // Initialize Option4 (Update Machines) UI components
        option4BG = new ImageView("graphics/buttons/optionGrey.png");
        option4BG.setPreserveRatio(true);
        option4BG.setFitHeight(40);
        option4BG.setLayoutY(430);

        // Create and configure text for Option4
        Text option4Text = new Text("UPDATE MACHINE");
        option4Text.setFont(AppResources.OPTIONS_FONT);
        option4Text.setLayoutX(50);
        option4Text.setLayoutY(455);
        option4Text.setFill(Color.WHITE);

        // Create and configure icon image for Option4
        ImageView iconOption4 = new ImageView("graphics/buttons/icon_update.png");
        iconOption4.setPreserveRatio(true);
        iconOption4.setFitHeight(70);
        iconOption4.setLayoutY(395);
        iconOption4.setLayoutX(5);

        // Perform animations to display and move Options using AppAnimations
        AppAnimations.moveOption(option1BG, iconOption1, option1Text, 0.8f).play();
        AppAnimations.moveOption(option2BG, iconOption2, option2Text, 1f).play();
        AppAnimations.moveOption(option3BG, iconOption3, option3Text, 1.4f).play();
        AppAnimations.moveOption(option4BG, iconOption4, option4Text, 1.6f).play();

        // Initialize Settings Button
        settingsButton = new Button();
        settingsButton.setGraphic(new ImageView("graphics/buttons/icon_settings.png"));
        settingsButton.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        settingsButton.setScaleY(0.8);
        settingsButton.setScaleX(0.8);
        settingsButton.setLayoutY(520);
        settingsButton.setLayoutX(25);

        // Initialize container for side buttons
        sideButtons = new FlowPane();
        sideButtons.setLayoutY(194);
        sideButtons.setLayoutX(90);
        sideButtons.setVgap(50);

        // Initialize and configure Machines button
        button_machine = new Button();
        button_machine.setScaleX(11);
        button_machine.setScaleY(2);
        button_machine.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        // Add a listener for hover effects on Button Machine
        button_machine.hoverProperty().addListener((obs, oldVal, newValue) -> {
            if (newValue) {
                AppAnimations.buttonHover(option1Text).play();
            } else {
                AppAnimations.buttonHoverOff(option1Text).play();
            }
        });

        // Define action for machines button click
        button_machine.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                AppAnimations.buttonClick(option1Text).play();
                if (getChildren().contains(addMachinePane) && !getChildren().contains(tableView)) {
                    getChildren().add(tableView);
                    getChildren().remove(addMachinePane);
                }
            }
        });

        // Initialize and configure Button Machine
        button_machine = new Button();
        button_machine.setScaleX(11);
        button_machine.setScaleY(2);
        button_machine.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        // Add a listener for hover effects on Button Machine
        button_machine.hoverProperty().addListener((obs, oldVal, newValue) -> {
            if (newValue) {
                // Play hover animation when the button is hovered over
                AppAnimations.buttonHover(option1Text).play();
            } else {
                // Play hover off animation when the hover ends
                AppAnimations.buttonHoverOff(option1Text).play();
            }
        });

        // Define action for Button Machine click
        button_machine.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                // Play click animation when the button is clicked
                AppAnimations.buttonClick(option1Text).play();

                // Toggle between showing the tableView, settings, and addMachinePane
                if (!getChildren().contains(tableView)) {
                    if (getChildren().contains(settingsPane)) {
                        getChildren().remove(settingsPane);
                    }

                    if (getChildren().contains(addMachinePane)) {
                        getChildren().remove(addMachinePane);
                    }
                    getChildren().add(tableView);
                }
            }
        });

        // Similar detailed comments for button_machineAdd, button_machineRemove, button_machineUpdate...

        // Initialize and configure Button Machine Add
        button_machineAdd = new Button();
        button_machineAdd.setScaleX(11);
        button_machineAdd.setScaleY(2);
        button_machineAdd.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        button_machineAdd.hoverProperty().addListener((obs, oldVal, newValue) -> {
            if (newValue) {
                AppAnimations.buttonHover(option2Text).play();
            } else {
                AppAnimations.buttonHoverOff(option2Text).play();
            }
        });

        button_machineAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                AppAnimations.buttonClick(option2Text).play();

                if(!getChildren().contains(addMachinePane)){
                    if (getChildren().contains(settingsPane)) {
                        getChildren().remove(settingsPane);
                    }

                    if (getChildren().contains(tableView)) {
                        getChildren().remove(tableView);
                    }
                    getChildren().add(addMachinePane);
                }

            }
        });

        // Initialize and configure Button Machine Remove
        button_machineRemove = new Button();
        button_machineRemove.setScaleX(11);
        button_machineRemove.setScaleY(2);
        button_machineRemove.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        button_machineRemove.hoverProperty().addListener((obs, oldVal, newValue) -> {
            if (newValue) {
                AppAnimations.buttonHover(option3Text).play();
            } else {
                AppAnimations.buttonHoverOff(option3Text).play();
            }
        });

        button_machineRemove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                AppAnimations.buttonClick(option3Text).play();
                Machine machine = (Machine) tableView.getSelectionModel().getSelectedItem();
                DeleteSelectedItemAlert alert = new DeleteSelectedItemAlert(machine);

                alert.showAndWait().ifPresent(type -> { // Show confirmation dialog
                    if (type == alert.getButtonTypeYes()) {
                        // Delete the machine from the database after confirmation
                        MachineTable.getInstance().deleteMachine(machine.getId());
                        refreshTable();
                    }
                });
            }
        });

        // Initialize and configure Button Machine Update
        button_machineUpdate = new Button();
        button_machineUpdate.setScaleX(11);
        button_machineUpdate.setScaleY(2);
        button_machineUpdate.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        button_machineUpdate.hoverProperty().addListener((obs, oldVal, newValue) -> {
            if (newValue) {
                AppAnimations.buttonHover(option4Text).play();
            } else {
                AppAnimations.buttonHoverOff(option4Text).play();
            }
        });

        button_machineUpdate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                AppAnimations.buttonClick(option4Text).play();
                Machine machine = (Machine) tableView.getSelectionModel().getSelectedItem();
                UpdateSelectedItemAlert alert = new UpdateSelectedItemAlert(machine); // Create confirmation dialog

                // Update the machine in the database after confirmation
                alert.showAndWait().ifPresent(type -> {
                    if (type == alert.getButtonTypeConfirm()) {
                        MachineTable.getInstance().updateMachine(machine,
                                alert.getMachineName().getText(),
                                alert.getDescription().getText(),
                                alert.getGenre().getText(),
                                alert.getPublisher().getText());
                        refreshTable();
                    }
                });
            }
        });

        AppAnimations.iconPopIn(settingsButton, 0.3f, 1f).play();
        // Configure event handlers for hover and click effects on the settings button
        settingsButton.hoverProperty().addListener((obs, oldVal, newValue) -> {
            if (newValue) {
                AppAnimations.settingsTurnIn(settingsButton).play();
            } else {
                AppAnimations.settingsTurnOut(settingsButton).play();
            }
        });

        settingsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                AppAnimations.buttonClick(settingsButton).play();
                if (!getChildren().contains(settingsPane)){
                    if (getChildren().contains(addMachinePane) || getChildren().contains(tableView)) {
                        getChildren().remove(addMachinePane);
                        getChildren().remove(tableView);
                    }
                    getChildren().add(settingsPane);
                }

            }
        });

        // Add buttons to the sideButtons container
        sideButtons.getChildren().addAll(button_machine, button_machineAdd, button_machineRemove, button_machineUpdate);

        // Add all UI components to the DatabasePane
        getChildren().addAll(
                option1BG, option1Text, iconOption1,
                option2BG, option2Text, iconOption2,
                option3BG, option3Text, iconOption3,
                option4BG, option4Text, iconOption4, sideButtons,
                settingsButton
        );
    }

}
