package Stonks.Records;

import java.io.Serial;
import java.io.Serializable;
import java.util.Vector;

public abstract class Record implements Serializable {
    abstract public int getId();

    abstract public String getName();

    abstract public void addEntry(String entry);

    abstract public void showEntries();

    abstract public boolean hasReadAccess(int userId);

    abstract public boolean hasAddMemberAccess(int userId);

    abstract public boolean hasDeleteEntryAccess(int userId);

    abstract public boolean hasEditEntryAccess(int userId);

    abstract public boolean hasRemoveMemberAccess(int userId,int userType);

    abstract public boolean hasPromoteMemberAccess(int userId,int userType);

    abstract public boolean hasDemoteMemberAccess(int userId,int userType);

    abstract public boolean hasDeleteRecordAccess(int userId);

    abstract public int getMemberStatus(int userId);

    abstract public Vector<Integer> getRecordMembers();

    public void addUser(int userId){
        return;
    }

    public void grantAccess(int userId){
        return;
    }

    public void demoteUser(int userId){
        return;
    }

    public void promoteUser(int userId){
        return;
    }

    public void removeUser(int userId){
        return;
    }

    @Override
    public String toString() {
        return "Record{}";
    }
}
