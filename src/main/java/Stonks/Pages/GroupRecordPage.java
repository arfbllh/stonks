package Stonks.Pages;

import Stonks.Records.RecordData;
import Stonks.Users.UserData;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Vector;

public class GroupRecordPage
{
    public static void display()
    {
        Stage groupRecordWindow = new Stage();

        VBox groupRecordLayout = new VBox();
        VBox recordNames = new VBox();
        HBox buttons = new HBox();

        Button addRecord = new Button("Add Record");
        addRecord.setStyle("-fx-font: 15 Serif; -fx-base: #228B22; ");
        addRecord.setPrefSize(250,100);

        Button cancel = new Button("Cancel");
        cancel.setStyle("-fx-font: 15 Serif; -fx-base: #800000; ");
        cancel.setPrefSize(250,100);

        Label blabel = new Label("");
        blabel.setPrefSize(100,200);
        Label label = new Label("         There are no existing Group Records");
        label.setFont(new Font("Serif", 20));
        label.setAlignment(Pos.CENTER);

        buttons.setPrefSize(500,100);
        buttons.getChildren().addAll(addRecord,cancel);

        String name = UserData.getUsername(UserData.getCurrentUser());
        Vector<Integer> userRecords = RecordData.getUserRecords(UserData.getCurrentUser(),0);

        userRecords= RecordData.getUserRecords(UserData.getCurrentUser(),1);

        int numOfRecords = userRecords.size();

        Button arrayOfButton2[] = new Button[numOfRecords];

        if(numOfRecords == 0)recordNames.getChildren().addAll(blabel,label);



        //if no records, do display a label




        for(int i = 0; i < numOfRecords; i++)
        {
            int id= userRecords.get(i);
            String recName= RecordData.getRecordName(id);
            arrayOfButton2[i] = new Button(recName);

            if(i % 2 == 0)arrayOfButton2[i].setStyle("-fx-font: 20 Serif; -fx-base: #F9AA33; ");
            else arrayOfButton2[i].setStyle("-fx-font: 20 Serif; -fx-base: #4A6572; ");

            arrayOfButton2[i].setPrefSize(500,100);

        }

        for(int i = 0; i < numOfRecords; i++)
        {
            recordNames.getChildren().add(arrayOfButton2[i]);

            int id= userRecords.get(i);

            arrayOfButton2[i].setOnAction(e ->
            {
                RecordData.setCurrentRecord(id);
                groupRecordWindow.close();
                GroupRecordEntryPage.display();
            });
        }

        addRecord.setOnAction(e -> {
            groupRecordWindow.close();
            RecordInputWindow.display(groupRecordWindow, "Group");
        });

        cancel.setOnAction(e -> {
            groupRecordWindow.close();
        });

        ScrollPane scrollRecordNames = new ScrollPane();
        scrollRecordNames.setContent(recordNames);
        scrollRecordNames.setPrefSize(500,400);
        scrollRecordNames.setStyle("-fx-background: rgb(179,211,227);\n -fx-background-color: rgb(179,211,227)");

        groupRecordLayout.getChildren().addAll(scrollRecordNames, buttons);

        groupRecordWindow.initModality(Modality.APPLICATION_MODAL);

        Scene groupRecordPageScene = new Scene(groupRecordLayout, 500,500);
        groupRecordWindow.setScene(groupRecordPageScene);
        groupRecordWindow.show();
    }
}
