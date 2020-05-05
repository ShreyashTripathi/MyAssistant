package com.example.myassisstent.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.service.autofill.UserData;
import android.widget.Toast;

import androidx.annotation.Nullable;


import com.example.myassisstent.model.User;

import java.util.ArrayList;

public class MyDatabase extends SQLiteOpenHelper {

    Context context;
    public MyDatabase(@Nullable Context context) {
        super(context, "user_db", null, 5);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "Create table UserData(u_id integer primary key autoincrement ,name Text,email Text,username Text,password Text,department Text, project Text, performance Text, mob_no Text, id_seq TEXT)";
        sqLiteDatabase.execSQL(sql);
    }

    public void insertUserData(User u1)
    {
        String email_;
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("name", u1.getName());
            cv.put("email", u1.getEmail());
            cv.put("username", u1.getUsername());
            cv.put("password", u1.getPassword());
            cv.put("department", u1.getDepartment());
            cv.put("mob_no", u1.getMob_no());
            cv.put("project",u1.getProjectAssigned());
            cv.put("performance",u1.getPerformance()+"");
            cv.put("id_seq","");
            email_ = u1.getEmail();
            long idd = db.insert("UserData", null, cv);
            updateUser(idd,email_);
            Toast.makeText(context, "Data Inserted _ Id: "+idd, Toast.LENGTH_LONG).show();

    }


    public void updateUser(long idd,String email_)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id_seq","EMP_"+idd);
        db.update("UserData",cv,"email = ?",new String[]{email_});
    }
    public ArrayList<User> showUserData(String order_col)
    {
        String sql;
        SQLiteDatabase db = this.getReadableDatabase();
        if(order_col != "")
            sql = "Select * from UserData order by "+order_col+" ASC";
        else
            sql = "Select * from UserData";
        Cursor cursor = db.rawQuery(sql,null);
        ArrayList<User> userArrayList = new ArrayList<>();
        int flag_2 = 0;
        if(cursor.moveToFirst()) {
            do {
                flag_2 = 1;
                User u1 = new User();
                u1.setId(cursor.getString(cursor.getColumnIndex("id_seq")));
                u1.setName(cursor.getString(cursor.getColumnIndex("name")));
                u1.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                u1.setUsername(cursor.getString(cursor.getColumnIndex("username")));
                u1.setPassword(cursor.getString(cursor.getColumnIndex("password")));
                u1.setDepartment(cursor.getString(cursor.getColumnIndex("department")));
                u1.setMob_no(cursor.getString(cursor.getColumnIndex("mob_no")));
                u1.setProjectAssigned(cursor.getString(cursor.getColumnIndex("project")));
                userArrayList.add(u1);

            } while (cursor.moveToNext());
        }
        if(flag_2 == 0)
        {
            Toast.makeText(context, "No data found!", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
        closeDatabase(db);
        return  userArrayList;
    }

    public boolean searchUser(String username,String password)
    {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "Select * from UserData where email = ? AND password = ?";
        String args[] = {username,password};
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

    public String getEmpID(String _email)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "Select * from UserData where email = ?";
        Cursor cursor = db.rawQuery(sql,new String[]{_email});
        String _emp_id = "";
        if(cursor.moveToFirst())
        {
            _emp_id = cursor.getString(cursor.getColumnIndex("id_seq"));
        }
        if(_emp_id.equals(""))
        {
            //Toast.makeText(context, "Emp_id not found for the given email!", Toast.LENGTH_SHORT).show();
        }

        cursor.close();
        closeDatabase(db);
        return _emp_id;
    }
    public User searchUser(String e_id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "Select * from UserData where id_seq = ?";
        String args[] = {e_id};
        Cursor cursor = db.rawQuery(sql,args);
        int flag = 0;
        User u1 = null;
        if(cursor.moveToFirst())
        {
            flag = 1;
            u1 = new User();
            u1.setName(cursor.getString(cursor.getColumnIndex("name")));
            u1.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            u1.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            u1.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            u1.setDepartment(cursor.getString(cursor.getColumnIndex("department")));
            u1.setMob_no(cursor.getString(cursor.getColumnIndex("mob_no")));
            u1.setProjectAssigned(cursor.getString(cursor.getColumnIndex("project")));
        }
        if(flag == 1)
        {
            cursor.close();
            closeDatabase(db);
            return u1;
        }
        cursor.close();
        closeDatabase(db);
        return null;
    }

    public void updateUser(String u_id,String project_)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("project",project_);
        db.update("UserData",cv,"id_seq = ?",new String[]{u_id});
        Toast.makeText(context, "Project Updated!", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS UserData");
    }
    private void closeDatabase(SQLiteDatabase db) {
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
}
