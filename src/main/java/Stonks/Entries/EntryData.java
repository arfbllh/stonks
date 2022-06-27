package Stonks.Entries;

import Database.EntryManage;
import Stonks.CountData;
import Stonks.DataStore;

import java.util.Vector;

public class EntryData {
    private static Vector<Entry> entries= new Vector<>();
    private static int currentEntry;

    public static void addEntry(String name,Vector<String> tags,int amount,boolean isCashIn,int recordId){
        entries.add(new Entry(name,tags,amount,isCashIn,recordId, CountData.entryCount++));
    }

    private static int getEntryIdIndex(int id){
        for(int i=0;i<entries.size();i++){
            if(entries.get(i).getId()==id)return i;
        }

        return -1;
    }

    public static int getCurrentEntry(){
        return currentEntry;
    }

    public static void setCurrentEntry(int id){
        currentEntry= id;
    }

    public static Vector<Integer> getRecordEntries(int recordId){
        Vector<Integer> res= new Vector<>();

        for(int i=0;i<entries.size();i++){
            if(entries.get(i).getRecordId()==recordId)res.add(entries.get(i).getId());
        }

        return res;
    }

    public static String getEntryName(int entryId){
        int index= getEntryIdIndex(entryId);
        return entries.get(index).getName();
    }

    public static int getEntryAmount(int entryId){
        int index= getEntryIdIndex(entryId);
        return entries.get(index).getAmount();
    }

    public static String getEntryTag(int entryId){
        int index= getEntryIdIndex(entryId);
        return entries.get(index).getTag();
    }

    public static boolean isCashIn(int entryId){
        int index= getEntryIdIndex(entryId);
        return entries.get(index).getCashInStatus();
    }

    public static void deleteEntry(int entryId){
        int index= getEntryIdIndex(entryId);

        entries.remove(index);
    }

    public static int getRecordCashIn(int recordId){
        int result=0;
        for(int i=0;i<entries.size();i++){
            if(entries.get(i).getRecordId()==recordId && entries.get(i).getCashInStatus()==true){
                result += entries.get(i).getAmount();
            }
        }

        return result;
    }

    public static int getRecordCashOut(int recordId){
        int result=0;
        for(int i=0;i<entries.size();i++){
            if(entries.get(i).getRecordId()==recordId && entries.get(i).getCashInStatus()==false){
                result += entries.get(i).getAmount();
            }
        }

        return result;
    }

    public static int getRecordNet(int recordId){
        int result= getRecordCashIn(recordId)-getRecordCashOut(recordId);
        return result;
    }
    public static void init(){
        entries = EntryManage.init();

    }
    public static void close(){
        EntryManage.exit(entries);
    }
}