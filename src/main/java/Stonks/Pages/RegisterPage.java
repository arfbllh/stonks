package Stonks.Pages;

import Stonks.Users.UserData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class RegisterPage
{
    public static void display()
    {
        //Stage registerWindow = new Stage();
        window.Window.setTitle("Stonks");

        Button login = new Button("Login");
        Button register = new Button("Register");

        ChoiceBox<String> accountType = new ChoiceBox<>();
        //accountType.setValue("Individual");

        //accountType.getItems().add("Individual");
        //accountType.getItems().add("Office");

        GridPane registerLayout = new GridPane();
        registerLayout.setAlignment(Pos.CENTER);
        registerLayout.setHgap(10);
        registerLayout.setVgap(10);
        registerLayout.setPadding(new Insets(10, 10, 10, 10));

        Label enterName = new Label("Enter Your Name");
        enterName.setTextFill(Color.rgb(255,255,0));
        Label gobacktoLogin = new Label("Have an account?");
        gobacktoLogin.setTextFill(Color.rgb(255,255,0));
        //Label selectaccounttype = new Label("Select the account type:");

        TextField userName = new TextField();

        Label enterPass = new Label("Enter Your Passcode");
        enterPass.setTextFill(Color.rgb(255,255,0));
        PasswordField passCode = new PasswordField();

        login.setOnAction(e -> {
            //registerWindow.close();
            LoginPage.display();
        });

        register.setOnAction(e -> {
            //used database function here
            int check= UserData.addUser(userName.getText(), passCode.getText(), accountType.getValue());
            if(check==-1){
                WarningAlert warning = new WarningAlert("Invalid Credentials", "Username or passcode can not be empty!");
                warning.display();
            }
            else if(check==0){
                WarningAlert warning = new WarningAlert("Invalid Username","Same username already exists");
                warning.display();
            }
            //registerWindow.close();
            else LoginPage.display();
        });

        registerLayout.add(gobacktoLogin, 0, 3);
        registerLayout.add(login, 1, 3);


       // registerLayout.add(selectaccounttype, 0, 2);
       // registerLayout.add(accountType, 0, 3);
        registerLayout.add(enterName, 0, 5);
        registerLayout.add(userName, 0, 6);
        registerLayout.add(enterPass, 0, 8);
        registerLayout.add(passCode, 0, 9);
        registerLayout.add(register, 0, 11);

        BorderPane finalRegisterLayout = new BorderPane();

        Image img2 = null;
        try {
            img2 = new Image(new FileInputStream("Background.jpg"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BackgroundImage myBI2= new BackgroundImage(img2,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        registerLayout.setBackground(new Background(myBI2));

        Scene registerScene = new Scene(registerLayout, 1200, 800);
        window.Window.setScene(registerScene);
        window.Window.show();
    }
}
