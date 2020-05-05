package com.example.myassisstent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FirstPage extends AppCompatActivity {

    RadioGroup rg;
    RadioButton rb;
    Button b;
    String _role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        rg = findViewById(R.id.role);
        b = findViewById(R.id.role_button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId=rg.getCheckedRadioButtonId();

                rb = findViewById(selectedId);

                if(rb != null)
                    _role = rb.getText().toString();

                if(_role == null)
                {
                    Toast.makeText(FirstPage.this, "First select the role!", Toast.LENGTH_SHORT).show();
                }
                else if(_role.equals("Admin"))
                {
                    Intent i1 = new Intent(FirstPage.this,Admin_Login.class);
                    startActivity(i1);
                }
                else if(_role.equals("User"))
                {
                    Intent i2 = new Intent(FirstPage.this,Login.class);
                    startActivity(i2);
                }
            }
        });

    }

}
