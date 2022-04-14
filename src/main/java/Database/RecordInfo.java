package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RecordInfo {
    static Connection connection = DB.returnConnection();


    static void createRecord(String name){
        String command = "CREATE TABLE tab" + name + "(id integer not null primary key autoincrement, name varchar,  amount BIGINT, tag varchar, type int)";
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
    public static void deleteRecord(int recordId){
        String command = "DROP TABLE tab" + recordId;
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

}
