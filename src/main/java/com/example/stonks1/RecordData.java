package com.example.stonks1;

import java.util.Vector;

public class RecordData {
    private static Vector<Record> records = new Vector<>();
    private static Vector<Integer> recordType= new Vector<>();
    private static int currentRecord;

    public static String getRecordName(int id){
        return records.get(id).getName();
    }

    public static void addRecord(String name,int userId,boolean isIndividual){
        Record rec;
        if(isIndividual) {
            IndRecord new_rec = new IndRecord(name, userId);
            rec = new_rec;
            recordType.add(0);
        }
        else {
            GroupRecord new_rec= new GroupRecord(name, userId);
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

    public static boolean isIndividual(int recordId){
        if(recordType.get(recordId)==0)return true;
        return false;
    }

    public static void deleteRecord(int recordId){
        for(int i=recordId; i<records.size()-1; i++){
            records.set(i,records.get(i+1));
        }
        records.remove(records.size()-1);
    }

    public static void addEntry(int recId,String entry){
        records.get(recId).addEntry(entry);
    }

    public static Vector<Integer> getUserRecords(int userId,int type){
        Vector<Integer> res= new Vector<>();
        for(int i = 0; i< records.size(); i++){
            if(records.get(i).hasAccess(userId))if(type==2||recordType.get(i)==type)res.add(i);
        }

        return res;
    }

    public static void grantRecordAccess(int recordId,int userId){
        records.get(recordId).grantAccess(userId);
    }
}
