package Database;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EntryManage {
    static Connection connection = DB.connection;
    static void InsertEntry(int recordId, String entryName, Long amount, String tag, int type){
        String command = "insert into tab"+ recordId + " (name, amount, tag, type)" + "values(?, ?, ?, ?)";
        PreparedStatement statement;
        try {
            statement = (PreparedStatement) connection.prepareStatement(command);
            statement.setString(1, entryName);
            statement.setString(2, String.valueOf(amount));
            statement.setString(3, tag);
            statement.setString(4, String.valueOf(type));
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    static void deleteEntry(int id, int recordId){
        String command = "delete form tab"+ Integer.toString(recordId) + " where id = ?";
        PreparedStatement statement = null;
        try {
            statement = (PreparedStatement) connection.prepareStatement(command);
            statement.setString(1, String.valueOf(id));
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void editEntry(int id, int recordId, String entryName, Long amount, String tag, int type){
        String command = "insert form tab"+ recordId + " where id = ?";
        PreparedStatement statement = null;
        try {
            statement = (PreparedStatement) connection.prepareStatement(command);
            statement.setString(1, String.valueOf(id));
            statement.executeUpdate();
            ResultSet resultSet = statement.getResultSet();
            resultSet.updateString("Name", entryName);
            resultSet.updateBigDecimal("amount", BigDecimal.valueOf(amount));
            resultSet.updateString("tag", tag);
            resultSet.updateInt("name", type);
            resultSet.updateRow();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
