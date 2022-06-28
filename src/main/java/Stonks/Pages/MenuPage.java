package Stonks.Pages;

import Stonks.Users.UserData;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MenuPage
{
    public static void display()
    {
        window.Window.setTitle("Stonks");
        /*GridPane menuLayout = new GridPane();
        menuLayout.setAlignment(Pos.CENTER);
        menuLayout.setHgap(10);
        menuLayout.setVgap(10);
        menuLayout.setPadding(new Insets(10, 10, 10, 10));*/

        VBox menuLayout = new VBox();

        HBox upperHBox = new HBox();

        //upperHBox.setSpacing(10);
        upperHBox.setAlignment(Pos.CENTER);
        upperHBox.setPrefSize(600, 400);

        HBox lowerHBox = new HBox();
        lowerHBox.setAlignment(Pos.CENTER);
        lowerHBox.setPrefSize(600,400);


        menuLayout.getChildren().addAll(upperHBox,lowerHBox);

        VBox individualRecordBox = new VBox();
        VBox groupRecordsBox = new VBox();

        Button individualRecords = new Button("Individual Records");
        Button groupRecords = new Button("Group Records");
        Button Invite = new Button("Invites");
        Button logOut = new Button("Log Out");

        logOut.setOnAction(e -> {
            UserData.setCurrentUser(-1);
            //menuWindow.close();
            LoginPage.display();
        });

        individualRecordBox.getChildren().add(individualRecords);
        groupRecordsBox.getChildren().add(groupRecords);


        Invite.setPrefSize(600,400);
        logOut.setPrefSize(600, 400);
        individualRecords.setPrefSize(600, 400);
        groupRecords.setPrefSize(600, 400);

        individualRecords.setStyle("-fx-font: 22 Serif; -fx-base: #4A6572; ");
        groupRecords.setStyle("-fx-font: 22 Serif; -fx-base: #F9AA33 ; ");
        Invite.setStyle("-fx-font: 22 Serif; -fx-base: #F9AA33 ; ");
        logOut.setStyle("-fx-font: 22 Serif; -fx-base: #4A6572; ");



        upperHBox.getChildren().addAll(individualRecordBox,groupRecordsBox);
        lowerHBox.getChildren().addAll(Invite, logOut);


        /*menuLayout.add(logOut, 20, 0);
        menuLayout.add(individualRecords, 0, 0);
        menuLayout.add(groupRecords, 1, 0);
        menuLayout.add(Invite, 0, 2);*/

        Invite.setOnAction(e -> {
            InvitePage.display();
        });

        individualRecords.setOnAction(e -> {
            //menuWindow.close();
            IndividualRecordPage.display();
        });

        groupRecords.setOnAction(e -> {

            GroupRecordPage.display();
        });

        Image img = null;
        try {
            img = new Image(new FileInputStream("Background.jpg"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BackgroundImage myBI= new BackgroundImage(img,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        menuLayout.setBackground(new Background(myBI));

        Scene menuScene = new Scene(menuLayout, 1200, 800);
        window.Window.setScene(menuScene);
        window.Window.show();
    }
}
