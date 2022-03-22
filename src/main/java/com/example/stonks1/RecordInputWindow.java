package com.example.stonks1;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RecordInputWindow
{
    public static void display(Stage prevWindow)
    {
        Stage recordInputWindow = new Stage();
        recordInputWindow.setTitle("com/example/stonks1");

        GridPane recordInputWindowLayout = new GridPane();
        recordInputWindowLayout.setAlignment(Pos.CENTER);
        recordInputWindowLayout.setHgap(10);
        recordInputWindowLayout.setVgap(10);
        recordInputWindowLayout.setPadding(new Insets(10, 10, 10, 10));

        Button saveButton = new Button("Save");
        Button cancelButton = new Button("Cancel");

        ChoiceBox<String> recordType = new ChoiceBox<>();
        recordType.setValue("Personal");

        recordType.getItems().add("Personal");
        recordType.getItems().add("Group");

        Label enterRecord = new Label("Enter the name of record");

        TextField nameOfRecord = new TextField();

        Label enterType = new Label("Select the type of Record :");

        recordInputWindowLayout.add(enterRecord, 0, 0);
        recordInputWindowLayout.add(nameOfRecord, 0, 2);

        recordInputWindowLayout.add(enterType, 0, 4);
        recordInputWindowLayout.add(recordType, 0, 6);

        recordInputWindowLayout.add(saveButton, 0, 8);
        recordInputWindowLayout.add(cancelButton, 1, 8);

        saveButton.setOnAction(e ->
        {
            String check = recordType.getValue();
            if(check.equals("Personal") == true)RecordData.addRecord(nameOfRecord.getText(), UserData.getCurrentUser(), true);
            else if(check.equals("Group") == true)RecordData.addRecord(nameOfRecord.getText(), UserData.getCurrentUser(), false);

            prevWindow.close();
            RecordPage.display();
            recordInputWindow.close();
        });

        cancelButton.setOnAction(e -> {
            recordInputWindow.close();
        });

        recordInputWindow.initModality(Modality.APPLICATION_MODAL);
        Scene recordInputWindowScene = new Scene(recordInputWindowLayout, 600, 300);
        recordInputWindow.setScene(recordInputWindowScene);
        recordInputWindow.show();
    }
}
