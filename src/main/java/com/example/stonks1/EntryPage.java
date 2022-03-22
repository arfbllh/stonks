package com.example.stonks1;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Vector;

public class EntryPage
{
    public static void display()
    {
        Stage EntryWindow = new Stage();
        EntryWindow.setTitle("com/example/stonks1");

        GridPane EntryLayout = new GridPane();
        EntryLayout.setVgap(10);
        EntryLayout.setHgap(10);
        EntryLayout.setAlignment(Pos.CENTER);
        EntryLayout.setPadding(new Insets(10, 10, 10, 10));

        Button addEntry = new Button("Add Entry");

        addEntry.setOnAction(e -> {
            EntryInputWindow.display(EntryWindow);
        });


        Vector<Integer> recordEntries= EntryData.getRecordEntries(RecordData.getCurrentRecord());

        int numberOfEntries = recordEntries.size();

        Button entries[] = new Button[numberOfEntries];
        Button deleteRecord = new Button("Delete Record");
        Button backButton = new Button("Back");

        backButton.setOnAction(e -> {
            EntryWindow.close();
            RecordPage.display();
        });

        for(int i = 0; i < numberOfEntries; i++)
        {
            int id= recordEntries.get(i);
            String entryName= EntryData.getEntryName(id);
            entries[i] = new Button(entryName);
            entries[i].setPrefSize(200, 40);

            entries[i].setOnAction(e -> {
                EntryData.setCurrentEntry(id);
                EntryEditPage.display(EntryWindow);
            });
        }

        for(int i = 0; i < numberOfEntries; i++)
        {
            EntryLayout.add(entries[i], 0, i);
        }

        deleteRecord.setOnAction(e -> {
            RecordData.deleteRecord(RecordData.getCurrentRecord());
            RecordData.setCurrentRecord(-1);
            EntryWindow.close();
            RecordPage.display();
        });

         if(RecordData.isIndividual(RecordData.getCurrentRecord()) == false)
        {
            Button addUser = new Button("Add User");
            EntryLayout.add(addUser,3, numberOfEntries+3);

            addUser.setOnAction( e -> {
                AddUserPage.display(EntryWindow);
            });
        }

        EntryLayout.add(addEntry, 0, numberOfEntries+3);
        EntryLayout.add(deleteRecord, 1, numberOfEntries+3);
        EntryLayout.add(backButton, 2, numberOfEntries+3);

        Scene EntryScene  = new Scene(EntryLayout, 1200, 800);
        EntryWindow.setScene(EntryScene);
        EntryWindow.show();
    }
}
