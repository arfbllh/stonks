package Stonks.Records;

import Stonks.MemberConstants;

import java.util.Vector;

public class GroupRecord extends IndRecord {
    public Vector<RecordMember> users;
    public GroupRecord(String name, int userId, int recordId) {
        super(name,userId,recordId);
        users= new Vector<>();
        users.add(new AlphaMember(userId));
    }

    public void addUser(int userId){
        if(users.size()==MemberConstants.MEMBER_LIMIT)return;
        users.add(new OmegaMember(userId));
    }

    private int getUserIdIndex(int userId){
        for(int i=0;i<users.size();i++){
            if(users.get(i).getUserId()==userId)return i;
        }
        return -1;
    }

    @Override
    public boolean hasReadAccess(int userId){
        for(int i=0;i<users.size();i++)if(userId==users.get(i).getUserId())return true;
        return false;
    }

    @Override
    public void grantAccess(int userId){
        //System.out.println("Gave");
        users.add(new OmegaMember(userId));
    }

    @Override
    public boolean hasAddMemberAccess(int userId){
        int index= getUserIdIndex(userId);
        if(index==-1)return false;

        return users.get(index).addMemberAccess();
    }

    @Override
    public boolean hasDeleteEntryAccess(int userId){
        int index= getUserIdIndex(userId);
        if(index==-1)return false;

        return users.get(index).deleteEntryAccess();
    }

    @Override
    public boolean hasEditEntryAccess(int userId){
        int index= getUserIdIndex(userId);
        if(index==-1)return false;

        return users.get(index).editEntryAccess();
    }

    @Override
    public boolean hasRemoveMemberAccess(int userId,int userType){
        int index= getUserIdIndex(userId);
        if(index==-1)return false;

        return users.get(index).removeMemberAccess(userType);
    }

    @Override
    public boolean hasPromoteMemberAccess(int userId,int userType){
        int index= getUserIdIndex(userId);
        if(index==-1)return false;

        return users.get(index).promoteMemberAccess(userType);
    }

    @Override
    public boolean hasDemoteMemberAccess(int userId,int userType){
        int index= getUserIdIndex(userId);
        if(index==-1)return false;

        return users.get(index).demoteMemberAccess(userType);
    }

    @Override
    public boolean hasDeleteRecordAccess(int userId){
        int index= getUserIdIndex(userId);
        if(index==-1)return false;

        return users.get(index).deleteRecordAccess();
    }

    @Override
    public int getMemberStatus(int userId){
        int index= getUserIdIndex(userId);
        if(index==-1)return -1;

        return users.get(index).getMemberType();
    }

    @Override
    public Vector<Integer> getRecordMembers(){
        Vector<Integer> res= new Vector<>();
        for(int i=0;i<users.size();i++){
            res.add(users.get(i).getUserId());
        }

        return res;
    }

    @Override
    public void promoteUser(int userId){
        int index= getUserIdIndex(userId);
        int status= users.get(index).getMemberType();

        RecordMember new_member = null;
        if(status== MemberConstants.SIGMA)new_member= new AlphaMember(userId);
        else if(status==MemberConstants.OMEGA)new_member= new SigmaMember(userId);
        users.remove(index);
        users.add(new_member);
    }

    @Override
    public void demoteUser(int userId){
        int index= getUserIdIndex(userId);
        int status= users.get(index).getMemberType();

        RecordMember new_member = null;
        if(status==MemberConstants.SIGMA)new_member= new OmegaMember(userId);
        users.remove(index);
        users.add(new_member);
    }

    @Override
    public void removeUser(int userId){
        int index= getUserIdIndex(userId);
        users.remove(index);
    }

    @Override
    public String toString() {
        return "GroupRecord{" +
                "users=" + users +
                ", entries=" + entries +
                ", name='" + name + '\'' +
                ", recordId=" + recordId +
                ", creatorId=" + creatorId +
                '}';
    }
}
