package com.example.myassisstent.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myassisstent.R;
import com.example.myassisstent.model.User;


import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    Context context;
    ArrayList<User> userArrayList;
    public UserAdapter(Context context, ArrayList<User> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.user_card,null,false);
        itemView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User u1 = userArrayList.get(position);
        holder.name.setText("Name: " + u1.getName());
        holder.email.setText("Email: " + u1.getEmail());
        holder.password.setText("Pass: " + u1.getPassword());
        holder.mob_no.setText("Mob_No: " + u1.getMob_no());
        holder.dept.setText("Dept: " + u1.getDepartment());
        holder.project.setText("Project: " + u1.getProjectAssigned());
        holder.id.setText("ID: " + u1.getId());
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        TextView id,name,email,password,dept,project,mob_no;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_c);
            email = itemView.findViewById(R.id.email_c);
            password = itemView.findViewById(R.id.password_c);
            id = itemView.findViewById(R.id.u_id_c);
            dept = itemView.findViewById(R.id.dept_c);
            project = itemView.findViewById(R.id.project_c);
            mob_no = itemView.findViewById(R.id.mob_c);
        }
    }
}
