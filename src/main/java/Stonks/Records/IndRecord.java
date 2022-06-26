package Stonks.Records;

import Stonks.MemberConstants;

import java.util.Vector;

public class IndRecord extends Record {
    Vector<String> entries;
    String name;
    int recordId,creatorId;

    IndRecord(String name, int userId,int recordId){
        this.name= name;
        this.recordId= recordId;
        this.creatorId= userId;
        entries = new Vector<>();
    }

    public int getId(){
        return recordId;
    }

    public String getName(){
        return name;
    }

    public void addEntry(String entry){
        entries.add(entry);
        System.out.println("Added "+entry);
    }

    public void showEntries(){
        for(int i=0;i<entries.size();i++)System.out.println(entries.get(i));
    }

    public boolean hasReadAccess(int userId){
        if(creatorId==userId)return true;
        return false;
    }

    public boolean hasAddMemberAccess(int userId){
        if(creatorId==userId)return true;
        return false;
    }

    public boolean hasDeleteEntryAccess(int userId){
        if(creatorId==userId)return true;
        return false;
    }

    public boolean hasEditEntryAccess(int userId){
        if(creatorId==userId)return true;
        return false;
    }

    public boolean hasRemoveMemberAccess(int userId,int userType){
        if(creatorId==userId)return true;
        return false;
    }

    public boolean hasPromoteMemberAccess(int userId,int userType){
        if(creatorId==userId)return true;
        return false;
    }

    public boolean hasDemoteMemberAccess(int userId,int userType){
        if(creatorId==userId)return true;
        return false;
    }

    public boolean hasDeleteRecordAccess(int userId){
        if(creatorId==userId)return true;
        return false;
    }

    public int getMemberStatus(int userId){
        int res= -1;
        if(creatorId==userId)res= MemberConstants.ALPHA;
        return res;
    }

    public Vector<Integer> getRecordMembers(){
        Vector<Integer> res=new Vector<>();
        res.add(creatorId);

        return res;
    }

    @Override
    public String toString() {
        return "IndRecord{" +
                "entries=" + entries +
                ", name='" + name + '\'' +
                ", recordId=" + recordId +
                ", creatorId=" + creatorId +
                '}';
    }
}
