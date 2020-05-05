package com.example.myassisstent;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class User_Page extends AppCompatActivity {

    Button logout,v_fd_bt;
    String _email,_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__page);



        logout = findViewById(R.id._logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearUserName(User_Page.this);
                goToMainActivity();
            }
        });

        v_fd_bt = findViewById(R.id.view_fd);
        v_fd_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(User_Page.this,ReceiveFeedback.class);
                startActivity(i);
            }
        });
    }

    public void goToMainActivity()
    {
        finishAfterTransition();
        Intent i = new Intent(User_Page.this,Login.class);
        startActivity(i);
    }
    public static void clearUserName(Context ctx)
    {
        SharedPreferences.Editor editor = SaveSharedPreference.getSharedPreferences(ctx).edit();
        editor.clear(); //clear all stored data
        editor.apply();
    }
}
