package Database;

import Stonks.Records.*;
import Stonks.Records.Record;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import static Database.DB.deleteTable;
import static Database.DB.isTableExits;


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
        Vector<Integer> vec = new Vector<>();
        if(!isTableExits("RecordType")) return vec;
        try{
            String command = "select * from RecordType ";
            PreparedStatement st = connection.prepareStatement(command);
            ResultSet res = st.executeQuery();
            while(res.next()){
                vec.add(res.getInt(2));
            }
            System.out.println(vec.toString());
            st.close();
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        deleteTable("RecordType");
        return vec;
    }

    public static Vector<Record> restore2(Vector<Integer> recordType) {
        Vector<Record> vec = new Vector<>();
        Vector<Record> vec2 = new Vector<>();

        if(!isTableExits("Record")) return vec;
        try{
            String command = "select * from Record";
            PreparedStatement st = connection.prepareStatement(command);
            ResultSet res = st.executeQuery();
            for(int i : recordType){
                if(!res.next()) break;
                Record rec;
                if(i == 0){
                    IndRecord in = new IndRecord(res.getString("name"), res.getInt("userid"), res.getInt("record"));
                    rec = in;
                }
                else{
                    GroupRecord gr = new GroupRecord(res.getString("name"), res.getInt("userid"), res.getInt("record"));
                    rec = gr;
                }
                vec.add(rec);
            }

            st.close();
            res.close();
            int i = 0;
            for(Record rec : vec){
                int u = recordType.get(i++);
                if(u == 0) {
                    vec2.add(rec);
                    continue;
                }
                GroupRecord gr = (GroupRecord) rec;
                command = "select * from group" + rec.getId();
                st = connection.prepareStatement(command);
                res = st.executeQuery();
                Vector<RecordMember> tmp = new Vector<>();
                while(res.next()){
                    int k = res.getInt(2);
                    System.out.println(k);
                    //if(res.getInt(1) == rec.getRecordMembers().get(0)) continue;
                    if(k == 0)
                        tmp.add(new AlphaMember(res.getInt(1)));
                    if(k == 1)
                        tmp.add(new SigmaMember(res.getInt(1)));
                    if(k == 2)
                        tmp.add(new OmegaMember(res.getInt(1)));
                }
                gr.users = tmp;
                st.close();
                res.close();
                System.out.println("group" + gr.getId());
                deleteTable("group" + rec.getId());
                System.out.println(gr.users.toString());
                vec2.add(gr);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        deleteTable("Record");

        return vec2;
    }

    public static void save1(Vector<Integer> recordType) {
        if(!isTableExits("RecordType")){
            try{
                String command = "create table RecordType(id integer not null primary key autoincrement, type int)";
                PreparedStatement st = connection.prepareStatement(command);
                st.executeUpdate();
                st.close();
            } catch (SQLException e) {
               e.printStackTrace();
            }
        }
        for(int u : recordType){
            try{
                String command = "insert into RecordType(type) values(?)";
                PreparedStatement st = connection.prepareStatement(command);
                st.setString(1, String.valueOf(u));
                st.executeUpdate();
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void save2(Vector<Record> records, Vector<Integer> vec) {
        if(!isTableExits("Record")){
            try{
                String command = "create table Record(id integer not null primary key autoincrement, name varchar, userid int, record int)";
                PreparedStatement st = connection.prepareStatement(command);
                st.executeUpdate();
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        int i = 0;
        for(Record rec : records){
            int u = vec.get(i++);
            try{
                String command = "insert into Record(name, userid, record) values(?, ?, ?)";
                PreparedStatement st = connection.prepareStatement(command);
                st.setString(1, rec.getName());
                st.setString(2, String.valueOf(rec.getRecordMembers().get(0)));
                st.setString(3, String.valueOf(rec.getId()));
                st.executeUpdate();
                st.close();

                if(u == 1){
                    GroupRecord gr = (GroupRecord) rec;
                    int id = rec.getId();
                    if(!isTableExits("group" + rec.getId())){
                        command = "create table group" + rec.getId()+ "(id int, type int)";
                        st = connection.prepareStatement(command);
                        st.executeUpdate();
                        st.close();
                    }
                    for(RecordMember rm : gr.users){
                        OmegaMember om = (OmegaMember) rm;
                        int type = om.getMemberType();
                        command = "insert into group" + rec.getId() + "(id, type) values(?, ?)";
                        st = connection.prepareStatement(command);
                        st.setString(1, String.valueOf(om.userId));
                        st.setString(2, String.valueOf(type));
                        st.executeUpdate();
                        st.close();

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
