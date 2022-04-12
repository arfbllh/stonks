package Stonks;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import Database.DB;

public class HelloApplication extends Application
{
    public static void main(String[] args) throws SQLException {

        Stonk();
        //UserData.showAllUsers();

        launch(args);
        Bonk();
    }

    public void start(Stage primaryStage) throws Exception
    {
        LoginPage.display();



    }
    static public void Stonk()  {
        EntryData.init();
        RecordData.init();
        UserData.init();
        CountData.init();
        GroupRecord.init();

    }
    static void Bonk() {
        UserData.close();
        EntryData.close();
        RecordData.close();
        CountData.close();
        GroupRecord.close();

    }
}