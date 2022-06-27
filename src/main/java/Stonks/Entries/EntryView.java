package Stonks.Entries;

import Stonks.Pages.EntryEditPage;
import Stonks.Pages.EntryPage;
import Stonks.Pages.GroupRecordEntryPage;
import Stonks.Pages.IndividualRecordEntryPage;
import Stonks.Records.RecordData;
import Stonks.Users.UserData;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class EntryView
{
    public static void display(Stage prev, String type)
    {
        Stage entryViewWindow  = new Stage();

        /*GridPane entryViewLayout = new GridPane();
        entryViewLayout.setHgap(10);
        entryViewLayout.setVgap(10);
        entryViewLayout.setPadding(new Insets(10, 10, 10, 10));
           */

        VBox entryViewLayout = new VBox();
        HBox buttons = new HBox();
        buttons.setPrefSize(500,100);
        VBox informationLayout = new VBox();

        informationLayout.setPadding(new Insets(10,10,10,10));
        informationLayout.setSpacing(30);
        informationLayout.setPrefSize(500,400);
        Label remarks = new Label("Current Remarks");
        remarks.setTextFill(Color.rgb(189,183,107));
        Label lAmount = new Label("Current Amount");
        lAmount.setTextFill(Color.rgb(189,183,107));
        Label lTag = new Label("Current Tag");
        lTag.setTextFill(Color.rgb(189,183,107));

        Label displayedRemarks = new Label(EntryData.getEntryName(EntryData.getCurrentEntry()));
        displayedRemarks.setTextFill(Color.rgb(0,191,255));
        Label displayedAmount = new Label(String.valueOf(EntryData.getEntryAmount(EntryData.getCurrentEntry())));
        displayedAmount.setTextFill(Color.rgb(0,191,255));
        Label displayedTag = new Label(EntryData.getEntryTag(EntryData.getCurrentEntry()));
        displayedTag.setTextFill(Color.rgb(0,191,255));

        Button edit = new Button("Edit");
        edit.setPrefSize(180,100);
        edit.setStyle("-fx-font: 15 Serif; -fx-base: #32CD32; ");

        Button cancel = new Button("Cancel");
        cancel.setPrefSize(180,100);
        cancel.setStyle("-fx-font: 15 Serif; -fx-base: #708090; ");

        Button deleteEntry = new Button("Delete Entry");
        deleteEntry.setPrefSize(140,100);
        deleteEntry.setStyle("-fx-font: 15 Serif; -fx-base: #FF6347; ");

        informationLayout.getChildren().addAll(remarks,displayedRemarks,lAmount,displayedAmount,lTag,displayedTag);

       /* entryViewLayout.add(remarks, 0, 0);
        entryViewLayout.add(displayedRemarks, 0, 2);
        entryViewLayout.add(lAmount, 0, 4);
        entryViewLayout.add(displayedAmount, 0, 6);
        entryViewLayout.add(lTag, 0, 8);
        entryViewLayout.add(displayedTag, 0, 10);
        */

        if(RecordData.hasEditEntryAccess(RecordData.getCurrentRecord(), UserData.getCurrentUser()) == true)
        {
            edit.setOnAction(e -> {
                entryViewWindow.close();
                EntryEditPage.display(prev, type);
            });
        }

        cancel.setOnAction(e -> {
            entryViewWindow.close();
            //prev.close();
            if(type.equals("Individual")) IndividualRecordEntryPage.display();
            else GroupRecordEntryPage.display();
        });

        if(RecordData.hasDeleteEntryAccess(RecordData.getCurrentRecord(), UserData.getCurrentUser()) == true)
        {
            deleteEntry.setOnAction(e -> {
                EntryData.deleteEntry(EntryData.getCurrentEntry());

                //prev.close();
                if(type.equals("Individual")) IndividualRecordEntryPage.display();
                else GroupRecordEntryPage.display();
                entryViewWindow.close();
            });
        }
        buttons.getChildren().addAll(edit,deleteEntry,cancel);
        entryViewLayout.getChildren().addAll(informationLayout,buttons);
        //entryViewLayout.setStyle("-fx-background: rgb(123,104,238);\n -fx-background-color: rgb(123,104,238)");

        Image img2 = null;
        try {
            img2 = new Image(new FileInputStream("Background3.jpg"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BackgroundImage myBI2= new BackgroundImage(img2,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        informationLayout.setBackground(new Background(myBI2));

        Scene entryViewScene = new Scene(entryViewLayout, 500, 500);
        entryViewWindow.setScene(entryViewScene);
        entryViewWindow.show();
    }
}
