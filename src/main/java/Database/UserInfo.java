package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import static Database.DB.*;
import Stonks.*;
import Stonks.Users.Invite;
import Stonks.Users.User;

public class UserInfo {
    public static int authUser(String userName, String pass)  {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "select * from UserInfo where name = ? and password = ?";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, userName);
            statement.setString(2, pass);
            resultSet = statement.executeQuery();
            if(resultSet.next())
                return resultSet.getInt("id");
            else
                return 0;
        }
        catch (SQLException e){
            return 0;
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


    public static Vector<User> start(){
        Vector<User> tmp = new Vector<>();
        if(!isTableExits("UserInfo")) return tmp;
        PreparedStatement statement = null;
        String command = "select * from UserInfo";
        try{
            statement = connection.prepareStatement(command);
            ResultSet res = statement.executeQuery();
            while(res.next()){
                User u = new User(res.getString("name"), res.getString("pass"), res.getInt("type") == 1, res.getInt("id"));
                tmp.add(u);
            }
            statement.close();
            res.close();
            for(User u : tmp){
                if(!isTableExits(u.getUserName() + "_invite")) continue;
                command = "select *from " + u.getUserName() + "_invite";
                PreparedStatement st = connection.prepareStatement(command);
                ResultSet res1 = st.executeQuery();
                Vector<Invite> invites = new Vector<>();
                while(res1.next()){
                    Invite in = new Invite(res1.getInt("sender"), res1.getInt("receiver"), res1.getInt("record"));
                    invites.add(in);
                }
                u.invites = invites;
                deleteTable(u.getUserName() + "_invite");

            }
            statement.close();
            res.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        deleteTable("UserInfo");
        return  tmp;
    }

    public static void close(Vector<User> vec){
        PreparedStatement statement = null;
        String command = "CREATE TABLE UserInfo(id integer not null primary key autoincrement, name varchar, pass varchar, type int)";
        try {
            statement = connection.prepareStatement(command);
            statement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        for(User u : vec){
            command = "insert into UserInfo(name, pass, type) VALUES(?, ?, ?);";
            try{
                statement = connection.prepareStatement(command);
                statement.setString(1, u.getUserName());
                statement.setString(2, u.getPassCode());
                statement.setString(3, String.valueOf(u.isIndividual()));
                statement.executeUpdate();
                statement.close();
                Vector<Invite> tmp = u.getInvites();
                if(!isTableExits(u.getUserName() + "_invite")){
                    command = "CREATE TABLE " + u.getUserName() + "_invite(id integer not null primary key autoincrement, sender int, receiver int, record int)";
                    statement = connection.prepareStatement(command);
                    statement.executeUpdate();
                }
                for(Invite i : tmp){
                    command = "insert into" + u.getUserName() + "_invite(sender, receiver, record) VALUES(?, ?, ?);";
                    statement = connection.prepareStatement(command);
                    statement.setString(1, String.valueOf(i.getSenderId()));
                    statement.setString(2, String.valueOf(i.getReceiverId()));
                    statement.setString(3, String.valueOf(i.getRecordId()));
                    statement.executeUpdate();
                    statement.close();
                }
            }
            catch (SQLException e){
                e.printStackTrace();
            }

        }
    }
}