package com.example.myassisstent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myassisstent.Database.MyDatabase;
import com.example.myassisstent.model.User;

import org.w3c.dom.Text;

public class Admin_Assign_Project extends FragmentActivity implements CustomFragment.DialogListener {

    EditText e_id;
    MyDatabase myDatabase;
    TextView n,e,d,p,m,e_h;
    String project_assigned;
    Button go;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__assign__project);
        e_id = findViewById(R.id.e_id_p);
        n = findViewById(R.id.name_e_p);
        e = findViewById(R.id.email_e_p);
        d = findViewById(R.id.dept_e_p);
        p = findViewById(R.id.project_e_p);
        m = findViewById(R.id.mob_e_p);
        e_h = findViewById(R.id.e_heading_p);
        go = findViewById(R.id.go_button);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDatabase = new MyDatabase(Admin_Assign_Project.this);
                User searchRes = myDatabase.searchUser(e_id.getText().toString());
                if(searchRes == null)
                {
                    Toast.makeText(Admin_Assign_Project.this, "No User Found while Searching!", Toast.LENGTH_SHORT).show();
                    n.setText("");
                    e.setText("");
                    d.setText("");
                    p.setText("");
                    m.setText("");
                    e_h.setText("No employee found");
                }
                else
                {
                    Toast.makeText(Admin_Assign_Project.this, "User Found while Searching!", Toast.LENGTH_SHORT).show();
                    n.setText(searchRes.getName());
                    e.setText(searchRes.getEmail());
                    d.setText(searchRes.getDepartment());
                    p.setText(searchRes.getProjectAssigned());
                    m.setText(searchRes.getMob_no());
                    e_h.setText("Employee's Details");
                }

            }
        });

    }

    public void cardClicked(View view) {

        DialogFragment dialogFragment = new CustomFragment();

        Bundle bundle = new Bundle();
        bundle.putBoolean("notAlertDialog", true);

        dialogFragment.setArguments(bundle);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        dialogFragment.show(ft, "dialog");
    }
    public void onFinishEditDialog(String inputText) {
        project_assigned = inputText;
        myDatabase.updateUser(e_id.getText().toString(),project_assigned);
        p.setText(project_assigned);
    }

    public void backPage(View view) {
        finishAfterTransition();
        Intent intent = new Intent(Admin_Assign_Project.this,Admin_Page.class);
        startActivity(intent);
    }
}
