package com.example.finalproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class MenuDb {
    // database constants
    public static final String DB_NAME = "menu.db";
    public static final int    DB_VERSION = 1;

    // list table constants
    public static final String MENU_TABLE = "menu";

    public static final String MENU_ID = "_id";
    public static final int    MENU_ID_COL = 0;

    public static final String MENU_NAME = "menu_name";
    public static final int    MENU_NAME_COL = 1;

    public static final String MENU_PRICE = "menu_price";
    public static final int    MENU_PRICE_COL = 2;


    // CREATE and DROP TABLE statements
    public static final String CREATE_MENU_TABLE =
            "CREATE TABLE " + MENU_TABLE + " (" +
                    MENU_ID   + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    MENU_NAME + " TEXT    UNIQUE," +
                    MENU_PRICE + " DOUBLE)";

    public static final String DROP_MENU_TABLE =
            "DROP TABLE IF EXISTS " + MENU_TABLE;

    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name,
                        SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // create tables
            db.execSQL(CREATE_MENU_TABLE);

            // insert menu items
            db.execSQL("INSERT INTO menu VALUES (0, 'Hamburger', 5.25)");
            db.execSQL("INSERT INTO menu VALUES (1, 'Chicken Sandwich', 6.24)");
            db.execSQL("INSERT INTO menu VALUES (2, 'Beef Sandwich', 6.24)");
            db.execSQL("INSERT INTO menu VALUES (3, 'Spread Cheese Sandwich', 8.47)");
            db.execSQL("INSERT INTO menu VALUES (4, 'Blue Cheese', 3.12)");
            db.execSQL("INSERT INTO menu VALUES (5, 'Spicy Chicken Sandwich', 4.35)");
            db.execSQL("INSERT INTO menu VALUES (6, 'Spicy Beef Sandwich', 6.24)");
            db.execSQL("INSERT INTO menu VALUES (7, 'Green Salad', 10.25)");
            db.execSQL("INSERT INTO menu VALUES (8, 'Fruit Mix', 7.64)");
            db.execSQL("INSERT INTO menu VALUES (9, 'Bread', 1.55)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db,
                              int oldVersion, int newVersion) {

            Log.d("Menu", "Upgrading db from version "
                    + oldVersion + " to " + newVersion);

            Log.d("Menu", "Deleting all data!");
            db.execSQL(MenuDb.DROP_MENU_TABLE);
            onCreate(db);
        }
    }

    // database object and database helper object
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    // constructor
    public MenuDb(Context context) {
        dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
    }

    // private methods
    private void openReadableDB() {
        db = dbHelper.getReadableDatabase();
    }

    private void openWriteableDB() {
        db = dbHelper.getWritableDatabase();
    }

    private void closeDB() {
        if (db != null)
            db.close();
    }

    // public methods
    public ArrayList<OrderItem> getMenuItems() {
        ArrayList<OrderItem> menuItems = new ArrayList<OrderItem>();
        openReadableDB();

        Cursor cursor = db.query(MENU_TABLE,
                null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            OrderItem menuItem = new OrderItem();
            menuItem.setId(cursor.getInt(MENU_ID_COL));
            menuItem.setItemName(cursor.getString(MENU_NAME_COL));
            menuItem.setItemPrice(cursor.getDouble(MENU_PRICE_COL));
            menuItem.initOrderItem();

            menuItems.add(menuItem);
        }
        cursor.close();
        closeDB();
        return menuItems;
    }
}
