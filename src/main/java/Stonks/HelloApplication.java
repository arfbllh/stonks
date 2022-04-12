package Stonks;

import Database.*;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import Database.DB;

public class HelloApplication extends Application
{
    public static void main(String[] args) throws SQLException {

        //launch(args);
        test();
    }

    public void start(Stage primaryStage) throws Exception
    {
        LoginPage.display();
    }

    static void test(){

        DB.Connector();
        try {
            int k = UserInfo.addUser("a", "b");
            System.out.println(k);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}