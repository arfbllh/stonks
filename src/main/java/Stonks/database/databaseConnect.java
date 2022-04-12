package Stonks.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class databaseConnect {
    public static Connection connector() {
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:src/main/java/Stonks/database");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
