package Stonks.Pages;

import Stonks.Records.RecordData;
import Stonks.Users.UserData;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
//import sun.awt.X11.InfoWindow;

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

        TextField addName = new TextField();

        Button addUser = new Button("+");

        HBox takename = new HBox();
        takename.setSpacing(20);
        takename.setPadding(new Insets(10,10,10,10));

        Button back = new Button("Back");
        Button confirm = new Button("Confirm");

        back.setPrefSize(250,100);
        confirm.setPrefSize(250,100);

        confirm.setStyle("-fx-font: 15 Serif; -fx-base: #32CD32; ");
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
            names[i].setText(addedUsers.get(i));
            addedNames.getChildren().add(names[i]);
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

        addUserWindow.initModality(Modality.APPLICATION_MODAL);
        Scene addUserScene = new Scene(addUserLayout, 400, 400);
        addUserWindow.setScene(addUserScene);
        addUserWindow.show();
    }
}
