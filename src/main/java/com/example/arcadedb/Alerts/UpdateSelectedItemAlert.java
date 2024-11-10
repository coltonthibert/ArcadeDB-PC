package com.example.arcadedb.Alerts;

import com.example.arcadedb.Beans.Machine;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class UpdateSelectedItemAlert extends Alert {
    Text machineNameText = new Text("Machine Name:");
    Text descriptionText = new Text("Description:");
    Text publisherText = new Text("Publisher:");
    Text genreText = new Text("Genre:");
    TextField machineName = new TextField();
    TextField description = new TextField();
    TextField publisher = new TextField();
    TextField genre = new TextField();

    ButtonType buttonTypeConfirm = new ButtonType("Confirm");
    ButtonType buttonTypeCancel = new ButtonType("Cancel");
    public UpdateSelectedItemAlert(Machine machine) {
        super(AlertType.INFORMATION);
        this.setTitle("Update Machine");
        this.setHeaderText("Update Machine: " + machine.getMachineName());
        this.setResizable(true);

        machineName.setPromptText("Machine Name");
        machineName.setText(machine.getMachineName());

        description.setPromptText("Description");
        description.setText(machine.getDescription());

        publisher.setPromptText("Publisher");
        publisher.setText(machine.getPublisher());

        genre.setPromptText("Genre");
        genre.setText(machine.getGenre());

        Text errorText = new Text("");
        errorText.setFill(Color.RED);
        errorText.setStyle("-fx-font-weight: bold; -fx-font-size: 16px");

        VBox vBox = new VBox();
        vBox.setPrefWidth(500);
        vBox.getChildren().addAll(machineNameText, machineName, descriptionText,
                description, publisherText, publisher, genreText, genre, errorText);

        this.setGraphic(new ImageView("graphics/buttons/icon_update_black.png"));
        this.setWidth(500);
        this.setHeight(500);
        this.getDialogPane().setContent(vBox);
        this.getButtonTypes().setAll(buttonTypeConfirm, buttonTypeCancel);

        // Retrieve the Confirm button
        Node confirmButton = this.getDialogPane().lookupButton(buttonTypeConfirm);

        // Add event filter to prevent closing when Confirm is clicked and fields are empty
        confirmButton.addEventFilter(ActionEvent.ACTION, event -> {
            if (machineName.getText().isEmpty() || description.getText().isEmpty() || publisher.getText().isEmpty() || genre.getText().isEmpty()) {
                event.consume(); // Consume the event to prevent dialog from closing
                errorText.setText("Please fill out all fields");
            }
        });
    }
    public TextField getMachineName() {
        return machineName;
    }

    public void setMachineName(TextField machineName) {
        this.machineName = machineName;
    }

    public TextField getDescription() {
        return description;
    }

    public void setDescription(TextField description) {
        this.description = description;
    }

    public TextField getPublisher() {
        return publisher;
    }

    public void setPublisher(TextField publisher) {
        this.publisher = publisher;
    }

    public TextField getGenre() {
        return genre;
    }

    public void setGenre(TextField genre) {
        this.genre = genre;
    }
    public ButtonType getButtonTypeConfirm() {
        return buttonTypeConfirm;
    }
}
