package Stonks.Entries;

import Database.*;
import Stonks.CountData;
import javafx.util.Pair;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public class EntryData {
    private static Vector<Entry> entries= new Vector<>();
    private static int currentEntry;

    public static int addEntry(String name,Vector<String> tags,String amount,boolean isCashIn,int recordId){
        if(name.isEmpty()||tags.get(0).isEmpty()||amount.isEmpty())return -1;
        for(int i=0;i<amount.length();i++){
            if(amount.charAt(i)<'0'||amount.charAt(i)>'9')return 0;
        }

        int parsedAmount= Integer.valueOf(amount);
        entries.add(new Entry(name,tags,parsedAmount,isCashIn,recordId, CountData.entryCount++));
        return 1;
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

    public static int getTotalCashInByTag(int recordId,String tagName){
        int res=0;
        for(int i=0;i<entries.size();i++){
            Entry entry= entries.get(i);
            if(entry.getRecordId()==recordId && entry.getTag().equals((tagName))&& entry.getCashInStatus()){
                res += entry.getAmount();
            }
        }
        return res;
    }

    public static int getTotalCashOutByTag(int recordId,String tagName){
        int res=0;
        for(int i=0;i<entries.size();i++){
            Entry entry= entries.get(i);
            if(entry.getRecordId()==recordId && entry.getTag().equals((tagName))&& !entry.getCashInStatus()){
                res += entry.getAmount();
            }
        }
        return res;
    }

    public static Vector<Pair<String,Integer>> getRecordCashInByTagNames(int recordId){
        Vector<Pair<String,Integer>> res = new Vector<>();
        Set<String> taken= new HashSet<>();

        for(int i=0;i<entries.size();i++){
            if(entries.get(i).getRecordId()==recordId  && entries.get(i).getCashInStatus() && !taken.contains(entries.get(i).getTag())){
                res.add(new Pair(entries.get(i).getTag(),getTotalCashInByTag(recordId,entries.get(i).getTag())));
                taken.add(entries.get(i).getTag());
            }
        }
        return res;
    }

    public static Vector<Pair<String,Integer>> getRecordCashOutByTagNames(int recordId){
        Vector<Pair<String,Integer>> res = new Vector<>();
        Set<String> taken= new HashSet<>();

        for(int i=0;i<entries.size();i++){
            if(entries.get(i).getRecordId()==recordId && !entries.get(i).getCashInStatus() && !taken.contains(entries.get(i).getTag())){
                res.add(new Pair(entries.get(i).getTag(),getTotalCashOutByTag(recordId,entries.get(i).getTag())));
                taken.add(entries.get(i).getTag());
            }
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
