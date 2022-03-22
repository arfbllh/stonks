package com.example.stonks1;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Vector;

public class EntryInputWindow
{
    public static void display(Stage prev)
    {
        Stage entryInputWindow = new Stage();

        GridPane entryInputWindowLayout = new GridPane();
        entryInputWindowLayout.setHgap(10);
        entryInputWindowLayout.setVgap(10);
        entryInputWindowLayout.setPadding(new Insets(10,10,10,10));
        entryInputWindowLayout.setAlignment(Pos.CENTER);

        TextField entry = new TextField();
        entry.setPromptText("Remarks");

        TextField amount = new TextField();
        amount.setPromptText("Amount");

        Label tagLabel = new Label("Enter new tag:");
        TextField tag = new TextField();

        Button cashIn = new Button("Cash in");
        Button cashOut = new Button("Cash out");
        Button cancel = new Button("Cancel");

        entryInputWindowLayout.add(entry, 0, 0);
        entryInputWindowLayout.add(amount, 0, 2);
        entryInputWindowLayout.add(tagLabel, 0, 4);
        entryInputWindowLayout.add(tag, 0, 6);

        entryInputWindowLayout.add(cashIn, 0, 8);
        entryInputWindowLayout.add(cashOut, 2, 8);
        entryInputWindowLayout.add(cancel, 4, 8);




       cashIn.setOnAction(e ->
        {
            String st = amount.getText();
            System.out.println(st);
            Vector<String> temp = new Vector<>();
            temp.add(tag.getText());
            int parsed = Integer.valueOf(st);
            EntryData.addEntry(entry.getText(), temp, parsed, true, RecordData.getCurrentRecord());
            prev.close();
            EntryPage.display();
            entryInputWindow.close();
        });

        cashOut.setOnAction(e -> {
            Vector<String> temp = new Vector<>();
            temp.add(tag.getText());
            int p = Integer.parseInt(amount.getText());
            EntryData.addEntry(entry.getText(), temp, p, false, RecordData.getCurrentRecord());
            prev.close();
            EntryPage.display();
            entryInputWindow.close();
        });

        cancel.setOnAction(e -> {
            entryInputWindow.close();
        });

        entryInputWindow.initModality(Modality.APPLICATION_MODAL);

        Scene entryInputWindowScene = new Scene(entryInputWindowLayout, 600, 300);
        entryInputWindow.setScene(entryInputWindowScene);
        entryInputWindow.show();
    }
}
