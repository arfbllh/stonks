package Stonks;

import Database.*;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Vector;

import Database.DB;
import User.*;
import javafx.util.Pair;

public class HelloApplication extends Application
{
    public static void main(String[] args) throws SQLException {

        DB.Connector();
        launch(args);


    }
    //Program start
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