package com.example.tva_projekt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.BoringLayout;
import android.util.SparseIntArray;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME="Login.db";
    public SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, "Login.db", null, 5);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {

        MyDB.execSQL("create Table users(id integer primary key autoincrement,username TEXT, password TEXT)");
        MyDB.execSQL("create Table dhribovje(id INTEGER primary key autoincrement,imeHribovja TEXT NOT NULL)");
        MyDB.execSQL("create Table dvrh(idVrha INTEGER primary key autoincrement,imeVrha TEXT NOT NULL, ndmv INTEGER NOT NULL, longitude TEXT NOT NULL, latitude TEXT NOT NULL, opis TEXT NOT NULL, idHribovja INTEGER NOT NULL, FOREIGN KEY(idHribovja) REFERENCES dhribovje(id))");
    }


    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {

        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("drop Table if exists admin");
        MyDB.execSQL("drop Table if exists dhribovje");
        MyDB.execSQL("drop table if exists dvrh");
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
        long result=MyDB.insert("dhribovje", null, values);
        if (result==-1) return false;
        else
            return true;

    }
//    public Vrh(String imeVrha, String opis, String lokacijaVrhLong, String lokacijaVrhLat, Integer idVrha, Integer idHribovja, Integer ndmv
    public Boolean dodajVrh(String imeVrha, String opis, String lokacijaVrhLong, String lokacijaVrhLat, Integer idVrha, Integer idHribovja, Integer ndmv){
        SQLiteDatabase MyDB=this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put("id", IdHribovja);
        values.put("imeVrha", imeVrha);
        values.put("opis", opis);
        values.put("latitude",lokacijaVrhLat);
        values.put("longitude", lokacijaVrhLong);
        values.put("idHribovja", idHribovja);
        values.put("ndmv", ndmv);
        long result=MyDB.insert("dvrh", null, values);
        if (result==-1) return false;
        else
            return true;

    }
    public Integer pridobiStVrhov(Integer idHribovja){
        SQLiteDatabase db=this.getReadableDatabase();

        Integer result = (int) DatabaseUtils.queryNumEntries(db,"dvrh","idHribovja=?",new String[]{idHribovja.toString()});
        if(result != 0) {
            return result;
        }
        else return 0;
    }
    public ArrayList<Hribovje> izpisiHribovja(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursorHribovja = db.rawQuery("SELECT * FROM dhribovje",null);
        ArrayList<Hribovje> hribovjeArrayList = new ArrayList<>();

        if(cursorHribovja.moveToFirst()){
            do {
                Hribovje hribovje = new Hribovje();
                hribovje.setIdHribovja(Integer.parseInt(cursorHribovja.getString(0)));
                hribovje.setIme(cursorHribovja.getString(1));
                hribovjeArrayList.add(hribovje);
                } while (cursorHribovja.moveToNext());
            }
        cursorHribovja.close();
        return hribovjeArrayList;
    }
    public ArrayList<Vrh> izpisiVrhove(Integer idHribovja){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursorVrhovi = db.rawQuery("SELECT * FROM dvrh WHERE idHribovja=? ORDER BY ndmv DESC",new String[]{idHribovja.toString()});
        ArrayList<Vrh> vrhArrayList = new ArrayList<>();
        if(cursorVrhovi.moveToFirst()){
            do {
                Vrh vrh = new Vrh();
                vrh.setIdVrha(Integer.parseInt(cursorVrhovi.getString(0)));
                vrh.setImeVrha(cursorVrhovi.getString(1));
                vrh.setNdmv(Integer.parseInt(cursorVrhovi.getString(2)));
                vrh.setLokacijaVrhLong(cursorVrhovi.getString(3));
                vrh.setLokacijaVrhLat(cursorVrhovi.getString(4));
                vrh.setOpis(cursorVrhovi.getString(5));
                vrh.setIdHribovja(idHribovja);
                vrhArrayList.add(vrh);
            } while (cursorVrhovi.moveToNext());
        }
        cursorVrhovi.close();
        return vrhArrayList;
    }
    public List<Vrh> search(String keyword){
        List<Vrh> vrhs=null;
        try{
            SQLiteDatabase db=this.getReadableDatabase();
            Cursor cursor = db.rawQuery("Select * FROM dvrh WHERE imeVrha LIKE ?", new String[]{"%" + keyword + "%"});
            if (cursor.moveToFirst()){
                vrhs=new ArrayList<>();
                do {
                    Vrh vrh = new Vrh();
                    vrh.setIdVrha(Integer.parseInt(cursor.getString(0)));
                    vrh.setImeVrha(cursor.getString(1));
                    vrh.setNdmv(Integer.parseInt(cursor.getString(2)));
                    vrh.setLokacijaVrhLong(cursor.getString(3));
                    vrh.setLokacijaVrhLat(cursor.getString(4));
                    vrh.setOpis(cursor.getString(5));
                    vrh.setIdHribovja(Integer.parseInt(cursor.getString(6)));
                    vrhs.add(vrh);
                }
                while (cursor.moveToNext());
            }
        } catch (Exception e){
            vrhs=null;
        }
        return vrhs;
    }


}
