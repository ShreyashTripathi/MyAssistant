package com.example.myassisstent.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.myassisstent.model.Feedback;

import java.util.ArrayList;

public class User_Feedback_Database extends SQLiteOpenHelper {

    Context context;
    private static final String TABLE_ = "User_Feedback";
    private static final String DB_ = "user_fd_db";
    private static final String FEEDBACK_KEY = "feedback" ;
    private static final String EMP_ID_KEY = "emp_id" ;
    private static final String FEEDBACK_ID_KEY = "fd_id" ;
    private static final String ADMIN_ID_KEY = "admin_id" ;

    public User_Feedback_Database(@Nullable Context context){
        super(context,DB_,null,5);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "Create table " +TABLE_+"( "+FEEDBACK_ID_KEY+" Text ,"+EMP_ID_KEY+" Text ,"+ADMIN_ID_KEY+" Text ,"+FEEDBACK_KEY+" Text )";
        sqLiteDatabase.execSQL(sql);
    }

    public void insertFeedback(Feedback fd)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(FEEDBACK_ID_KEY,fd.getFeedback_ID());
        cv.put(EMP_ID_KEY,fd.getEmp_ID());
        cv.put(FEEDBACK_KEY,fd.getFeedback_Text());
        cv.put(ADMIN_ID_KEY,fd.getAdmin_Email());
        long idd = db.insert(TABLE_,null,cv);
        Toast.makeText(context, "Feedback inserted: "+idd, Toast.LENGTH_SHORT).show();
    }

    public ArrayList<Feedback> getFeedback(String _emp_id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "Select * from "+TABLE_+" where "+EMP_ID_KEY+" = ?";
        Cursor cursor = db.rawQuery(sql,new String[]{_emp_id});
        ArrayList<Feedback> feedList = new ArrayList<>();
        if(cursor.moveToFirst())
        {
            do{
                Feedback fd = new Feedback();
                fd.setFeedback_ID(cursor.getString(cursor.getColumnIndex(FEEDBACK_ID_KEY)));
                fd.setEmp_ID(cursor.getString(cursor.getColumnIndex(EMP_ID_KEY)));
                fd.setAdmin_Email(cursor.getString(cursor.getColumnIndex(ADMIN_ID_KEY)));
                fd.setFeedback_Text(cursor.getString(cursor.getColumnIndex(FEEDBACK_KEY)));
                feedList.add(fd);
            }while(cursor.moveToNext());
        }
        if(feedList.isEmpty())
        {
            Toast.makeText(context, "Didn't got the feedback", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Got the feedback", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
        closeDatabase(db);
        return feedList;
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
