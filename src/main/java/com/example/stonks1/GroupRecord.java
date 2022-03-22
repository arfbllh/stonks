package com.example.stonks1;

import java.util.Vector;

public class GroupRecord extends IndRecord {
    Vector<Integer> users;
    GroupRecord(String name, int userId) {
        super(name,userId);
        users= new Vector<>();
        users.add(userId);
    }

    public void addUser(int userId){
        users.add(userId);
    }

    @Override
    public boolean hasAccess(int userId){
        for(int i=0;i<users.size();i++)if(userId==users.get(i))return true;
        return false;
    }

    @Override
    public void grantAccess(int userId){
        System.out.println("Gave");
        users.add(userId);
    }
}
