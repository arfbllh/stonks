package Stonks.Pages;

import Stonks.Entries.EntryData;
import Stonks.Windows.EntryInputWindow;
import Stonks.MemberConstants;
import Stonks.Records.RecordData;
import Stonks.Users.UserData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Vector;

public class EntryPage
{
    public static void display()
    {
        Stage EntryWindow = new Stage();
        EntryWindow.setTitle("Stonks");

        GridPane EntryLayout = new GridPane();
        EntryLayout.setVgap(10);
        EntryLayout.setHgap(10);
        EntryLayout.setAlignment(Pos.CENTER);
        EntryLayout.setPadding(new Insets(10, 10, 10, 10));

        GridPane leftCornerLayout = new GridPane();
        leftCornerLayout.setVgap(10);
        leftCornerLayout.setHgap(10);
        leftCornerLayout.setPadding(new Insets(10, 10, 10, 10));


        VBox vleftCornerTopLayout = new VBox();

        //vleftCornerTopLayout.setBorder(new Border(new BorderStroke(Color.BLACK,
        //BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));



        leftCornerLayout.add(vleftCornerTopLayout, 0, 0);


        GridPane rightcornerGridLayout = new GridPane();
        rightcornerGridLayout.setVgap(10);
        rightcornerGridLayout.setHgap(10);
        //rightcornerGridLayout.setAlignment(Pos.CENTER);
        rightcornerGridLayout.setPadding(new Insets(10, 10, 10, 10));

        GridPane entryTopLayout = new GridPane();
        entryTopLayout.setPadding(new Insets(10, 10, 10, 10));
        entryTopLayout.setHgap(10);
        entryTopLayout.setVgap(10);
        entryTopLayout.setAlignment(Pos.CENTER);


        Button addEntry = new Button("Add Entry");

        addEntry.setOnAction(e -> {
            EntryInputWindow.display(EntryWindow);
        });


        Vector<Integer> recordEntries= EntryData.getRecordEntries(RecordData.getCurrentRecord());

        int numberOfEntries = recordEntries.size();

        Label entries[] = new Label[numberOfEntries];
        Button viewButtons[] = new Button[numberOfEntries];
        Button deleteRecord = new Button("Delete Record");
        Button backButton = new Button("Back");
        Button member = new Button("Members");
        Button leave = new Button("Leave Record");

        Label cashAmount[] = new Label[numberOfEntries];
        Label tags[] = new Label[numberOfEntries];

        Label totalCashIn = new Label("Total Cash In:");
        Label totalCashOut = new Label("Total Cash out:");
        Label netCash = new Label("Net Cash:");

        int totalIn = EntryData.getRecordCashIn(RecordData.getCurrentRecord());
        int totalOut = EntryData.getRecordCashOut(RecordData.getCurrentRecord());
        int net = EntryData.getRecordNet(RecordData.getCurrentRecord());

        Label cashInAmount = new Label(String.valueOf(totalIn));
        cashInAmount.setTextFill(Color.color(0, .5, 0));
        Label cashOutAmount = new Label(String.valueOf(totalOut));

        cashOutAmount.setTextFill(Color.color(.5, 0, 0));
        Label netAmount = new Label(String.valueOf(net));
        if(net < 0)netAmount.setTextFill(Color.color(.5, 0, 0));
        else netAmount.setTextFill(Color.color(0, .5, 0));

        backButton.setOnAction(e -> {
            EntryWindow.close();
            RecordPage.display();
        });

        for(int i = 0; i < numberOfEntries; i++)
        {
            int id= recordEntries.get(i);
            String entryName= EntryData.getEntryName(id);

            entries[i] = new Label(entryName);
            entries[i].setPrefSize(100, 40);

            viewButtons[i] = new Button("View");

            if(EntryData.isCashIn(id) == true)
            {
                String t = String.valueOf(EntryData.getEntryAmount(id));
                cashAmount[i] = new Label(t);
                cashAmount[i].setPrefSize(80, 20);
                cashAmount[i].setTextFill(Color.color(0, .5, 0));
            }

            else if(EntryData.isCashIn(id) == false)
            {
                String t = String.valueOf(EntryData.getEntryAmount(id));
                cashAmount[i] = new Label(t);
                cashAmount[i].setPrefSize(80, 20);
                cashAmount[i].setTextFill(Color.color(.5, 0, 0));
                //cashType[i].setStyle("-fx-background-color: #ff0000; ");
            }

            String temptag = EntryData.getEntryTag(id);

            tags[i] = new Label(temptag);
            tags[i].setPrefSize(80, 40);
            tags[i].setTextFill(Color.color(0, 0, .8));

            viewButtons[i].setOnAction(e -> {
                EntryData.setCurrentEntry(id);
                //EntryView.display(EntryWindow);
            });
        }

        Label intro = new Label("Entries in ");
        Label recordName = new Label(RecordData.getRecordName(RecordData.getCurrentRecord()));
        intro.setFont(new Font("Arial", 30));
        recordName.setFont(new Font("Arial", 30));
        recordName.setTextFill(Color.color(.8, .8, 0));

        vleftCornerTopLayout.getChildren().addAll(totalCashIn, cashInAmount, totalCashOut, cashOutAmount, netCash, netAmount);

        for(int i = 0; i < numberOfEntries; i++)
        {
            EntryLayout.add(entries[i], 0, i+2);
            EntryLayout.add(cashAmount[i], 1, i+2);
            EntryLayout.add(tags[i], 2, i+2);
            EntryLayout.add(viewButtons[i], 3, i+2);
        }

        if(RecordData.hasDeleteRecordAccess(RecordData.getCurrentRecord(), UserData.getCurrentUser()))
        {
            deleteRecord.setOnAction(e -> {
                RecordData.deleteRecord(RecordData.getCurrentRecord());
                RecordData.setCurrentRecord(-1);
                EntryWindow.close();
                RecordPage.display();
            });
        }

        if(RecordData.isIndividual(RecordData.getCurrentRecord()) == false)
        {
            Button addUser = new Button("Add User");
            rightcornerGridLayout.add(addUser,0, 3);
            rightcornerGridLayout.add(leave, 0, 4);

            leave.setOnAction(e -> {
                RecordData.removeRecordMember(RecordData.getCurrentRecord(), UserData.getCurrentUser());
                EntryWindow.close();
                RecordPage.display();
            });

            Label status = new Label();
            Label lstatus = new Label("Your Status :");

            if(RecordData.getRecordMemberStatus(RecordData.getCurrentRecord(), UserData.getCurrentUser()) == MemberConstants.ALPHA)
            {
                status.setText("Alpha");
                status.setTextFill(Color.color(.5, 0, 0));
            }

            else if(RecordData.getRecordMemberStatus(RecordData.getCurrentRecord(), UserData.getCurrentUser()) == MemberConstants.SIGMA)
            {
                status.setText("Sigma");
                status.setTextFill(Color.color(0, 0, .5));
            }

            else if(RecordData.getRecordMemberStatus(RecordData.getCurrentRecord(), UserData.getCurrentUser()) == MemberConstants.OMEGA)
            {
                status.setText("Omega");
                status.setTextFill(Color.color(0, .4, 0));
            }

            rightcornerGridLayout.add(lstatus, 0, 5);
            rightcornerGridLayout.add(status, 0, 6);

            if(RecordData.hasAddMemberAccess(RecordData.getCurrentRecord(), UserData.getCurrentUser()) == true)
            {
                addUser.setOnAction( e -> {
                    AddUserPage.display(EntryWindow);
                });
            }

            rightcornerGridLayout.add(member, 0, 8);

            member.setOnAction(e -> {
                RecordMemberPage.display(EntryWindow);
            });
        }


        rightcornerGridLayout.add(addEntry, 0, 0);
        rightcornerGridLayout.add(deleteRecord, 0, 1);
        rightcornerGridLayout.add(backButton, 0, 2);

        ScrollPane scrollEntryLayout = new ScrollPane();
        scrollEntryLayout.setPadding(new Insets(30, 300, 30, 300));
        scrollEntryLayout.setContent(EntryLayout);

        entryTopLayout.add(intro, 0, 0);
        entryTopLayout.add(recordName, 1, 0);



        BorderPane finalEntryLayout = new BorderPane();
        finalEntryLayout.setTop(entryTopLayout);
        finalEntryLayout.setCenter(scrollEntryLayout);
        finalEntryLayout.setRight(rightcornerGridLayout);
        finalEntryLayout.setLeft(leftCornerLayout);

        BorderPane.setAlignment(intro, Pos.CENTER);
        BorderPane.setAlignment(scrollEntryLayout, Pos.CENTER);

        Scene EntryScene  = new Scene(finalEntryLayout, 1200, 800);
        EntryWindow.setScene(EntryScene);
        EntryWindow.show();
    }
}
