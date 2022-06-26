package Stonks.Users;

public class Invite {
    private int senderId,receiverId,recordId;

    Invite(int senderId,int recordId){

        this.senderId= senderId;
        this.recordId= recordId;
    }

    Invite(int senderId,int receiverId,int recordId){
        this.senderId= senderId;
        this.receiverId= receiverId;
        this.recordId= recordId;
    }

    public int getSenderId(){
        return senderId;
    }

    public int getReceiverId(){
        return receiverId;
    }

    public int getRecordId(){
        return recordId;
    }
}
