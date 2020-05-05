package com.example.myassisstent.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;


import com.example.myassisstent.model.Admin;

import java.util.ArrayList;

public class Admin_Database extends SQLiteOpenHelper {
    Context context;
    public Admin_Database(@Nullable Context context) {
        super(context, "admin_db", null, 5);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "Create table AdminData(name,email,username,password)";
        sqLiteDatabase.execSQL(sql);
    }

    public void insertAdminData(Admin a1)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",a1.getName());
        cv.put("email",a1.getEmail());
        cv.put("username",a1.getUsername());
        cv.put("password",a1.getPassword());
        db.insert("AdminData",null, cv);
        Toast.makeText(context, "Data Inserted in Admin DB!", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<Admin> showAdminData()
    {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "Select * from AdminData";
        Cursor cursor = db.rawQuery(sql,null);
        ArrayList<Admin> userArrayList = new ArrayList<>();
        int flag_2 = 0;
        while(cursor.moveToNext())
        {
            flag_2 = 1;
            Admin a1 = new Admin();
            a1.setName(cursor.getString(0));
            a1.setEmail(cursor.getString(1));
            a1.setUsername(cursor.getString(2));
            a1.setPassword(cursor.getString(3));
            userArrayList.add(a1);
        }
        if(flag_2 == 0)
        {
            Toast.makeText(context, "No data found!", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
        closeDatabase(db);
        return  userArrayList;
    }

    public boolean searchAdmin(String _email,String _password)
    {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "Select * from AdminData where email = ? AND password = ?";
        String args[] = {_email,_password};
        Cursor cursor = db.rawQuery(sql,args);
        int flag = 0;
        while(cursor.moveToNext())
        {
            flag = 1;
        }
        if(flag == 1)
        {
            cursor.close();
            closeDatabase(db);
            return true;
        }

        cursor.close();
        closeDatabase(db);
        return false;
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    private void closeDatabase(SQLiteDatabase db) {
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
}
