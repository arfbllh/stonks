package Stonks.Pages;

import Stonks.Entries.EntryData;
import Stonks.Records.RecordData;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Vector;

public class EntryEditPage
{
    public static void display(Stage prev, String type)
    {
        Stage entryEditWindow = new Stage();

        /*GridPane entryEditLayout = new GridPane();
        entryEditLayout.setVgap(10);
        entryEditLayout.setHgap(10);
        entryEditLayout.setPadding(new Insets(10, 10, 10, 10));
        entryEditLayout.setAlignment(Pos.CENTER);*/

        VBox entryEditLayout = new VBox();
        VBox informationLayout = new VBox();
        informationLayout.setPrefSize(500,400);
        informationLayout.setPadding(new Insets(10,10,10,10));
        informationLayout.setSpacing(30);
        HBox buttons = new HBox();
        buttons.setPrefSize(500,100);

        TextField entry = new TextField();
        entry.setText(EntryData.getEntryName(EntryData.getCurrentEntry()));

        TextField amount = new TextField();
        amount.setText(String.valueOf(EntryData.getEntryAmount(EntryData.getCurrentEntry())));

        TextField tag = new TextField();
        tag.setText(EntryData.getEntryTag(EntryData.getCurrentEntry()));

        Button cancel = new Button("Cancel");
        cancel.setPrefSize(250,100);
        cancel.setStyle("-fx-font: 15 Serif; -fx-base: #708090; ");

        Button update = new Button("Update Entry");
        update.setPrefSize(250,100);
        update.setStyle("-fx-font: 15 Serif; -fx-base: #32CD32; ");

        //Button deleteEntry = new Button("Delete Entry");
        //deleteEntry.setPrefSize(180,100);
        //deleteEntry.setStyle("-fx-font: 15 Serif; -fx-base: #FF6347; ");

        Label remarks = new Label("Current Remarks");
        remarks.setTextFill(Color.rgb(189,183,107));
        Label lAmount = new Label("Current Amount");
        lAmount.setTextFill(Color.rgb(189,183,107));
        Label lTag = new Label("Current Tag");
        lTag.setTextFill(Color.rgb(189,183,107));

        update.setOnAction(e -> {
            Vector<String> temp = new Vector<>();
            temp.add(tag.getText());
            int curEntry= EntryData.getCurrentEntry();

            int check= EntryData.addEntry(entry.getText(), temp, amount.getText(),EntryData.isCashIn(curEntry), RecordData.getCurrentRecord() );
            if(check==-1){
                WarningAlert warning = new WarningAlert("Invalid Input", "Fields can not be empty!");
                warning.display();
            }
            else if(check==0){
                WarningAlert warning = new WarningAlert("Invalid Amount", "Amount can not be non numeric!");
                warning.display();
            }

            else {
                EntryData.deleteEntry(EntryData.getCurrentEntry());


                //prev.close();
                if(type.equals("Individual")) IndividualRecordEntryPage.display();
                else GroupRecordEntryPage.display();
                entryEditWindow.close();
            }
        });

        cancel.setOnAction(e -> {
            entryEditWindow.close();
            if(type.equals("Individual")) IndividualRecordEntryPage.display();
            else GroupRecordEntryPage.display();
        });

        Image img2 = null;
        img2 = new Image("Background3.jpg");
        BackgroundImage myBI2= new BackgroundImage(img2,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        informationLayout.setBackground(new Background(myBI2));
        informationLayout.setStyle("-fx-background: rgb(74,101,114);\n -fx-background-color: rgb(74,101,114)");

        informationLayout.getChildren().addAll(remarks,entry,lAmount,amount,lTag, tag);
        buttons.getChildren().addAll(update,cancel);
       /* entryEditLayout.add(remarks, 0, 0);
        entryEditLayout.add(entry, 0, 2);
        entryEditLayout.add(lAmount, 0, 4);
        entryEditLayout.add(amount, 0, 6);
        entryEditLayout.add(lTag, 0, 8);
        entryEditLayout.add(tag, 0, 10);
        entryEditLayout.add(deleteEntry, 6, 12);

        entryEditLayout.add(cancel, 4, 12);
        entryEditLayout.add(update, 2, 12);*/
        entryEditLayout.getChildren().addAll(informationLayout,buttons);
        entryEditLayout.setStyle("-fx-background: rgb(74,101,114);\n -fx-background-color: rgb(74,101,114)");

        entryEditWindow.initModality(Modality.APPLICATION_MODAL);

        Scene entryEditScene = new Scene(entryEditLayout, 500, 500);
        entryEditWindow.setScene(entryEditScene);
        entryEditWindow.show();
    }
}
