package com.practice.databaseapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHandler extends SQLiteOpenHelper {

    /* define all the constants to */
    private static final int DB_VERSION = 1;

    private static final String DB_NAME = "userDB", TABLE_NAME = "users", USERID_COL = "id", USERNAME_COL = "name", PASSWORD_COL = "password";

    // Constructor which takes the context of the application, name of the database to create, version of the database to create as arguments
    public DbHandler(MainActivity mainActivityContext) {
        super(mainActivityContext, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // forming a CREATE TABLE query
//        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " ( " + USERID_COL + " INTEGER PRIMARY KEY, " + USERNAME_COL + " TEXT, " + PASSWORD_COL + " TEXT " + " ); ";
        String createTableQuery = "CREATE TABLE users( id INTEGER PRIMARY KEY, name TEXT, password TEXT); " ;
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users; ");
        onCreate(db);
//        db.close();
    }

    // user defined methods to check if the user already exists in the DB
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAME_COL, user.getName());
        contentValues.put(PASSWORD_COL, user.getPassword());
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

    // user defined methods to add new user
    public int checkUser(User user) {
        int requiredId = -1;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor  = db.rawQuery("SELECT id FROM users WHERE name=? AND password=?", new String[]{
                user.getName(), user.getPassword()
        } );

        if(cursor.getCount() > 0){
            /* since id is the only thing we indexed, the returned set will have it at index (0,0) */

            // make the cursor move to the first row
            cursor.moveToFirst();
            // now since the cursor is already in the first row, make it index the value at the first column
            requiredId = cursor.getInt(0);

        }
        cursor.close();

        // at last return the Id we are searhing for if found or else return -1
        return requiredId;
    }

}
