package com.example.duan1nhom7qlkhachsan.Activity.hotro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class HoTroDAO {
    DbHelper dbHelper;
    public HoTroDAO(Context context){
        dbHelper=new DbHelper(context);
    }
    public ArrayList<HoTro>getlistDSNPP(){
        ArrayList<HoTro>list=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=dbHelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select*from NHAPHANPHOI",null);
        if (cursor.getCount()!=0){
            cursor.moveToFirst();
            do {
                list.add(new HoTro(cursor.getString(0),cursor.getString(1),cursor.getString(2)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public boolean themNPP(HoTro nhaPhanPhoi){
        SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("MaNPP",nhaPhanPhoi.getManpp());
        contentValues.put("TenNPP",nhaPhanPhoi.getTennpp());
        contentValues.put("GioiThieu",nhaPhanPhoi.getGioithieu());
        long check=sqLiteDatabase.insert("NHAPHANPHOI",null,contentValues);
        if (check==-1){
            return false;
        }else{
            return true;
        }
    }

    public boolean themNPPtest(HoTro nhaPhanPhoi){
        SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select*from NHAPHANPHOI where TenNPP=?",new String[]{nhaPhanPhoi.getTennpp()});
        if (cursor.getCount()!=0){
            return false;
        }else{
            ContentValues contentValues=new ContentValues();
            contentValues.put("MaNPP",nhaPhanPhoi.getManpp());
            contentValues.put("TenNPP",nhaPhanPhoi.getTennpp());
            contentValues.put("GioiThieu",nhaPhanPhoi.getGioithieu());
            long check=sqLiteDatabase.insert("NHAPHANPHOI",null,contentValues);
            if (check==-1){
                return false;
            }else{
                return true;
            }
        }

    }
}
