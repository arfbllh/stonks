package com.example.stonks1;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MenuPage
{
    public static void display()
    {
        Stage menuWindow = new Stage();
        menuWindow.setTitle("com/example/stonks1");

        GridPane menuLayout = new GridPane();
        menuLayout.setAlignment(Pos.CENTER);
        menuLayout.setHgap(10);
        menuLayout.setVgap(10);
        menuLayout.setPadding(new Insets(10, 10, 10, 10));

        Button records = new Button("Records");
        Button predictions = new Button("Predictions");
        Button history = new Button("History");
        Button query = new Button("Query");
        Button Invite = new Button("Invite");
        Button logOut = new Button("Log Out");

        logOut.setOnAction(e -> {
            UserData.setCurrentUser(-1);
            menuWindow.close();
            LoginPage.display();
        });

        menuLayout.add(logOut, 20, 0);
        menuLayout.add(records, 0, 0);
        menuLayout.add(predictions, 3, 0);
        menuLayout.add(query, 0, 1);
        menuLayout.add(history, 3, 1);
        menuLayout.add(Invite, 0, 2);

        Invite.setOnAction(e -> {
            InvitePage.display();
        });

        records.setOnAction(e -> {
            menuWindow.close();
            RecordPage.display();
        });

        Scene menuScene = new Scene(menuLayout, 1200, 800);
        menuWindow.setScene(menuScene);
        menuWindow.show();
    }
}
