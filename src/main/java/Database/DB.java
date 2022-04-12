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
    public static boolean authUser(String userName, String pass) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "select * from UserInfo where name = ? and password = ?";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, userName);
            statement.setString(2, pass);
            resultSet = statement.executeQuery();
            return resultSet.next();
        }
        catch (SQLException e){
            return false;
        }
        finally {
            statement.close();
            resultSet.close();
        }
    }
    static int addUser(String name, String password) throws SQLException {
        if(name == "")
            return -1;
        else if(password == "")
            return -2;
        else if(userExists(name))
            return -3;

        return 1;
    }

    static boolean userExists(String userName) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "select * from UserInfo where name = ?";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, userName);
            resultSet = statement.executeQuery();
            return resultSet.next();
        }
        catch (SQLException e){
            return false;
        }
        finally {
            statement.close();
            resultSet.close();
        }
    }


}
