package com.example.stonks1;

import java.util.Vector;

public class IndRecord extends Record {
    static int count;
    Vector<String> entries;
    String name;
    int id,creatorId;

    IndRecord(String name, int userId){
        this.name= name;
        this.id= count++;
        this.creatorId= userId;
        entries = new Vector<>();
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public void addEntry(String entry){
        entries.add(entry);
        System.out.println("Added "+entry);
    }

    public void showEntries(){
        for(int i=0;i<entries.size();i++)System.out.println(entries.get(i));
    }

    public boolean hasAccess(int userId){
        if(creatorId==userId)return true;
        return false;
    }
}
