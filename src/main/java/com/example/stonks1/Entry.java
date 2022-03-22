package com.example.stonks1;

import javafx.scene.control.ListCell;

import java.util.Vector;

public class Entry {
    private String name;
    private Vector<String> tags;
    private int amount, recordId;
    private boolean isCashIn;

    Entry(String name,Vector<String> tags,int amount,boolean isCashIn,int recordId){
        this.name= name;
        this.amount= amount;
        this.tags= tags;
        this.isCashIn= isCashIn;
        this.recordId= recordId;
    }

    public int getRecordId(){
        return recordId;
    }

    public String getName(){
        return name;
    }

    public int getAmount(){
        return amount;
    }

    public String getTag(){
        return tags.get(0);
    }

    public boolean getCashInStatus(){
        return isCashIn;
    }

    public void addTag(String tag){
        tags.add(tag);
    }
}
