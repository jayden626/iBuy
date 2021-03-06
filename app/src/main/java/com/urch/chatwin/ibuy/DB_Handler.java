package com.urch.chatwin.ibuy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DB_Handler extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "iBuy";
    // Contacts table name
    private static final String TABLE_ITEMS = "items";
    // Shops Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_COST = "cost";
    private static final String KEY_QUANTITY = "quantity";
    private static final String KEY_ENTERED = "entered";
    private static final String KEY_DUE = "due";
    private static final String KEY_COMMON = "common";
    private static final String KEY_UID = "uID";
    private static final String KEY_PURCHASED = "purchased";

    private static final String TABLE_USERS = "USERS";
    private static final String TABLE_COMMON = "COMMON";


    public DB_Handler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ITEMS_TABLE = "CREATE TABLE " + TABLE_ITEMS + "("
        + KEY_ID + " INTEGER PRIMARY KEY,"
        + KEY_NAME + " TEXT,"
        + KEY_CATEGORY + " TEXT,"
        + KEY_LOCATION + " TEXT,"
        + KEY_COST + " INTEGER,"
        + KEY_QUANTITY + " INTEGER,"
        + KEY_ENTERED + " INTEGER,"
        + KEY_DUE + " INTEGER,"
        + KEY_COMMON + " INTEGER,"
        + KEY_UID + " INTEGER,"
        + KEY_PURCHASED + " INTEGER,"
        + "FOREIGN KEY("+KEY_UID+") REFERENCES "+TABLE_USERS+"("+KEY_ID+")" + ")";

        /*String CREATE_COMMON_TABLE = "CREATE TABLE " + TABLE_COMMON + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_CATEGORY + " TEXT,"
                + KEY_LOCATION + " TEXT,"
                + KEY_COST + " INTEGER,"
                + KEY_COMMON + " INTEGER" + ")";*/

        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USERS+ "("
        + KEY_ID + " INTEGER PRIMARY KEY,"
        + KEY_NAME + " TEXT" + ")";
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_ITEMS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
// Creating tables again
        onCreate(db);
    }
    // Adding new Item
    public void addItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, item.getName());
        values.put(KEY_CATEGORY, item.getCategory());
        values.put(KEY_LOCATION, item.getLocation());
        values.put(KEY_COST, item.getCost()*100);
        values.put(KEY_QUANTITY, item.getQuantity());
        values.put(KEY_ENTERED, item.getEntered());
        values.put(KEY_DUE, item.getDue());
        values.put(KEY_COMMON, item.isCommon());
        values.put(KEY_UID, item.getuID());
        values.put(KEY_PURCHASED, 0);

// Inserting Row
        db.insert(TABLE_ITEMS, null, values);
        db.close(); // Closing database connection
    }

    public void addUser(String name) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);

// Inserting Row
        db.insert(TABLE_USERS, null, values);
        db.close(); // Closing database connection
    }
    // Getting one shop
    public Item getItem(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ITEMS, new String[]{KEY_ID,
                KEY_NAME, KEY_CATEGORY, KEY_LOCATION, KEY_COST, KEY_QUANTITY, KEY_ENTERED, KEY_DUE, KEY_COMMON, KEY_UID}, KEY_ID + "=?",
        new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        boolean com = cursor.getString(8).equalsIgnoreCase("1");
        Item item = new Item(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2), cursor.getString(3), Double.parseDouble(cursor.getString(4))/100,
                Integer.parseInt(cursor.getString(5)), Long.parseLong(cursor.getString(6)),
                Long.parseLong(cursor.getString(7)), com, Integer.parseInt(cursor.getString(9)));
        cursor.close();
        db.close();
        return item;
    }

    public User getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERS, new String[]{KEY_ID,
                        KEY_NAME}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        User user = new User(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
        cursor.close();
        db.close();
        return user;
    }

    // Getting All Items
    public ArrayList<Item> getAllItems() {
        ArrayList<Item> itemList = new ArrayList<Item>();
// Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_ITEMS + " WHERE " + KEY_PURCHASED + " = 0";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                boolean com = cursor.getString(8).equalsIgnoreCase("1");

                Item item = new Item(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3), Double.parseDouble(cursor.getString(4))/100,
                        Integer.parseInt(cursor.getString(5)), Long.parseLong(cursor.getString(6)),
                        Long.parseLong(cursor.getString(7)), com, Integer.parseInt(cursor.getString(9)));
// Adding contact to list
                itemList.add(item);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
// return contact list
        return itemList;
    }

    // Getting All Items
    public ArrayList<Item> getAllCommon() {
        ArrayList<Item> itemList = new ArrayList<Item>();
// Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_ITEMS + " WHERE " + KEY_COMMON + " = 1";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                boolean com = cursor.getString(8).equalsIgnoreCase("1");

                Item item = new Item(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3), Double.parseDouble(cursor.getString(4))/100,
                        Integer.parseInt(cursor.getString(5)), Long.parseLong(cursor.getString(6)),
                        Long.parseLong(cursor.getString(7)), com, Integer.parseInt(cursor.getString(9)));
// Adding contact to list
                itemList.add(item);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
// return contact list
        return itemList;
    }

    // Getting All Users
    public ArrayList<User> getAllUsers() {
        ArrayList<User> userList = new ArrayList<User>();
// Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
                userList.add(user);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
// return contact list
        return userList;
    }

    // Getting items Count
    public int getItemsCount() {
        String countQuery = "SELECT * FROM " + TABLE_ITEMS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
// return count
        return count;
    }

    // Getting users Count
    public int getUsersCount() {
        String countQuery = "SELECT * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

// return count
        return count;
    }
    // Updating a shop
    public int updateItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_NAME, item.getName());
            values.put(KEY_LOCATION, item.getLocation());
            values.put(KEY_COST, item.getCost() * 100);
            values.put(KEY_QUANTITY, item.getQuantity());
            values.put(KEY_ENTERED, item.getEntered());
            values.put(KEY_DUE, item.getDue());
            values.put(KEY_COMMON, item.isCommon());
            values.put(KEY_UID, item.getuID());

            // updating row
            return db.update(TABLE_ITEMS, values, KEY_ID + " = ?",
                    new String[]{String.valueOf(item.getId())});
        }finally {
            db.close();
        }
    }

    public void purchaseItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put(KEY_PURCHASED, 1);

// updating row
            db.update(TABLE_ITEMS, values, KEY_ID + " = ?",
                    new String[]{String.valueOf(item.getId())});
        }finally {
            db.close();
        }
    }

    public void nonCommon(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put(KEY_COMMON, 0);

// updating row
            db.update(TABLE_ITEMS, values, KEY_ID + " = ?",
                    new String[]{String.valueOf(item.getId())});
        }finally {
            db.close();
        }
    }

    // Deleting a shop
    public void deleteItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            db.delete(TABLE_ITEMS, KEY_ID + " = ?",
                new String[] { String.valueOf(item.getId()) });
        }finally {
            db.close();
        }
    }
}