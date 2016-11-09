package com.urch.chatwin.ibuy;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Jayden on 7/11/2016.
 */

public class Item implements Serializable {
    private int id;
    private String name;
    private String category;
    private String location;
    private double cost;
    private int quantity;
    private Calendar entered;
    private Calendar due;
    private boolean isCommon;
    private int uID;

    public Item(int iID, String iName, String iCategory, String iLocation, double iCost, int iQuant, long iEnt, long iDue, boolean icom, int iUID){
        id = iID;
        name = iName;
        category = iCategory;
        location = iLocation;
        cost = iCost;
        quantity = iQuant;
        entered = Calendar.getInstance();
        entered.setTimeInMillis(iEnt);
        due = Calendar.getInstance();
        due.setTimeInMillis(iDue);
        isCommon = icom;
        uID = iUID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setEntered(long time) {
        this.entered.setTimeInMillis(time);
    }

    public void setDue(long time) {
        this.due.setTimeInMillis(time);
    }

    public void setCommon(boolean common) {
        isCommon = common;
    }

    public void setuID(int uID) {
        this.uID = uID;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getLocation() {
        return location;
    }

    public double getCost() {
        return cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public long getEntered() {
        return entered.getTimeInMillis();
    }

    public long getDue() {
        return due.getTimeInMillis();
    }

    public boolean isCommon() {
        return isCommon;
    }

    public int getuID() {
        return uID;
    }

    public String toString(){
        DateFormat formatter= SimpleDateFormat.getDateInstance();
        return "name: "+name+" quan: " + quantity + " cost: "+cost+" category: "+category+" location: "+location+" due: "+ formatter.format(due.getTime()) + " entered: " + formatter.format(entered.getTime()) + " common: " + String.valueOf(isCommon) + " user: " + uID;
    }
}
