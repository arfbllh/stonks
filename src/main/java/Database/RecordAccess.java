package Database;

import javafx.util.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class RecordAccess {
    public static void createAccessTable(String name){
        System.out.println(name);
        String command = "create table " + name + "(recordId int, type int)";
        Connection connection = DB.connection;
        PreparedStatement statement;
        try{
            statement = connection.prepareStatement(command);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void addRecord(String userName, int RecordId, int memberType){
        String command = "insert into  " + userName + "(recordId, type) values(?, ?)";
        Connection connection = DB.connection;
        PreparedStatement statement;
        try{
            statement = connection.prepareStatement(command);
            statement.setString(1, String.valueOf(RecordId));
            statement.setString(2, String.valueOf(memberType));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Vector<Pair<Integer, Integer>> getRecords(String userName){
        Vector<Pair<Integer, Integer>> v = new Vector<>();
        String command = "select * from " + userName;
        Connection connection = DB.connection;
        PreparedStatement statement;
        try{
            statement = connection.prepareStatement(command);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                v.add(new Pair<Integer, Integer>(resultSet.getInt("RecordId"), resultSet.getInt("type")));
            }
            return v;
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

}
