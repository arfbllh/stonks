package Stonks.Users;

import Stonks.CountData;
import Stonks.Records.RecordData;

import java.io.Serializable;
import java.util.Vector;

public class User implements Serializable {
    private String userName,passCode;
    private boolean isPersonal;
    public Vector<Invite> invites;
    private int userId;

    public User(String userName, String passCode, boolean isIndividual, int userId){
        this.userName= userName;
        this.passCode= passCode;
        this.isPersonal= isIndividual;
        invites= new Vector<>();
        this.userId = userId;
    }

    public User(String userName, String passCode) {
        this.userName = userName;
        this.passCode = passCode;
        this.userId = CountData.userCount++;
    }

    public int getId(){
        return userId;
    }

    public String getUserName(){
        return userName;
    }
    public String getPassCode(){ return passCode;}

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
        Vector<Invite> res= new Vector<>();

        for(int i=0;i<invites.size();i++){
            int recordId= invites.get(i).getRecordId();
            if(RecordData.getRecordIdIndex(recordId)==-1)continue;
            res.add(invites.get(i));
        }
        invites= res;
        return invites;
    }
    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", passCode='" + passCode + '\'' +
                ", isPersonal=" + isPersonal +
                ", invites=" + invites +
                ", userId=" + userId +
                '}';
    }
}