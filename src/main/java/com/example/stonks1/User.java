package com.example.stonks1;

import java.io.Serializable;
import java.util.Vector;

public class User implements Serializable {
    private String userName,passCode;
    private boolean isPersonal;
    private Vector<Invite> invites;

    User(String userName,String passCode,boolean isIndividual){
        this.userName= userName;
        this.passCode= passCode;
        this.isPersonal= isIndividual;
        invites= new Vector<>();
    }

    public User(String userName, String passCode) {
        this.userName = userName;
        this.passCode = passCode;
    }

    public String getUserName(){
        return userName;
    }

    public boolean authenticate(String pass){
        return passCode.equals(pass);
    }

    public boolean isIndividual(){
        return isPersonal;
    }

    public void addInvite(int senderId,int recordId){
        invites.add(new Invite(senderId,recordId));
    }

    public Vector<Invite> getInvites(){
        return invites;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", passCode='" + passCode + '\'' +
                ", isPersonal=" + isPersonal +
                ", invites=" + invites +
                '}';
    }
}
