package com.example.stonks1;

import java.io.*;
import java.util.Vector;

public class UserData implements Serializable
{
    private static Vector<User> users= new Vector<>();
    private static int currentUser;
    static private boolean saved;

    static void addUser(String username, String passcode, String account)
    {
        boolean isIndividual= true;
        if(account.equals("Individual") == false)isIndividual= false;
        users.add(new User(username,passcode,isIndividual));
    }

    static public int getCurrentUser()
    {
        return currentUser;
    }

    static public String getUsername(int id)
    {
        return users.get(id).getUserName();
    }

    static public boolean checkIndividual(int id)
    {

        return users.get(id).isIndividual();
    }

    static public void setCurrentUser(int id)
    {
        currentUser= id;
    }

    static int getUserId(String username,String passcode){
        for(int i = 0; i < users.size(); i++)
        {
            if(users.get(i).getUserName().equals(username) && users.get(i).authenticate(passcode))return i;
        }

        return -1;
    }

    static int getUserIdByName(String username){

        for(int i=0;i<users.size();i++){
            if(users.get(i).getUserName().equals(username))return i;
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
        users.get(receiverId).addInvite(senderId,recordId);
    }

    public static Vector<Invite> getUserInvites(int usedId){
        return users.get(usedId).getInvites();
    }

    static void initdatabase(){
        try{
            FileInputStream fos = new FileInputStream("user");
            ObjectInputStream oos = new ObjectInputStream(fos);
            users = (Vector<User>) oos.readObject();
            fos.close();
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    static void closedatabase(){
        try{
            FileOutputStream fos = new FileOutputStream("user");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(users);
            System.out.println("I'm Called in Disembody");
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
