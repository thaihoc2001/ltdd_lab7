package com.example.lab7_lttbdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class UserData extends SQLiteOpenHelper {
    private static final int DATABASE_VERION = 1;
    private static final String DATABASE_NAME = "peopleDB";
    private static final String TABLE_PEOPLE = "peoples";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";

    public UserData(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTablePeopleScript =
                "CREATE TABLE " + TABLE_PEOPLE + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT" + ")";

        sqLiteDatabase.execSQL(createTablePeopleScript);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PEOPLE);

        onCreate(sqLiteDatabase);
    }

    public void addPeople(User user){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(KEY_NAME, user.getName());

        sqLiteDatabase.insert(TABLE_PEOPLE, null, contentValues);

        sqLiteDatabase.close();
    }

    public User getPeople(int id){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(
                TABLE_PEOPLE,
                new String[] {
                        KEY_ID, KEY_NAME
                },
                KEY_ID + "=?",
                new String[] {String.valueOf(id)},
                null, null,
                "ORDER BY " + KEY_ID + " ASC",
                null
        );

        if (cursor != null)
            cursor.moveToFirst();

       User user = new User(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1)
        );

        return user;
    }

    public List<User> getAllPeoples(){
        List<User> list = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_PEOPLE;

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            while (cursor.moveToNext()){
                list.add(
                        new User(
                                Integer.parseInt(cursor.getString(0)),
                                cursor.getString(1)
                        )
                );
            }
        }

        return list;
    }


    public int updatePeople(User user){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(KEY_ID, user.getId());
        contentValues.put(KEY_NAME, user.getName());

        return sqLiteDatabase.update(
                TABLE_PEOPLE,
                contentValues,
                KEY_ID + "=?",
                new String[]{String.valueOf(user.getId())}
        );
    }

    public void deletePeople(int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        sqLiteDatabase.delete(
                TABLE_PEOPLE,
                KEY_ID + "=?",
                new String[]{String.valueOf(id)}
        );

        sqLiteDatabase.close();
    }

    public int getPeopleCount(){
        String countQuery = "SELECT * FROM " + TABLE_PEOPLE;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);

        cursor.close();;

        return cursor.getCount();
    }
}
