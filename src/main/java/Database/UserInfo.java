package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import static Database.DB.*;

import Stonks.Users.Invite;
import Stonks.Users.User;

public class UserInfo {

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


    public static Vector<User> init(){
        Vector<User> tmp = new Vector<>();
        if(!isTableExits("UserInfo")) return tmp;
        PreparedStatement statement = null;
        String command = "select * from UserInfo";
        try{
            statement = connection.prepareStatement(command);
            ResultSet res = statement.executeQuery();
            while(res.next()){
                User u = new User(res.getString("name"), res.getString("pass"), true, res.getInt("id"));
                tmp.add(u);
            }
            statement.close();
            res.close();
            for(User u : tmp){
                if(!isTableExits(u.getUserName() + "_invite")) continue;
                command = "select * from " + u.getUserName() + "_invite";
                PreparedStatement st = connection.prepareStatement(command);
                ResultSet res1 = st.executeQuery();
                Vector<Invite> invites = new Vector<>();
                while(res1.next()){
                    Invite in = new Invite(res1.getInt("sender"), res1.getInt("receiver"), res1.getInt("record"));
                    invites.add(in);
                   // System.out.println("in invite = " + in.getSenderId() + " " + in.getReceiverId());
                }
                u.invites = invites;
                deleteTable(u.getUserName() + "_invite");

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        deleteTable("UserInfo");
        return  tmp;
    }

    public static void close(Vector<User> vec){
        PreparedStatement statement = null;
        String command = "CREATE TABLE UserInfo(no integer not null primary key autoincrement, id int, name varchar, pass varchar)";
        try {
            statement = connection.prepareStatement(command);
            statement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        for(User u : vec){
            command = "insert into UserInfo(id, name, pass) VALUES(?, ?, ?);";
            try{
                statement = connection.prepareStatement(command);
                statement.setString(1, String.valueOf(u.getId()));
                statement.setString(2, u.getUserName());
                statement.setString(3, u.getPassCode());
                statement.executeUpdate();
                statement.close();
                Vector<Invite> tmp = u.getInvites();
                if(!isTableExits(u.getUserName() + "_invite")){
                    command = "CREATE TABLE " + u.getUserName() + "_invite(id integer primary key autoincrement, sender int, receiver int, record int)";
                    statement = connection.prepareStatement(command);
                    statement.executeUpdate();
                }
                for(Invite i : tmp){
                    command = "insert into " + u.getUserName() + "_invite(sender, receiver, record) VALUES(?, ?, ?);";
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