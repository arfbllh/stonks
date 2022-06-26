package Stonks.Pages;

import Stonks.Entries.EntryData;
import Stonks.Entries.EntryView;
import Stonks.Records.RecordData;
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

public class EntryEditPage
{
    public static void display(Stage prev)
    {
        Stage entryEditWindow = new Stage();

        GridPane entryEditLayout = new GridPane();
        entryEditLayout.setVgap(10);
        entryEditLayout.setHgap(10);
        entryEditLayout.setPadding(new Insets(10, 10, 10, 10));
        entryEditLayout.setAlignment(Pos.CENTER);

        TextField entry = new TextField();
        entry.setText(EntryData.getEntryName(EntryData.getCurrentEntry()));

        TextField amount = new TextField();
        amount.setText(String.valueOf(EntryData.getEntryAmount(EntryData.getCurrentEntry())));

        TextField tag = new TextField();
        tag.setText(EntryData.getEntryTag(EntryData.getCurrentEntry()));

        Button cancel = new Button("Cancel");
        Button update = new Button("Update Entry");
        Button deleteEntry = new Button("Delete Entry");

        Label remarks = new Label("Current Remarks");
        Label lAmount = new Label("Current Amount");
        Label lTag = new Label("Current Tag");

        update.setOnAction(e -> {
            Vector<String> temp = new Vector<>();
            temp.add(tag.getText());
            int curEntry= EntryData.getCurrentEntry();

            EntryData.addEntry(entry.getText(), temp, Integer.valueOf(amount.getText()),EntryData.isCashIn(curEntry), RecordData.getCurrentRecord() );
            EntryData.deleteEntry(EntryData.getCurrentEntry());

            prev.close();
            EntryPage.display();
            entryEditWindow.close();
        });

        cancel.setOnAction(e -> {
            entryEditWindow.close();
            EntryView.display(prev);
        });

        entryEditLayout.add(remarks, 0, 0);
        entryEditLayout.add(entry, 0, 2);
        entryEditLayout.add(lAmount, 0, 4);
        entryEditLayout.add(amount, 0, 6);
        entryEditLayout.add(lTag, 0, 8);
        entryEditLayout.add(tag, 0, 10);
        entryEditLayout.add(deleteEntry, 6, 12);

        entryEditLayout.add(cancel, 4, 12);
        entryEditLayout.add(update, 2, 12);

        entryEditWindow.initModality(Modality.APPLICATION_MODAL);

        Scene entryEditScene = new Scene(entryEditLayout, 600, 300);
        entryEditWindow.setScene(entryEditScene);
        entryEditWindow.show();
    }
}
