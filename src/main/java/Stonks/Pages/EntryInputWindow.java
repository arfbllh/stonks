package Stonks.Pages;

import Stonks.Entries.EntryData;
import Stonks.Records.RecordData;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
        Label tagRemarks = new Label("Enter remarks:");
        Label tagAmount = new Label("Enter amount");
        TextField tag = new TextField();

        Button cashIn = new Button("Cash in");
        Button cashOut = new Button("Cash out");
        Button cancel = new Button("Cancel");

        cashIn.setPrefSize(180,100);
        cashOut.setPrefSize(180,100);
        cancel.setPrefSize(140,100);

        cashIn.setStyle("-fx-font: 15 Serif; -fx-base: #32CD32; ");
        cashOut.setStyle("-fx-font: 15 Serif; -fx-base: #DC143C; ");
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
        informationLayout.setStyle("-fx-background: rgb(123,104,238);\n -fx-background-color: rgb(123,104,238)");

        buttons.getChildren().addAll(cashIn,cashOut,cancel);

        //entryInputWindowLayout.setSpacing(30);
        entryInputWindowLayout.getChildren().addAll(informationLayout,buttons);


       cashIn.setOnAction(e ->
        {
            String st = amount.getText();
            System.out.println(st);
            Vector<String> temp = new Vector<>();
            temp.add(tag.getText());
            int parsed = Integer.valueOf(st);
            EntryData.addEntry(entry.getText(), temp, parsed, true, RecordData.getCurrentRecord());

           // prev.close();

            if(type.equals("Individual"))IndividualRecordEntryPage.display();
            else GroupRecordEntryPage.display();

            entryInputWindow.close();
        });

        cashOut.setOnAction(e -> {
            Vector<String> temp = new Vector<>();
            temp.add(tag.getText());
            int p = Integer.parseInt(amount.getText());
            EntryData.addEntry(entry.getText(), temp, p, false, RecordData.getCurrentRecord());

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
