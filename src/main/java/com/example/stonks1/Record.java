package com.example.stonks1;

public abstract class Record {
    abstract public int getId();

    abstract public String getName();

    abstract public void addEntry(String entry);

    abstract public void showEntries();

    abstract public boolean hasAccess(int userId);

    public void addUser(int userId){
        return;
    }

    public void grantAccess(int userId){
        return;
    }
}
