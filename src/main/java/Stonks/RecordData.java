package Stonks;

import java.util.Vector;

public class RecordData {
    private static Vector<Record> records = new Vector<>();
    private static Vector<Integer> recordType= new Vector<>();
    private static int currentRecord;

    public static void addRecord(String name,int userId,boolean isIndividual){
        Record rec;
        if(isIndividual) {
            IndRecord new_rec = new IndRecord(name, userId, CountData.recordCount++);
            rec = new_rec;
            recordType.add(0);
        }
        else {
            GroupRecord new_rec= new GroupRecord(name, userId, CountData.recordCount++);
            rec = new_rec;
            recordType.add(1);
        }

        records.add(rec);
    }

    public static int getCurrentRecord(){
        return currentRecord;
    }

    public static void setCurrentRecord(int id){
        currentRecord= id;
    }

    private static int getRecordIdIndex(int recordId){
        for(int i=0;i<records.size();i++){
            if(records.get(i).getId()==recordId)return i;
        }

        return -1;
    }

    public static String getRecordName(int id){
        int index= getRecordIdIndex(id);

        return records.get(index).getName();
    }

    public static boolean isIndividual(int recordId){
        int index= getRecordIdIndex(recordId);

        if(recordType.get(index)==0)return true;
        return false;
    }

    public static void deleteRecord(int recordId){
        int index= getRecordIdIndex(recordId);
        //System.out.println(index);
        recordType.remove(index);
        records.remove(index);
    }

    public static void addEntry(int recId,String entry){
        records.get(recId).addEntry(entry);
    }

    public static Vector<Integer> getUserRecords(int userId,int type){
        Vector<Integer> res= new Vector<>();
        for(int i = 0; i< records.size(); i++){
            if(records.get(i).hasReadAccess(userId))if(type==2||recordType.get(i)==type)res.add(records.get(i).getId());
        }

        return res;
    }

    public static void grantRecordAccess(int recordId,int userId){
        records.get(recordId).grantAccess(userId);
    }

    public static int getRecordMemberStatus(int recordId,int userId){
        int index= getRecordIdIndex(recordId);
        return records.get(index).getMemberStatus(userId);
    }

    public static boolean hasAddMemberAccess(int recordId,int userId){
        int index= getRecordIdIndex(recordId);
        return records.get(index).hasAddMemberAccess(userId);
    }

    public static boolean hasDeleteEntryAccess(int recordId,int userId){
        int index= getRecordIdIndex(recordId);
        return records.get(index).hasDeleteEntryAccess(userId);
    }

    public static boolean hasEditEntryAccess(int recordId,int userId){
        int index= getRecordIdIndex(recordId);
        return records.get(index).hasEditEntryAccess(userId);
    }

    public static boolean hasRemoveMemberAccess(int recordId,int userId,int targetType){
        int index= getRecordIdIndex(recordId);
        return records.get(index).hasRemoveMemberAccess(userId,targetType);
    }

    public static boolean hasPromoteMemberAccess(int recordId,int userId,int targetType){
        int index= getRecordIdIndex(recordId);
        return records.get(index).hasPromoteMemberAccess(userId,targetType);
    }

    public static boolean hasDemoteMemberAccess(int recordId,int userId,int targetType){
        int index= getRecordIdIndex(recordId);
        return records.get(index).hasDemoteMemberAccess(userId,targetType);
    }

    public static boolean hasDeleteRecordAccess(int recordId,int userId){
        int index= getRecordIdIndex(recordId);
        return records.get(index).hasDeleteRecordAccess(userId);
    }

    public static Vector<Integer> getRecordUsers(int recordId){
        int index= getRecordIdIndex(recordId);
        return records.get(index).getRecordMembers();
    }

    public static void promoteRecordMember(int recordId,int userId){
        int index= getRecordIdIndex(recordId);
        records.get(index).promoteUser(userId);
    }

    public static void demoteRecordMember(int recordId,int userId){
        int index= getRecordIdIndex(recordId);
        records.get(index).demoteUser(userId);
    }

    public static void removeRecordMember(int recordId,int userId){
        int index= getRecordIdIndex(recordId);
        records.get(index).removeUser(userId);
    }
    static void init(){

        DataStore<Record> obj = new DataStore<Record>();
        records = obj.init("record1");
        DataStore<Integer> obj2 = new DataStore<Integer>();
        recordType = obj2.init("record2");


    }
    static void close(){
        DataStore<Record> obj = new DataStore<Record>();
        DataStore<Integer> obj2 = new DataStore<Integer>();
        obj.close("record1", records);
        obj2.close("record2", recordType);
    }
}