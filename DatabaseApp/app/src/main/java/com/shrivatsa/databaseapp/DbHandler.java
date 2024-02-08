package com.shrivatsa.databaseapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHandler extends SQLiteOpenHelper {
// SQLiteOpenHelper is an abstract class hence all it's abstract methods must be implemented by our class
    private static final int DB_VERSION = 1;
    // store database and table name
    private static final String DB_NAME = "usersDB", TABLE_NAME = "users";
    // store table design
    private static final String USER_ID_COL = "id", USER_NAME_COL = "name", USER_PASSWORD_COL = "password";

    // the super class SQLiteOpenHelper does not have default constructor
    // the constructor of the parent class takes a context of an activity as one of the argument
    public DbHandler(MainActivity context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // for creating a table we use the onCreate() method
    @Override
    public void onCreate(SQLiteDatabase db){
        String createTableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + USER_ID_COL + "INTEGER PRIMARY KEY, " + USER_NAME_COL + " TEXT" + ")";
        db.execSQL(createTableQuery);
    }

    // when the version of the database changes we call the onUpgrade()
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME );
        onCreate(db);
    }

    // adding users to database using ContentValues holder
    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase(); // returns a database connection
        // a ContentValue object stores in a key value form
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME_COL, user.getName());
        contentValues.put(USER_PASSWORD_COL, user.getPassword());
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

    public int checkUser(User user) {
        // initially set the id to -1
        int id = -1;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT id FROM authorisedUsers WHERE name = ? AND password = ?", new String[]{
                user.getName(), user.getPassword()
        });
        if(Cursor.getCount() > 0){
            cursor.moveToFirst();
            id = cursor.getInt(0);
            cursor.close();
        }
        return id;
    }
}
