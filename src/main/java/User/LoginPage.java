package User;

import Stonks.MenuPage;
import Stonks.UserData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import Database.*;
public class LoginPage
{
    public static void display()
    {
        Stage loginWindow;
        TextField userName;
        PasswordField passCode;

        loginWindow = new Stage();
        loginWindow.setTitle("Stonks");

        Button login = new Button("Login");
        Button register = new Button("Register");

        Label user = new Label("Username :");
        Label pass = new Label("Passcode :");
        Label text = new Label("Not Signed up yet? ");
        Label selectaccounttype = new Label("Select Account type");

        userName = new TextField();
        passCode = new PasswordField();

        login.setOnAction(e -> {
            String tempUser = userName.getText();
            String tempPass = passCode.getText();
            //used database function here
            int userId = UserInfo.authUser(tempUser, tempPass);
            if(userId > 0)
            {
                loginWindow.close();
                UserData.setCurrentUser(userId);
                MenuPage.display();
            }

            else
            {
                System.out.println("No");
            }});

        register.setOnAction(e -> {
            loginWindow.close();
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


        Scene loginScene = new Scene(loginLayout, 1200, 800);
        loginWindow.setScene(loginScene);
        loginWindow.show();
    }
}
