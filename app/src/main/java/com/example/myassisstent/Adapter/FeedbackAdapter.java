package com.example.myassisstent.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myassisstent.R;
import com.example.myassisstent.model.Feedback;
import com.example.myassisstent.model.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.FeedbackViewHolder> {

    Context context;
    ArrayList<Feedback> feedList;
    public FeedbackAdapter(Context context, ArrayList<Feedback> feedList) {
        this.context = context;
        this.feedList = feedList;
    }
    @NonNull
    @Override
    public FeedbackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.feedback_card,null,false);
        itemView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new FeedbackViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedbackViewHolder holder, int position) {
        Feedback fd = feedList.get(position);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dt = sdf.format(new Date());

        holder.fd_id.setText("Received at: " + dt);
        holder.emp_id.setText(fd.getEmp_ID());
        holder.adm_id.setText(fd.getAdmin_Email());
        holder.fd.setText(fd.getFeedback_Text());
    }

    @Override
    public int getItemCount() {
        return feedList.size();
    }

    static class FeedbackViewHolder extends RecyclerView.ViewHolder {
        TextView fd_id,emp_id,adm_id,fd;
        FeedbackViewHolder(@NonNull View itemView) {
            super(itemView);
            fd_id = itemView.findViewById(R.id.fd_id_fdc);
            emp_id = itemView.findViewById(R.id.emp_id_fdc);
            adm_id = itemView.findViewById(R.id.adm_email_fdc);
            fd = itemView.findViewById(R.id.fd_fdc);
        }
    }
}
