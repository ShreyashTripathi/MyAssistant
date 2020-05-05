package com.example.myassisstent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.myassisstent.Adapter.FeedbackAdapter;
import com.example.myassisstent.Adapter.UserAdapter;
import com.example.myassisstent.Database.MyDatabase;
import com.example.myassisstent.Database.User_Feedback_Database;
import com.example.myassisstent.model.Feedback;

import java.util.ArrayList;

public class ReceiveFeedback extends AppCompatActivity {

    RecyclerView rv;
    User_Feedback_Database fd_db;
    ArrayList<Feedback> fdList;
    FeedbackAdapter fdAdapter;
    MyDatabase m_db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_feedback);
        rv = findViewById(R.id.fd_recycler_view);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        String _emp_sp = SaveSharedPreference.getUserName(ReceiveFeedback.this);
        int n = _emp_sp.length();
        String _email = _emp_sp.substring(0,n-4);
        m_db = new MyDatabase(ReceiveFeedback.this);
        String _emp_id = m_db.getEmpID(_email);

        fd_db = new User_Feedback_Database(ReceiveFeedback.this);
        //fdList = new ArrayList<>();
        fdList = fd_db.getFeedback(_emp_id);
        if(fdList.isEmpty())
        {
            Toast.makeText(this, "ArrayList is empty!", Toast.LENGTH_SHORT).show();
        }
        fdAdapter = new FeedbackAdapter(ReceiveFeedback.this,fdList);
        rv.setAdapter(fdAdapter);
    }
}
