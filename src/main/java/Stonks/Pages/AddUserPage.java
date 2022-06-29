package Stonks.Pages;

import Stonks.Entries.EntryData;
import Stonks.Records.RecordData;
import Stonks.Users.UserData;
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
//import sun.awt.X11.InfoWindow;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Vector;

public class AddUserPage
{
    static Vector<String> addedUsers = new Vector<>();
    public static void display(Stage prev)
    {
        Stage addUserWindow = new Stage();

        /*GridPane addUserLayout = new GridPane();
        addUserLayout.setAlignment(Pos.CENTER);
        addUserLayout.setHgap(10);
        addUserLayout.setVgap(10);
        addUserLayout.setPadding(new Insets(10, 10, 10, 10));
        */

        VBox addUserLayout = new VBox();

        VBox informationLayout = new VBox();
        HBox buttons = new HBox();

        buttons.setPrefSize(400,100);
        informationLayout.setSpacing(30);
        informationLayout.setPrefSize(400,300);

        informationLayout.setPadding(new Insets(10,10,10,10));

        Label enterName = new Label("Enter the user name(s) :");
        enterName.setTextFill(Color.rgb(189,183,107));

        TextField addName = new TextField();

        Button addUser = new Button("+");

        HBox takename = new HBox();
        takename.setSpacing(20);
        takename.setPadding(new Insets(10,10,10,10));

        Button back = new Button("Back");
        Button confirm = new Button("Confirm");

        back.setPrefSize(250,100);
        confirm.setPrefSize(250,100);

        confirm.setStyle("-fx-font: 15 Serif; -fx-base: #228B22; ");
        back.setStyle("-fx-font: 22 Serif; -fx-base: #708090; ");

        takename.getChildren().addAll(addUser,addName);

        /*addUserLayout.add(enterName, 0, 0);
        addUserLayout.add(addName, 0, 2);
        addUserLayout.add(addUser, 1, 2);
        addUserLayout.add(back, 1, addedUsers.size()+5);
        addUserLayout.add(confirm, 0, addedUsers.size()+5);
        */

        Label names[] = new Label[addedUsers.size()];

        HBox addedNames = new HBox();
        addedNames.setPadding(new Insets(10,10,10,10));
        addedNames.setSpacing(10);
        for(int i = 0; i < addedUsers.size(); i++)
        {
            names[i] = new Label();
            names[i].setTextFill(Color.rgb(189,183,107));
            names[i].setText(addedUsers.get(i));
            addedNames.getChildren().add(names[i]);
        }

        int labelNo = 3;

        addUser.setOnAction(e -> {
            int invitee = UserData.getUserIdByName(addName.getText());
            if(invitee != -1 && !RecordData.isUserAdded(RecordData.getCurrentRecord(),invitee) && !addedUsers.contains(addName.getText())) {
                addedUsers.add(addName.getText());
                addUserWindow.close();
            }
            AddUserPage.display(prev);
            addUserWindow.close();
        });

        //InfoWindow.Balloon GroupRecordEntryPage = null;
        back.setOnAction(e -> {
            addedUsers.clear();

            //prev.close();
            addUserWindow.close();
            GroupRecordEntryPage.display();
        });

        confirm.setOnAction(e -> {
           for(int i=0;i<addedUsers.size();i++){
               int userId= UserData.getUserIdByName(addedUsers.get(i));
               UserData.addUserInvite(UserData.getCurrentUser(),userId, RecordData.getCurrentRecord());
           }

            addedUsers.clear();

           // prev.close();
            addUserWindow.close();
            GroupRecordEntryPage.display();
        });

        addUserWindow.setOnCloseRequest(e ->{
            addedUsers.clear();

            //prev.close();
            addUserWindow.close();
            GroupRecordEntryPage.display();
        });

        buttons.getChildren().addAll(confirm,back);
        informationLayout.getChildren().addAll(enterName,takename, addedNames);
        addUserLayout.getChildren().addAll(informationLayout,buttons);

        Image img2 = null;
        img2 = new Image("Background3.jpg");
        BackgroundImage myBI2= new BackgroundImage(img2,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        addUserLayout.setBackground(new Background(myBI2));
        addUserLayout.setStyle("-fx-background: rgb(74,101,114);\n -fx-background-color: rgb(74,101,114)");

        addUserWindow.initModality(Modality.APPLICATION_MODAL);
        Scene addUserScene = new Scene(addUserLayout, 400, 400);
        addUserWindow.setScene(addUserScene);
        addUserWindow.show();
    }

    public static class EntryView
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
            img2 = new Image("Background3.jpg");
            BackgroundImage myBI2= new BackgroundImage(img2,
                    BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            informationLayout.setBackground(new Background(myBI2));
            informationLayout.setStyle("-fx-background: rgb(74,101,114);\n -fx-background-color: rgb(74,101,114)");

            Scene entryViewScene = new Scene(entryViewLayout, 500, 500);
            entryViewWindow.setScene(entryViewScene);
            entryViewWindow.show();
        }
    }
}
