package com.example.arcadedb.Alerts;

import com.example.arcadedb.Beans.Machine;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class DeleteSelectedItemAlert extends Alert {
    ImageView icon = new ImageView("/graphics/buttons/icon_remove_black.png");
    ButtonType buttonTypeYes = new ButtonType("Yes");
    ButtonType buttonTypeNo = new ButtonType("No");
    public DeleteSelectedItemAlert(Machine machine) {
        super(AlertType.CONFIRMATION);
        this.setTitle("Delete " + machine.getMachineName());
        this.setHeaderText("Are you sure you want to delete the machine " + machine.getMachineName() + "?");
        this.setContentText("This action cannot be undone.");
        this.setGraphic(icon);

        this.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

    }

    public ButtonType getButtonTypeYes() {
        return buttonTypeYes;
    }

    public ButtonType getButtonTypeNo() {
        return buttonTypeNo;
    }

}
