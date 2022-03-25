package Stonks;

public class SigmaMember extends OmegaMember{
    SigmaMember(int userId){
        super(userId);
    }

    @Override
    public int getMemberType(){
        return MemberConstants.SIGMA;
    }

    @Override
    public boolean editEntryAccess(){
        return true;
    }

    @Override
    public boolean deleteEntryAccess(){
        return true;
    }

    @Override
    public boolean addMemberAccess(){
        return true;
    }

    @Override
    public boolean removeMemberAccess(int memberType){
        if(memberType==MemberConstants.OMEGA)return true;
        return false;
    }

    @Override
    public boolean promoteMemberAccess(int memberType){
        if(memberType==MemberConstants.OMEGA)return true;
        return false;
    }

}
