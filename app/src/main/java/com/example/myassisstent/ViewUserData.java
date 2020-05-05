package com.example.myassisstent;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myassisstent.Adapter.UserAdapter;
import com.example.myassisstent.Database.MyDatabase;
import com.example.myassisstent.model.User;

import java.util.ArrayList;

public class ViewUserData extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    MyDatabase db;
    RecyclerView recyclerView;
    ArrayList<User> userArrayList;
    UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user_data);
        db = new MyDatabase(this);

        recyclerView = findViewById(R.id.user_view);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        userArrayList = db.showUserData("");
        if(userArrayList.isEmpty())
        {
            Toast.makeText(this, "ArrayList is empty!", Toast.LENGTH_SHORT).show();
        }
        userAdapter = new UserAdapter(this, userArrayList);
        recyclerView.setAdapter(userAdapter);
    }

    public void sortPopUp(View view) {
        PopupMenu popup = new PopupMenu(ViewUserData.this, view);
        popup.setOnMenuItemClickListener(ViewUserData.this);
        popup.inflate(R.menu.menu_sort_popup);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.name_menu:
                Toast.makeText(ViewUserData.this, "Name", Toast.LENGTH_SHORT).show();
                userArrayList = db.showUserData("name");
                userAdapter = new UserAdapter(this, userArrayList);
                recyclerView.setAdapter(userAdapter);
                return true;
            case R.id.dept_menu:
                Toast.makeText(ViewUserData.this, "Dept", Toast.LENGTH_SHORT).show();
                userArrayList = db.showUserData("department");
                userAdapter = new UserAdapter(this, userArrayList);
                recyclerView.setAdapter(userAdapter);
                return true;
            case R.id.project_menu:
                Toast.makeText(ViewUserData.this, "Project", Toast.LENGTH_SHORT).show();
                userArrayList = db.showUserData("project");
                userAdapter = new UserAdapter(this, userArrayList);
                recyclerView.setAdapter(userAdapter);
                return true;
            case R.id.perf_menu:
                Toast.makeText(ViewUserData.this, "Perf", Toast.LENGTH_SHORT).show();
                userArrayList = db.showUserData("performance");
                userAdapter = new UserAdapter(this, userArrayList);
                recyclerView.setAdapter(userAdapter);
                return true;
            default:
                return false;
        }
    }
}
