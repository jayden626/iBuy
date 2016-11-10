package com.urch.chatwin.ibuy;

/**
 * Created by Jayden on 8/11/2016.
 */

public class User {
    private int id;
    private String name;

    public User(int iId, String iName){
        id = iId;
        name = iName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
            return name;
    }
}
