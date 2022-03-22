package com.example.stonks1;

import java.io.Serializable;
import java.util.Vector;

public class allMyUsersLiveinThisHell implements Serializable {
    private Vector<User> users = new Vector<User>();

    public allMyUsersLiveinThisHell(Vector<User> users) {
        this.users = users;
    }

    public Vector<User> getUsers() {
        return users;
    }

    public void setUsers(Vector<User> users) {
        this.users = users;
    }
}
