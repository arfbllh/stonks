package Stonks.Pages;

import Stonks.Users.UserData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LoginPage
{
    public static void display()
    {
        //Stage loginWindow;
        TextField userName;
        PasswordField passCode;

        window.Window.setTitle("Stonks");

        Button login = new Button("Login");
        Button register = new Button("Register");

        Label user = new Label("Username :");
        user.setTextFill(Color.rgb(255,255,0));
        Label pass = new Label("Passcode :");
        pass.setTextFill(Color.rgb(255,255,0));
        Label text = new Label("Not Signed up yet? ");
        text.setTextFill(Color.rgb(255,255,0));
        Label selectaccounttype = new Label("Select Account type");
        selectaccounttype.setTextFill(Color.rgb(255,255,0));

        userName = new TextField();
        passCode = new PasswordField();

        Stage finalLoginWindow = window.Window;
        login.setOnAction(e -> {
            String tempUser = userName.getText();
            String tempPass = passCode.getText();
            //used database function here
            if(UserData.checkUser(tempUser, tempPass) == true)
            {
                //loginWindow.close();
                int userId= UserData.getUserId(tempUser,tempPass);
                UserData.setCurrentUser(userId);
                MenuPage.display();
            }

            else
            {
                WarningAlert warning = new WarningAlert("Invalid Credentials", "The username or passcode doesn't match!");
                warning.display();
                System.out.println("No");
            }});


        register.setOnAction(e -> {
            //loginWindow.close();
            RegisterPage.display();
        });

        GridPane loginLayout = new GridPane();
        loginLayout.setAlignment(Pos.CENTER);
        loginLayout.setHgap(10);
        loginLayout.setVgap(10);
        loginLayout.setPadding(new Insets(10, 10, 10, 10));

        loginLayout.add(user, 0, 1);
        loginLayout.add(userName, 1, 1);

        loginLayout.add(pass, 0, 2);
        passCode.setPromptText("Passcode");
        loginLayout.add(passCode, 1, 2);
        loginLayout.add(login, 1, 3);
        loginLayout.add(text,1, 8);
        loginLayout.add(register, 2, 8);

        Image img2 = null;

        img2 = new Image("Background.jpg");

        BackgroundImage myBI2= new BackgroundImage(img2,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        loginLayout.setBackground(new Background(myBI2));


        Scene loginScene = new Scene(loginLayout, 1200, 800);
        window.Window.setScene(loginScene);
        window.Window.show();
    }
}
