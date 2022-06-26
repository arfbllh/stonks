package Stonks.Records;

import Stonks.MemberConstants;

public class AlphaMember extends SigmaMember{
    AlphaMember(int userId){
        super(userId);
    }

    @Override
    public int getMemberType(){
        return MemberConstants.ALPHA;
    }

    @Override
    public boolean deleteRecordAccess(){
        return true;
    }

    @Override
    public boolean removeMemberAccess(int memberType){
        if(memberType==MemberConstants.OMEGA||memberType==MemberConstants.SIGMA)return true;
        return false;
    }

    @Override
    public boolean promoteMemberAccess(int memberType){
        if(memberType==MemberConstants.OMEGA||memberType==MemberConstants.SIGMA)return true;
        return false;
    }

    @Override
    public boolean demoteMemberAccess(int memberType){
        if(memberType==MemberConstants.SIGMA)return true;
        return false;
    }
}
