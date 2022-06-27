package Stonks.Database;

import Stonks.Entries.Entry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;

import static Stonks.Database.DB.*;
import static Stonks.Database.DB.deleteTable;

public class EntryManage {
    static Connection connection = DB.connection;

    public static Vector<Entry> init(){
        Vector<Entry> entries = new Vector<>();
        if(!isTableExits("entries")) return entries;
        PreparedStatement statement = null;
        String command = "select * from entries";
        try{
            statement = connection.prepareStatement(command);
            ResultSet res = statement.executeQuery();
            while(res.next()){
                Vector<String> v = new Vector<>();
                String s = res.getString("tags");
                v.addAll(Arrays.asList(s.split(" ")));
                Entry e = new Entry(res.getString("name"), v, res.getInt("amount"), res.getInt("type") == 1, res.getInt("record"), res.getInt("id"));
                entries.add(e);
            }

            statement.close();
            res.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        deleteTable("entries");
        return entries;
    }

    public static void exit(Vector<Entry> vec){
        if(!isTableExits("entries")){
            String command = "create table entries(id integer not null primary key, name varchar, amount BIGINT, type int, record int, tags varchar)";
            try{
                PreparedStatement st = connection.prepareStatement(command);
                st.executeUpdate();
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        for(Entry e : vec){
            StringBuilder str = new StringBuilder();

            for(String s : e.tags){
                str.append(s + " ");
            }
            String command = "insert into entries(id, name, amount, type, record, tags) values(?, ?, ?, ?, ?, ?)";
            try {
                PreparedStatement st = connection.prepareStatement(command);
                st.setString(1, String.valueOf(e.getId()));
                st.setString(2, e.getName());
                st.setString(3, String.valueOf(e.getAmount()));
                st.setString(4, String.valueOf(e.getCashInStatus()));
                st.setString(5, String.valueOf(e.getRecordId()));
                st.setString(6, String.valueOf(str));
                st.executeUpdate();
                st.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }



}
