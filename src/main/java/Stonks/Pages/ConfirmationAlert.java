package Stonks.Pages;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

public class ConfirmationAlert
{
    private String text = new String();
    private String heading = new String();
    ConfirmationAlert(String t, String s)
    {
        text = s;
        heading = t;
    }

    public void display()
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(heading);
        alert.setContentText(text);
        ButtonType type = new ButtonType("Cancel", ButtonBar.ButtonData.OK_DONE);
        alert.getDialogPane().getButtonTypes().add(type);
        alert.showAndWait();
    }
}
