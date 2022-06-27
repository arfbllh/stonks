package Stonks.Users;

import Stonks.CountData;


import java.io.*;
import java.util.Vector;
import Stonks.Database.*;

public class UserData implements Serializable
{
    private static Vector<User> users= new Vector<>();
    private static int currentUser;
    static private boolean saved;

    public static int addUser(String username, String passcode, String account)
    {
        if(username.isEmpty()||passcode.isEmpty())return -1;
        boolean isIndividual= true;
        //if(account.equals("Individual") == false)isIndividual= false;
        for(int i=0;i<users.size();i++){
            if(users.get(i).getUserName().equals(username))return 0;
        }
        users.add(new User(username,passcode,isIndividual, CountData.userCount++));
        return 1;
    }

    static public int getCurrentUser()
    {
        return currentUser;
    }

    private static int getUserIdIndex(int id){
        int res=-1;

        for(int i=0;i<users.size();i++){
            if(users.get(i).getId()==id)return i;
        }
        return res;
    }

    static public String getUsername(int id)
    {
        String result = null;
        int index= getUserIdIndex(id);
        result= users.get(index).getUserName();

        return result;
    }

    static public boolean checkIndividual(int id)
    {
        boolean res= true;
        int index= getUserIdIndex(id);
        res= users.get(index).isIndividual();

        return res;
    }

    static public void setCurrentUser(int id)
    {
        currentUser= id;
    }

    static public int getUserId(String username,String passcode){
        for(int i = 0; i < users.size(); i++)
        {
            if(users.get(i).getUserName().equals(username) && users.get(i).authenticate(passcode))return users.get(i).getId();
        }

        return -1;
    }

    public static int getUserIdByName(String username){

        for(int i=0;i<users.size();i++){
            if(users.get(i).getUserName().equals(username))return users.get(i).getId();
        }
        return -1;
    }

    public static boolean checkUser(String username, String passcode)
    {
        int check= getUserId(username,passcode);
        if(check==-1)return false;

        return true;
    }

    public static void addUserInvite(int senderId,int receiverId,int recordId){
        int index1= getUserIdIndex(senderId), index2= getUserIdIndex(receiverId);
        users.get(index2).addInvite(senderId,recordId);
    }

    public static Vector<Invite> getUserInvites(int userId){
        int index= getUserIdIndex(userId);
        return users.get(index).getInvites();
    }

//    static void init(){
//        DataStore<User> obj = new DataStore<User>();
//        users = obj.init("user");
//
//
//    }
//    static void close(){
//        DataStore<User> obj = new DataStore<User>();
//        ((DataStore<User>) obj).close("user", users);
//    }

   static void showAllUsers(){
        System.out.println(users.size());
       for(User u : users) System.out.println(u.toString());
    }

    public static void init()
    {
        users = UserInfo.init();
    }
    public static void saveUsers(){
        UserInfo.close(users);
    }
}