package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class UserDatabaseManager {
    private MyDatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public UserDatabaseManager(Context context) {
        dbHelper = new MyDatabaseHelper(context);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    // Create (Insert) a new user
    public void insertUser(String name, int age, String email) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("age", age);
        values.put("email", email);
        long result = db.insert("myusers", null, values);
    }

    // Read (Query) all users
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Cursor cursor = db.query("myusers", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            int age = cursor.getInt(cursor.getColumnIndexOrThrow("age"));
            String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
            users.add(new User(id, name, age, email));
        Log.d("wda1", "getAllUsers: "+users);
        }
        cursor.close();
        return users;
    }

    // Update a user's details
    public int updateUser(String name, int newAge, String newEmail) {
        ContentValues values = new ContentValues();
        values.put("age", newAge);
        values.put("email", newEmail);
        String selection = "name = ?";
        String[] selectionArgs = { name };
        return db.update("myusers", values, selection, selectionArgs);
    }

    // Delete a user
    public int deleteUser(String name) {
        String selection = "name LIKE ?";
        String[] selectionArgs = { name };
        return db.delete("myusers", selection, selectionArgs);
    }
}
