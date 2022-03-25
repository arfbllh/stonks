package Stonks;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegisterPage
{
    public static void display()
    {
        Stage registerWindow = new Stage();
        registerWindow.setTitle("Stonks");

        Button login = new Button("Login");
        Button register = new Button("Register");

        ChoiceBox<String> accountType = new ChoiceBox<>();
        accountType.setValue("Individual");

        accountType.getItems().add("Individual");
        accountType.getItems().add("Office");

        GridPane registerLayout = new GridPane();
        registerLayout.setAlignment(Pos.CENTER);
        registerLayout.setHgap(10);
        registerLayout.setVgap(10);
        registerLayout.setPadding(new Insets(10, 10, 10, 10));

        Label enterName = new Label("Enter Your Name");
        Label gobacktoLogin = new Label("Have an account?");
        Label selectaccounttype = new Label("Select the account type:");

        TextField userName = new TextField();

        Label enterPass = new Label("Enter Your Passcode");
        PasswordField passCode = new PasswordField();

        login.setOnAction(e -> {
            registerWindow.close();
            LoginPage.display();
        });

        register.setOnAction(e -> {
            UserData.addUser(userName.getText(), passCode.getText(), accountType.getValue());
            registerWindow.close();
            LoginPage.display();
        });

        registerLayout.add(gobacktoLogin, 0, 0);
        registerLayout.add(login, 1, 0);


        registerLayout.add(selectaccounttype, 0, 2);
        registerLayout.add(accountType, 0, 3);
        registerLayout.add(enterName, 0, 5);
        registerLayout.add(userName, 0, 6);
        registerLayout.add(enterPass, 0, 8);
        registerLayout.add(passCode, 0, 9);
        registerLayout.add(register, 0, 10);

        BorderPane finalRegisterLayout = new BorderPane();


        Scene registerScene = new Scene(registerLayout, 1200, 800);
        registerWindow.setScene(registerScene);
        registerWindow.show();
    }
}
