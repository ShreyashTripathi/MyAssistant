package com.example.myassisstent;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myassisstent.Database.MyDatabase;
import com.example.myassisstent.Database.User_Feedback_Database;
import com.example.myassisstent.model.Feedback;
import com.example.myassisstent.model.User;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Admin_SendFeedback extends AppCompatActivity {

    EditText e_id,feed_et;
    MyDatabase myDatabase;
    TextView n,e,d,p,m,e_h;
    Button go,send_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__send_feedback);
        e_id = findViewById(R.id.e_id_a_f);
        n = findViewById(R.id.name_a_f);
        e = findViewById(R.id.email_a_f);
        d = findViewById(R.id.dept_a_f);
        p = findViewById(R.id.project_a_f);
        m = findViewById(R.id.mob_a_f);
        e_h = findViewById(R.id.e_heading_a_f);
        go = findViewById(R.id.go_button_a_f);
        feed_et = findViewById(R.id.feedback_a_f);
        send_button = findViewById(R.id.send_button);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDatabase = new MyDatabase(Admin_SendFeedback.this);
                User searchRes = myDatabase.searchUser(e_id.getText().toString());
                if(searchRes == null)
                {
                    Toast.makeText(Admin_SendFeedback.this, "No User Found while Searching!", Toast.LENGTH_SHORT).show();
                    n.setText("");
                    e.setText("");
                    d.setText("");
                    p.setText("");
                    m.setText("");
                    e_h.setText("No employee found");
                }
                else
                {
                    Toast.makeText(Admin_SendFeedback.this, "User Found while Searching!", Toast.LENGTH_SHORT).show();
                    n.setText(searchRes.getName());
                    e.setText(searchRes.getEmail());
                    d.setText(searchRes.getDepartment());
                    p.setText(searchRes.getProjectAssigned());
                    m.setText(searchRes.getMob_no());
                    e_h.setText("Employee's Details");
                }

            }
        });
        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendFeedbackToEmp();
            }
        });

    }



    public void sendFeedbackToEmp() {
        User_Feedback_Database u_f_d_b = new User_Feedback_Database(Admin_SendFeedback.this);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        String dt = sdf.format(new Date());

        dt = dt + "_" + e_id.getText().toString();

        Feedback fd = new Feedback();
        fd.setEmp_ID(e_id.getText().toString());
        fd.setFeedback_Text(feed_et.getText().toString());
        fd.setFeedback_ID(dt);
        String admin_email = SaveSharedPreference.getUserName(Admin_SendFeedback.this);
        int len = admin_email.length();
        admin_email = admin_email.substring(0,len-6);
        fd.setAdmin_Email(admin_email);
        u_f_d_b.insertFeedback(fd);
        finishAfterTransition();
        Intent i1 = new Intent(Admin_SendFeedback.this,Admin_Page.class);
        startActivity(i1);
    }
}
