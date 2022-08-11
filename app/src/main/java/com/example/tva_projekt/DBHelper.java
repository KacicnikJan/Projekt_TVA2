package com.example.tva_projekt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.BoringLayout;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME="Login.db";
    public SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, "Login.db", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {

        MyDB.execSQL("create Table users(id integer primary key autoincrement,username TEXT, password TEXT)");
        MyDB.execSQL("create Table hribovje(id INTEGER primary key autoincrement,imeHribovja TEXT NOT NULL)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {

        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("drop Table if exists admin");
        MyDB.execSQL("drop Table if exists hribovje");
    }

    public Boolean insertData(String username, String password){

        SQLiteDatabase MyDB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("Username",username);
        contentValues.put("Password",password);
        long result=MyDB.insert("users", null, contentValues);
        if (result==-1) return false;
        else
            return true;
    }

    public Boolean checkusername(String username) {

        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor=MyDB.rawQuery("Select*from users where username=?",new String[] {username});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password){

        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor=MyDB.rawQuery("Select*from users where username=? and password=?", new String[] {username, password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkifadmin(String username, String password) {

        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor=MyDB.rawQuery("Select*from users where username=admin and password=admin", new String[] {username, password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public String getUserName(String username) {

        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor=MyDB.rawQuery("Select*from users where username=?",new String[] {username});
        return username;
    }
    public Boolean dodajHribovje(Integer IdHribovja, String ime){
        SQLiteDatabase MyDB=this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put("id", IdHribovja);
        values.put("imeHribovja", ime);
        long result=MyDB.insert("hribovje", null, values);
        if (result==-1) return false;
        else
            return true;

    }
}
