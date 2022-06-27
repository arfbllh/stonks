package Stonks;

import Database.*;
import Stonks.Pages.LoginPage;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.SQLException;

import Database.DB;

public class HelloApplication extends Application
{
    public static void main(String[] args) throws SQLException {

        DB.Connector();
        launch(args);
        DB.close();
    }
    //Program start
    public void start(Stage primaryStage) throws Exception
    {
        LoginPage.display();
    }


}