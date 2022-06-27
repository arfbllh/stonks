package Stonks.Records;

import Stonks.Database.RecordInfo;
import Stonks.CountData;


import java.util.Vector;

public class RecordData {
    private static Vector<Record> records = new Vector<>();
    private static Vector<Integer> recordType= new Vector<>();
    private static int currentRecord;

    public static boolean addRecord(String name,int userId,boolean isIndividual){
        if(name.isEmpty())return false;
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
        return true;
    }

    public static int getCurrentRecord(){
        return currentRecord;
    }

    public static void setCurrentRecord(int id){
        currentRecord= id;
    }

    public static int getRecordIdIndex(int recordId){
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

    public static boolean isUserAdded(int recordId,int userId){
        int index= getRecordIdIndex(recordId);
        return records.get(index).isUserAdded(userId);
    }
    public static void grantRecordAccess(int recordId,int userId){
        int index= getRecordIdIndex(recordId);
        records.get(index).grantAccess(userId);
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
    public static void init(){

        recordType = RecordInfo.restore1();
        records = RecordInfo.restore2(recordType);
        //System.out.println("here = " + recordType.size() + " " + records.size());
        //for(Record rec : records) System.out.println(rec.toString());
        //System.out.println(recordType.toString());


    }
    public static void close(){
        RecordInfo.save1(recordType);
        RecordInfo.save2(records, recordType);
    }
}
