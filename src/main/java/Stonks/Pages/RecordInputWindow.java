package Stonks.Pages;

import Stonks.Records.RecordData;
import Stonks.Users.UserData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RecordInputWindow
{
    public static void display(Stage prevWindow, String recordType)
    {
        Stage recordInputWindow = new Stage();
        recordInputWindow.setTitle("Stonks");

        GridPane recordInputWindowLayout = new GridPane();
        recordInputWindowLayout.setAlignment(Pos.CENTER);
        recordInputWindowLayout.setHgap(10);
        recordInputWindowLayout.setVgap(10);
        recordInputWindowLayout.setPadding(new Insets(10, 10, 10, 10));

        Button saveButton = new Button("Save");
        saveButton.setStyle("-fx-font: 15 Serif; -fx-base: #32CD32; ");
        Button cancelButton = new Button("Cancel");
        cancelButton.setStyle("-fx-font: 15 Serif; -fx-base: #DC143C; ");

        //ChoiceBox<String> recordType = new ChoiceBox<>();
        //recordType.setValue("Personal");

        //recordType.getItems().add("Personal");
        //recordType.getItems().add("Group");

        Label enterRecord = new Label("Enter the name of record");
        enterRecord.setFont(new Font("Serif", 20));

        TextField nameOfRecord = new TextField();
        nameOfRecord.setPrefSize(200, 10);

        //Label enterType = new Label("Select the type of Record :");

        recordInputWindowLayout.add(enterRecord, 0, 0);
        recordInputWindowLayout.add(nameOfRecord, 0, 2);

        //recordInputWindowLayout.add(enterType, 0, 4);
        //recordInputWindowLayout.add(recordType, 0, 6);

        recordInputWindowLayout.add(saveButton, 0, 4);
        recordInputWindowLayout.add(cancelButton, 1, 4);

        saveButton.setOnAction(e ->
        {
            WarningAlert warning = new WarningAlert("Invalid Record Name", "Record Name can not be empty!");
            //String check = recordType.getValue();
            if(recordType.equals("Personal")) {
                if(!RecordData.addRecord(nameOfRecord.getText(), UserData.getCurrentUser(), true)){
                    warning.display();
                }
            }
            else if(recordType.equals("Group") == true){
                if(!RecordData.addRecord(nameOfRecord.getText(), UserData.getCurrentUser(), false)){
                    warning.display();
                }
            }

            prevWindow.close();

            if(recordType.equals("Personal")) IndividualRecordPage.display();
            else if(recordType.equals("Group") == true) GroupRecordPage.display();
            recordInputWindow.close();
        });

        cancelButton.setOnAction(e -> {
            recordInputWindow.close();
        });

        recordInputWindow.initModality(Modality.APPLICATION_MODAL);

        recordInputWindowLayout.setStyle("-fx-background: rgb(123,104,238);\n -fx-background-color: rgb(123,104,238)");

        Scene recordInputWindowScene = new Scene(recordInputWindowLayout, 600, 300);
        recordInputWindow.setScene(recordInputWindowScene);
        recordInputWindow.show();
    }
}
