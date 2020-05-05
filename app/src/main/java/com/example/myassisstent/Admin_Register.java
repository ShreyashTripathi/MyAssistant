package com.example.myassisstent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myassisstent.Database.Admin_Database;
import com.example.myassisstent.model.Admin;


public class Admin_Register extends AppCompatActivity {

    EditText name,email,username,password;
    Button register;
    Admin_Database admin_database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__register);
        name = findViewById(R.id.a_name_r);
        email = findViewById(R.id.a_email_r);
        username = findViewById(R.id.a_username_r);
        password = findViewById(R.id.a_password_r);
        register = findViewById(R.id.a_register_r);

        admin_database = new Admin_Database(getApplicationContext());

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Admin a1 = new Admin();
                a1.setName(name.getText().toString());
                a1.setEmail(email.getText().toString());
                a1.setUsername(username.getText().toString());
                a1.setPassword(password.getText().toString());
                admin_database.insertAdminData(a1);
                finishAfterTransition();
                Intent intent = new Intent(Admin_Register.this,Admin_Login.class);
                startActivity(intent);
            }
        });


    }
}
