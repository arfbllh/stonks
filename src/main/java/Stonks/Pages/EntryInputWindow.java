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

import java.io.FileNotFoundException;
import java.util.Vector;

public class EntryInputWindow
{
    public static void display(Stage prev, String type)
    {
        Stage entryInputWindow = new Stage();

        VBox entryInputWindowLayout = new VBox();


        /*GridPane entryInputWindowLayout = new GridPane();
        entryInputWindowLayout.setHgap(10);
        entryInputWindowLayout.setVgap(10);
        entryInputWindowLayout.setPadding(new Insets(10,10,10,10));
        entryInputWindowLayout.setAlignment(Pos.CENTER);
        */
        TextField entry = new TextField();
        entry.setPromptText("Remarks");

        TextField amount = new TextField();
        amount.setPromptText("Amount");

        Label tagLabel = new Label("Enter tag:");
        tagLabel.setTextFill(Color.rgb(189,183,107));
        Label tagRemarks = new Label("Enter remarks:");
        tagRemarks.setTextFill(Color.rgb(189,183,107));
        Label tagAmount = new Label("Enter amount");
        tagAmount.setTextFill(Color.rgb(189,183,107));
        TextField tag = new TextField();

        Button cashIn = new Button("Cash in");
        Button cashOut = new Button("Cash out");
        Button cancel = new Button("Cancel");

        cashIn.setPrefSize(180,100);
        cashOut.setPrefSize(180,100);
        cancel.setPrefSize(140,100);

        cashIn.setStyle("-fx-font: 15 Serif; -fx-base: #228B22; ");
        cashOut.setStyle("-fx-font: 15 Serif; -fx-base: #800000; ");
        cancel.setStyle("-fx-font: 22 Serif; -fx-base: #708090; ");

        /*entryInputWindowLayout.add(entry, 0, 0);
        entryInputWindowLayout.add(amount, 0, 2);
        entryInputWindowLayout.add(tagLabel, 0, 4);
        entryInputWindowLayout.add(tag, 0, 6);

        entryInputWindowLayout.add(cashIn, 0, 8);
        entryInputWindowLayout.add(cashOut, 2, 8);
        entryInputWindowLayout.add(cancel, 4, 8);*/

        HBox buttons = new HBox();

        VBox informationLayout = new VBox();
        informationLayout.setPadding(new Insets(50,50,0,50));
        informationLayout.setSpacing(30);

        informationLayout.getChildren().addAll(tagRemarks, entry,tagAmount, amount,tagLabel,tag);
        informationLayout.setPrefSize(500, 400);
        //informationLayout.setStyle("-fx-background: rgb(123,104,238);\n -fx-background-color: rgb(123,104,238)");

        Image img2 = null;
        img2 = new Image("Background3.jpg");
        BackgroundImage myBI2= new BackgroundImage(img2,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        informationLayout.setBackground(new Background(myBI2));

        buttons.getChildren().addAll(cashIn,cashOut,cancel);

        //entryInputWindowLayout.setSpacing(30);
        entryInputWindowLayout.getChildren().addAll(informationLayout,buttons);


       cashIn.setOnAction(e ->
        {
            String st = amount.getText();
            System.out.println(st);
            Vector<String> temp = new Vector<>();
            temp.add(tag.getText());
            //int parsed = Integer.valueOf(st);
            int check= EntryData.addEntry(entry.getText(), temp, st, true, RecordData.getCurrentRecord());
            if(check==-1){
                WarningAlert warning = new WarningAlert("Invalid Input", "Fields can not be empty!");
                warning.display();
            }
            else if(check==0){
                WarningAlert warning = new WarningAlert("Invalid Amount", "Amount can not be non numeric!");
                warning.display();
            }
           // prev.close();
            else {
                if (type.equals("Individual")) IndividualRecordEntryPage.display();
                else GroupRecordEntryPage.display();

                entryInputWindow.close();
            }
        });

        cashOut.setOnAction(e -> {
            Vector<String> temp = new Vector<>();
            temp.add(tag.getText());
            String st = amount.getText();
            //int p = Integer.parseInt(amount.getText());
            int check= EntryData.addEntry(entry.getText(), temp, st, false, RecordData.getCurrentRecord());
            if(check==-1){
                WarningAlert warning = new WarningAlert("Invalid Input", "Fields can not be empty!");
                warning.display();
            }
            else if(check==0){
                WarningAlert warning = new WarningAlert("Invalid Amount", "Amount can not be non numeric!");
                warning.display();
            }
            //EntryData.addEntry(entry.getText(), temp, p, false, RecordData.getCurrentRecord());

           // prev.close();

            if(type.equals("Individual")) IndividualRecordEntryPage.display();
            else GroupRecordEntryPage.display();

            entryInputWindow.close();
        });

        cancel.setOnAction(e -> {
            entryInputWindow.close();
        });

        entryInputWindow.initModality(Modality.APPLICATION_MODAL);

        Scene entryInputWindowScene = new Scene(entryInputWindowLayout, 500, 500);
        entryInputWindow.setScene(entryInputWindowScene);
        entryInputWindow.show();
    }
}
