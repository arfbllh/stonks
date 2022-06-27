package Database;

import Stonks.Users.UserData;

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
        UserData.retrieveUser();
    }
    public static void close(){
        UserData.saveUsers();
    }



}
