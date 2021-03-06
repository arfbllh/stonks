package Stonks.Database;

import Stonks.CountData;
import Stonks.Entries.EntryData;
import Stonks.Users.UserData;
import Stonks.Records.*;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
public class DB {
    public static Connection connection;
    public static void Connector(){
        try{
            Class.forName("org.sqlite.JDBC");
            String path = "jdbc:sqlite:" + new java.io.File(".").getCanonicalPath() + "/stonks.db";
            System.out.println(path);
            connection = DriverManager.getConnection(path);
            if(connection == null){
                System.out.println("Stonks.Database connection was not success");
            }
            else{
                System.out.println("Stonks.Database connection success");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        start();
    }

    static  boolean isDBConnected() throws SQLException {
        if(!connection.isClosed()) Connector();
        return !connection.isClosed();
    }
    static Connection returnConnection(){
        return connection;
    }

    public static boolean isTableExits(String name){
        String query = "SELECT name from sqlite_master where type = 'table' and name = '" + name + "'";
        PreparedStatement statement = null;
        try {
            statement = (PreparedStatement) connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            boolean yes = resultSet.next();
            resultSet.close();
            statement.close();
            return yes;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void deleteTable(String name){

        if(isTableExits(name)){
            try{
                String command = "DROP TABLE " + name;
                PreparedStatement statement = connection.prepareStatement(command);
                statement.executeUpdate();
                statement.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    static void start(){
        UserData.init();
        EntryData.init();
        CountData.init();
        RecordData.init();
    }
    public static void close(){
        UserData.saveUsers();
        EntryData.close();
        CountData.close();
        RecordData.close();

    }



}
