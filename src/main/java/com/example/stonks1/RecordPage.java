package com.example.stonks1;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Vector;

public class RecordPage
{
    public static void display()
    {
        Stage recordWindow = new Stage();
        recordWindow.setTitle("com/example/stonks1");

        GridPane recordLayout = new GridPane();
        recordLayout.setAlignment(Pos.CENTER);
        recordLayout.setHgap(10);
        recordLayout.setVgap(10);
        recordLayout.setPadding(new Insets(10, 10, 10, 10));

        Button addRecord = new Button("Add Record");
        Button deleteRecord = new Button("Delete Record");

        Button logOut = new Button("Log Out");

        recordLayout.add(logOut, 20, 0);

        logOut.setOnAction(e -> {
            UserData.setCurrentUser(-1);
            recordWindow.close();
            LoginPage.display();
        });

        String name = UserData.getUsername(UserData.getCurrentUser());

        Label intro = new Label("Records of " + name);

        Label individual = new Label("Individual Records of "+ name);

       /* RecordData.addRecord("REC1",0,true);
        RecordData.addRecord("REC2",0,true);
        RecordData.addRecord("REC3",0,true);

        RecordData.addRecord("REC21",0,false);
        RecordData.addRecord("REC22",0,false);
        RecordData.addRecord("REC23",0,false);
        */

        Vector<Integer> userRecords = RecordData.getUserRecords(UserData.getCurrentUser(),0);

        int numOfRecords = userRecords.size();
        Button arrayOfButton[] = new Button[numOfRecords];
        Button backButton = new Button("Back");

        backButton.setOnAction(e -> {
            recordWindow.close();
            MenuPage.display();
        });

        //System.out.println("IND: ");
        for(int i=0;i<numOfRecords;i++){
            System.out.println(RecordData.getRecordName(userRecords.get(i)));
        }

        for(int i = 0; i < numOfRecords; i++)
        {
            int id= userRecords.get(i);
            String recName= RecordData.getRecordName(id);
            arrayOfButton[i] = new Button(recName);
        }



        Label group = new Label("Group Records of " + name);

        recordLayout.add(intro, 0, 0);
        recordLayout.add(individual, 0, 2);

        for(int i = 0; i < numOfRecords; i++)
        {
            recordLayout.add(arrayOfButton[i], 0, i+4);
            int id= userRecords.get(i);
            arrayOfButton[i].setOnAction(e -> {
                RecordData.setCurrentRecord(id);
                recordWindow.close();
                EntryPage.display();
            });
        }

        int endOfIndividual = numOfRecords+6;

        recordLayout.add(group, 0, endOfIndividual);

        userRecords= RecordData.getUserRecords(UserData.getCurrentUser(),1);

        numOfRecords = userRecords.size();

        Button arrayOfButton2[] = new Button[numOfRecords];

        for(int i = 0; i < numOfRecords; i++)
        {
            int id= userRecords.get(i);
            String recName= RecordData.getRecordName(id);
            arrayOfButton2[i] = new Button(recName);
            arrayOfButton2[i].setOnAction(e -> {
                RecordData.setCurrentRecord(id);
                recordWindow.close();
                EntryPage.display();
            });
        }

        int j = endOfIndividual + 2;

        System.out.println(numOfRecords);

        for(int i = 0; i < numOfRecords; i++)
        {
            recordLayout.add(arrayOfButton2[i], 0, j);
            j += 2;
        }

        int endOfGroup = j + 2;

        recordLayout.add(addRecord, 0,endOfGroup);
        recordLayout.add(backButton, 1, endOfGroup);

        addRecord.setOnAction(e -> {
            RecordInputWindow.display(recordWindow);
        });


        Scene recordScene = new Scene(recordLayout, 1200, 800);
        recordWindow.setScene(recordScene);
        recordWindow.show();


    }
}
