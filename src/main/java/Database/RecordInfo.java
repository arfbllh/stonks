package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RecordInfo {
    static Connection connection = DB.returnConnection();


    static void createRecord(String name){
        String command = "CREATE TABLE tab" + name + "(id integer not null primary key autoincrement, Name varchar,  amount BIGINT, tag varchar, type int)";
        System.out.println(command);
        PreparedStatement statement = null;
        try{
            connection = DB.returnConnection();
            statement = (PreparedStatement) connection.prepareStatement(command);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);

        }
    }
    public static void deleteRecord(String name){
        String command = "DROP TABLE tab" + name;
        PreparedStatement statement = null;
        try{
            connection = DB.returnConnection();
            statement = (PreparedStatement) connection.prepareStatement(command);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);

        }
    }
    static void InsertEntry(String RecordName, String entryName, Long amount, String tag, int type){
        String command = "insert into "+ RecordName + " (name, amount, tag, type)" + "values(?, ?, ?, ?)";
        PreparedStatement statement = null;
        try {
            statement = (PreparedStatement) connection.prepareStatement(command);
            statement.setString(1, entryName);
            statement.setString(2, String.valueOf(amount));
            statement.setString(3, RecordName);
            statement.setString(4, String.valueOf(type));
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
