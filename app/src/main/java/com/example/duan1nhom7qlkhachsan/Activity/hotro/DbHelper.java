package com.example.duan1nhom7qlkhachsan.Activity.hotro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context){
        super(context,"NhaPhanPhoi",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="Create table NHAPHANPHOI (MaNPP text primary key, TenNPP text, GioiThieu text)";
        sqLiteDatabase.execSQL(sql);

        String insnpp="insert into NHAPHANPHOI values('Ho và tên: ','Số điện thoại: ','Nội Dung: ')";
        sqLiteDatabase.execSQL(insnpp);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists NHAPHANPHOI");
        onCreate(sqLiteDatabase);

    }
}
