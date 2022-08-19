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
        super(context, "Login.db", null, 6);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {

        MyDB.execSQL("create Table users(id integer primary key autoincrement,username TEXT, password TEXT)");
        MyDB.execSQL("create Table dhribovje(id INTEGER primary key autoincrement,imeHribovja TEXT NOT NULL)");
        MyDB.execSQL("create Table dvrh(idVrha INTEGER primary key autoincrement,imeVrha TEXT NOT NULL, ndmv INTEGER NOT NULL, longitude TEXT NOT NULL, latitude TEXT NOT NULL, opis TEXT NOT NULL, idHribovja INTEGER NOT NULL, FOREIGN KEY(idHribovja) REFERENCES dhribovje(id))");
        MyDB.execSQL("create Table dpot(idPot INTEGER primary key autoincrement, imePoti TEXT NOT NULL, zahtevnost TEXT NOT NULL, casHoje TEXT NOT NULL, izhodisceLat TEXT NOT NULL, izhodisceLong TEXT NOT NULL, opisPoti TEXT NOT NULL, link TEXT NOT NULL, idVrha INTEGER NOT NULL, izhodisceDostop TEXT NOT NULL, FOREIGN KEY(idVrha) REFERENCES dvrh(idVrha))");

    }


    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {

        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("drop Table if exists admin");
        MyDB.execSQL("drop Table if exists dhribovje");
        MyDB.execSQL("drop table if exists dvrh");
        MyDB.execSQL("drop table if exists dpot");
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

    public  Boolean dodajPot(Integer idPot, Integer idVrha, String imePoti, String zahtevnost, String casHoje, String izhodisceLat, String izhodisceLong, String opisPoti, String link, String izhodisceDostop){
        SQLiteDatabase MyDB=this.getWritableDatabase();


        ContentValues values=new ContentValues();

        values.put("idVrha", idVrha);
        values.put("imePoti", imePoti);
        values.put("zahtevnost", zahtevnost);
        values.put("casHoje", casHoje);
        values.put("izhodisceLat", izhodisceLat);
        values.put("izhodisceLong", izhodisceLong);
        values.put("opisPoti", opisPoti);
        values.put("link", link);
        values.put("izhodisceDostop", izhodisceDostop);
        long result=MyDB.insert("dpot",null,values);
        if (result==-1) return false;
        else
            return true;
    }
    public Integer pridobiIdVrha(CharSequence imeVrha){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT idVrha FROM dvrh WHERE imeVrha LIKE ?",new String[]{imeVrha.toString()});
        cursor.moveToFirst();
        Integer result=cursor.getInt(0);
        return result;
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
    public Pot izpisPodrobnoPot(Integer idPot){

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursorPoti = db.rawQuery("SELECT * FROM dpot WHERE idPot=?",new String[]{idPot.toString()});
        cursorPoti.moveToFirst();
                Pot pot = new Pot();
                pot.setIdPot(Integer.parseInt(cursorPoti.getString(0)));
                pot.setImePoti(cursorPoti.getString(1));
                pot.setZahtevnost(cursorPoti.getString(2));
                pot.setCasHoje(cursorPoti.getString(3));
                pot.setIzhodisceLat(cursorPoti.getString(4));
                pot.setIzhodisceLong(cursorPoti.getString(5));
                pot.setOpisPoti(cursorPoti.getString(6));
                pot.setLink(cursorPoti.getString(7));
                pot.setIdVrha(Integer.parseInt(cursorPoti.getString(8)));
                pot.setIzhodisceDostop(cursorPoti.getString(9));
            return pot;

    }

    public ArrayList<Pot> izpisiPoti(Integer idVrha){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursorPoti = db.rawQuery("SELECT * FROM dpot WHERE idVrha=?",new String[]{idVrha.toString()});
        ArrayList<Pot> potiArrayList = new ArrayList<>();

        if(cursorPoti.moveToFirst()){
            do {
                Pot pot = new Pot();
                //cas opisPoti TEXT NOT NULL, link TEXT NOT NULL, idVrha INTEGER NOT NULL, izhodisceDostop TEXT NOT NULL, FOREIGN KEY(idVrha) REFERENCES dvrh(idVrha))");
                pot.setIdPot(Integer.parseInt(cursorPoti.getString(0)));
                pot.setImePoti(cursorPoti.getString(1));
                pot.setZahtevnost(cursorPoti.getString(2));
                pot.setCasHoje(cursorPoti.getString(3));
                pot.setIzhodisceLat(cursorPoti.getString(4));
                pot.setIzhodisceLong(cursorPoti.getString(5));
                pot.setOpisPoti(cursorPoti.getString(6));
                pot.setLink(cursorPoti.getString(7));
                pot.setIdVrha(Integer.parseInt(cursorPoti.getString(8)));
                pot.setIzhodisceDostop(cursorPoti.getString(9));


                potiArrayList.add(pot);
            } while (cursorPoti.moveToNext());
        }
        cursorPoti.close();
        return potiArrayList;
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
