package com.example.stonks1;

import javafx.application.Application;
import javafx.stage.Stage;

public class HelloApplication extends Application
{
    public static void main(String[] args)
    {
        UserData.initdatabase();
        launch(args);
        UserData.closedatabase();

    }

    public void start(Stage primaryStage) throws Exception
    {
        LoginPage.display();
    }
}