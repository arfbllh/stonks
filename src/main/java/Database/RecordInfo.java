package Database;

import Stonks.Records.Record;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;
import Database.*;

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

    public static Vector<Integer> restore1() {
        if(!isTab)
    }

    public static Vector<Record> restore2(Vector<Integer> recordType) {
    }

    public static void save1(Vector<Integer> recordType) {
    }

    public static void save2(Vector<Record> records) {
    }
}
