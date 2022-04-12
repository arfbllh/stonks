package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Database.DB.*;

public class UserInfo {
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
    public static int addUser(String name, String password) {
        if(name == "")
            return -1;
        else if(password == "")
            return -2;

        String command = "INSERT INTO UserInfo(name,password)" +  "VALUES(?,?)";
        PreparedStatement statement = null;
        try {
            if(userExists(name))
                return -3;
            Connection connection = DB.returnConnection();

            statement = connection.prepareStatement(command);
            statement.setString(1, name);
            statement.setString(2, password);
            int affectedRows = statement.executeUpdate();
            if(affectedRows > 0){
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -4;
        }
        return 0;

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
