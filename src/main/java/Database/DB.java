package Database;

import java.sql.*;
public class DB {
    public static Connection connection;
    public static void Connector(){
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/stonks.db");
            if(connection == null){
                System.out.println("Database connection was not success");
            }
            else{
                System.out.println("Database connection success");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static  boolean isDBConnected() throws SQLException {
        return !connection.isClosed();
    }
    static Connection returnConnection(){
        return connection;
    }



}
