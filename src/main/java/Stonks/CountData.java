package Stonks;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static Database.DB.*;

public class CountData {
    public static int userCount;
    public static int recordCount;
    public static int entryCount;
    public static void close()  {
        try{
            String command = "Create table count(user int, record int, entry int)";

            PreparedStatement st = connection.prepareStatement(command);

            st.executeUpdate();
            st.close();

            st = connection.prepareStatement("insert into count(user, record, entry) values(?, ?, ?)");
            st.setString(1, String.valueOf(userCount));
            st.setString(2, String.valueOf(recordCount));
            st.setString(3, String.valueOf(entryCount));
            st.executeUpdate();


        }  catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static  void init() {
        try{
            if(!isTableExits("count")){
                userCount = 0;
                recordCount = 0;
                entryCount = 0;
            }
            else{
                String command = "select * from count";
                PreparedStatement st = connection.prepareStatement(command);
                ResultSet res = st.executeQuery();
                res.next();
                userCount = res.getInt(1);
                recordCount = res.getInt(2);
                entryCount = res.getInt(3);
                st.close();
                res.close();
                deleteTable("count");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
