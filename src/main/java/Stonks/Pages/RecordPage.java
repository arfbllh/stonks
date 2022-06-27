package Stonks.Pages;

import Stonks.Windows.RecordInputWindow;
import Stonks.Records.RecordData;
import Stonks.Users.UserData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Vector;

public class RecordPage
{
    public static void display()
    {
        Stage recordWindow = new Stage();
        recordWindow.setTitle("Stonks");

        String name = UserData.getUsername(UserData.getCurrentUser());
        Vector<Integer> userRecords = RecordData.getUserRecords(UserData.getCurrentUser(),0);

        GridPane gridTopLayout = new GridPane();
        gridTopLayout.setAlignment(Pos.CENTER);
        gridTopLayout.setHgap(10);
        gridTopLayout.setVgap(10);
        gridTopLayout.setPadding(new Insets(30, 10, 30, 200));

        GridPane gridMidLayout = new GridPane();
        gridMidLayout.setAlignment(Pos.CENTER);
        gridMidLayout.setHgap(0);
        gridMidLayout.setVgap(10);
        gridMidLayout.setPadding(new Insets(10, 10, 10, 0));

        Button addRecord = new Button("Add Record");
        Button deleteRecord = new Button("Delete Record");
        Button logOut = new Button("Log Out");
        Button backButton = new Button("Back");

        Label intro = new Label("Records of ");
        Label userName = new Label(name);
        Label userName2 = new Label(name);
        Label userName3 = new Label(name);

        Label individual = new Label("Individual Records of ");
        Label group = new Label("Group Records of");

        intro.setFont(new Font("Arial", 30));
        //intro.setTextFill(Color.color(1, 0, 0));
        individual.setFont(new Font("Arial", 30));
        group.setFont(new Font("Arial", 30));
        userName.setFont(new Font("Arial", 30));
        userName.setTextFill(Color.color(0, .8, .8));

        userName2.setFont(new Font("Arial", 30));
        userName2.setTextFill(Color.color(0, .8, .8));

        userName3.setFont(new Font("Arial", 30));
        userName3.setTextFill(Color.color(0, .8, .8));

        int numOfRecords = userRecords.size();
        Button arrayOfButton[] = new Button[numOfRecords];


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

        for(int i = 0; i < numOfRecords; i++)
        {
            gridMidLayout.add(arrayOfButton[i], 15, i+4);
            int id= userRecords.get(i);
            arrayOfButton[i].setOnAction(e -> {
                RecordData.setCurrentRecord(id);
                recordWindow.close();
                EntryPage.display();
            });
        }

        int endOfIndividual = numOfRecords+6;

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

        //System.out.println(numOfRecords);

        for(int i = 0; i < numOfRecords; i++)
        {
            gridMidLayout.add(arrayOfButton2[i], 15, j);
            j += 2;
        }

        int endOfGroup = j + 2;

        logOut.setOnAction(e -> {
            UserData.setCurrentUser(-1);
            recordWindow.close();
            //LoginPage.display();
        });

        addRecord.setOnAction(e -> {
            RecordInputWindow.display(recordWindow);
        });

        backButton.setOnAction(e -> {
            recordWindow.close();
            //MenuPage.display();
        });

        gridTopLayout.add(logOut, 20, 0);
        gridTopLayout.add(intro, 0, 0);
        gridTopLayout.add(userName, 1, 0);

        gridMidLayout.add(group, 15, endOfIndividual);
        gridMidLayout.add(individual, 15, 2);
        gridMidLayout.add(userName2, 16, 2);
        gridMidLayout.add(userName3,16, endOfIndividual);
        gridMidLayout.add(addRecord, 15,endOfGroup);
        gridMidLayout.add(backButton, 20, endOfGroup);



        ScrollPane scrollRecordLayout = new ScrollPane();
        scrollRecordLayout.setContent(gridMidLayout);
        scrollRecordLayout.setPadding(new Insets(30, 300, 30, 300));

        BorderPane finalRecordLayout = new BorderPane();

        BorderPane.setAlignment(gridTopLayout, Pos.CENTER);
        BorderPane.setAlignment(scrollRecordLayout, Pos.CENTER);

        finalRecordLayout.setTop(gridTopLayout);
        finalRecordLayout.setCenter(scrollRecordLayout);
        finalRecordLayout.setPadding(new Insets(10, 10, 10, 10));

        Scene recordScene = new Scene(finalRecordLayout, 1200, 800);
        recordWindow.setScene(recordScene);
        recordWindow.show();
    }
}
