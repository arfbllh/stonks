package Stonks.Records;

import Stonks.MemberConstants;

public class OmegaMember extends RecordMember{
    public int userId;

    OmegaMember(int userId){
        this.userId= userId;
    }

    public int getUserId(){
        return userId;
    }

    public int getMemberType(){
        return MemberConstants.OMEGA;
    }

    @Override
    public boolean addEntryAccess(){
        return true;
    }

}
