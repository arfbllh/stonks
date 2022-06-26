package Stonks.Entries;

import Stonks.Entries.EntryData;
import Stonks.Pages.EntryEditPage;
import Stonks.Pages.EntryPage;
import Stonks.Records.RecordData;
import Stonks.Users.UserData;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EntryView
{
    public static void display(Stage prev)
    {
        Stage entryViewWindow  = new Stage();

        GridPane entryViewLayout = new GridPane();
        entryViewLayout.setHgap(10);
        entryViewLayout.setVgap(10);
        entryViewLayout.setPadding(new Insets(10, 10, 10, 10));

        Label remarks = new Label("Current Remarks");
        Label lAmount = new Label("Current Amount");
        Label lTag = new Label("Current Tag");

        Label displayedRemarks = new Label(EntryData.getEntryName(EntryData.getCurrentEntry()));
        Label displayedAmount = new Label(String.valueOf(EntryData.getEntryAmount(EntryData.getCurrentEntry())));
        Label displayedTag = new Label(EntryData.getEntryTag(EntryData.getCurrentEntry()));

        Button edit = new Button("Edit");
        Button cancel = new Button("Cancel");
        Button deleteEntry = new Button("Delete Entry");

        entryViewLayout.add(remarks, 0, 0);
        entryViewLayout.add(displayedRemarks, 0, 2);
        entryViewLayout.add(lAmount, 0, 4);
        entryViewLayout.add(displayedAmount, 0, 6);
        entryViewLayout.add(lTag, 0, 8);
        entryViewLayout.add(displayedTag, 0, 10);

        if(RecordData.hasEditEntryAccess(RecordData.getCurrentRecord(), UserData.getCurrentUser()) == true)
        {
            edit.setOnAction(e -> {
                entryViewWindow.close();
                EntryEditPage.display(prev);
            });
        }

        cancel.setOnAction(e -> {
            entryViewWindow.close();
            prev.close();
            EntryPage.display();
        });

        if(RecordData.hasDeleteEntryAccess(RecordData.getCurrentRecord(), UserData.getCurrentUser()) == true)
        {
            deleteEntry.setOnAction(e -> {
                EntryData.deleteEntry(EntryData.getCurrentEntry());

                prev.close();
                EntryPage.display();
                entryViewWindow.close();
            });
        }

        entryViewLayout.add(edit, 0, 12);
        entryViewLayout.add(cancel, 2, 12);
        entryViewLayout.add(deleteEntry, 1,12);

        Scene entryViewScene = new Scene(entryViewLayout, 600, 300);
        entryViewWindow.setScene(entryViewScene);
        entryViewWindow.show();
    }
}
