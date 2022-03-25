package Stonks;

import java.io.Serializable;

public abstract class RecordMember implements Serializable {
    abstract int getMemberType();

    abstract int getUserId();

    boolean addEntryAccess(){
        return false;
    }

    boolean addMemberAccess(){
        return false;
    }

    boolean deleteEntryAccess(){
        return false;
    }

    boolean editEntryAccess(){
        return false;
    }

    boolean removeMemberAccess(int memberType){
        return false;
    }

    boolean promoteMemberAccess(int memberType){
        return false;
    }

    boolean demoteMemberAccess(int memberType){
        return false;
    }

    boolean deleteRecordAccess(){
        return false;
    }

    @Override
    public String toString() {
        return "RecordMember{}";
    }
}
