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

public class AddUserPage
{
    static Vector<String> addedUsers = new Vector<>();
    public static void display(Stage prev)
    {
        Stage addUserWindow = new Stage();

        GridPane addUserLayout = new GridPane();
        addUserLayout.setAlignment(Pos.CENTER);
        addUserLayout.setHgap(10);
        addUserLayout.setVgap(10);
        addUserLayout.setPadding(new Insets(10, 10, 10, 10));


        Label enterName = new Label("Enter the user name(s) :");

        TextField addName = new TextField();

        Button addUser = new Button("+");
        Button back = new Button("Back");
        Button confirm = new Button("Confirm");

        addUserLayout.add(enterName, 0, 0);
        addUserLayout.add(addName, 0, 2);
        addUserLayout.add(addUser, 1, 2);
        addUserLayout.add(back, 1, addedUsers.size()+5);
        addUserLayout.add(confirm, 0, addedUsers.size()+5);

        Label names[] = new Label[addedUsers.size()];

        for(int i = 0; i < addedUsers.size(); i++)
        {
            names[i] = new Label();
            names[i].setText(addedUsers.get(i));
            addUserLayout.add(names[i], 0, i+3);
        }

        int labelNo = 3;

        addUser.setOnAction(e -> {
            if(UserData.getUserIdByName(addName.getText())!=-1) {
                addedUsers.add(addName.getText());
                addUserWindow.close();
            }
            AddUserPage.display(prev);
            addUserWindow.close();
        });

        back.setOnAction(e -> {
            addedUsers.clear();

            prev.close();
            addUserWindow.close();
            EntryPage.display();
        });

        confirm.setOnAction(e -> {
           for(int i=0;i<addedUsers.size();i++){
               int userId= UserData.getUserIdByName(addedUsers.get(i));
               UserData.addUserInvite(UserData.getCurrentUser(),userId,RecordData.getCurrentRecord());
           }

            addedUsers.clear();

            prev.close();
            addUserWindow.close();
            EntryPage.display();
        });

        addUserWindow.initModality(Modality.APPLICATION_MODAL);
        Scene addUserScene = new Scene(addUserLayout, 600, 300);
        addUserWindow.setScene(addUserScene);
        addUserWindow.show();
    }
}
