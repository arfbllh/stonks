package com.example.stonks1;

import java.util.Vector;

public class EntryData {
    private static Vector<Entry> entries= new Vector<>();
    private static int currentEntry;

    public static void addEntry(String name,Vector<String> tags,int amount,boolean isCashIn,int recordId){
        entries.add(new Entry(name,tags,amount,isCashIn,recordId));
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
            if(entries.get(i).getRecordId()==recordId)res.add(i);
        }

        return res;
    }

    public static String getEntryName(int entryId){
        return entries.get(entryId).getName();
    }

    public static int getEntryAmount(int entryId){
        return entries.get(entryId).getAmount();
    }

    public static String getEntryTag(int entryId){
        return entries.get(entryId).getTag();
    }

    public static boolean isCashIn(int entryId){
        return entries.get(entryId).getCashInStatus();
    }

    public static void deleteEntry(int entryId){
        for(int i=entryId; i<entries.size()-1; i++){
            entries.set(i,entries.get(i+1));
        }
        entries.remove(entries.size()-1);
    }
}
