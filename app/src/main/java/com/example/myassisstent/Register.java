package com.example.myassisstent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myassisstent.Database.MyDatabase;
import com.example.myassisstent.model.User;


public class Register extends AppCompatActivity {

    EditText name,email,username,password,mob,dept;
    Button register;
    MyDatabase myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = findViewById(R.id._name_r);
        email = findViewById(R.id._email_r);
        username = findViewById(R.id._username_r);
        password = findViewById(R.id._password_r);
        register = findViewById(R.id._register_r);
        mob = findViewById(R.id._mob_r);
        dept = findViewById(R.id._dept_r);

        myDatabase = new MyDatabase(Register.this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User u1 = new User();
                u1.setName(name.getText().toString());
                u1.setEmail(email.getText().toString());
                u1.setUsername(username.getText().toString());
                u1.setPassword(password.getText().toString());
                u1.setMob_no(mob.getText().toString());
                u1.setDepartment(dept.getText().toString());
                myDatabase.insertUserData(u1);
                finishAfterTransition();
                Intent intent = new Intent(Register.this,Login.class);
                startActivity(intent);
            }
        });


    }
}
