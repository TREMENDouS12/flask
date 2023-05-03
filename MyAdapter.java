package com.example.navscrreens;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<User> userArrayList;

    public MyAdapter(Context context, ArrayList<User> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

        User user = userArrayList.get(position);

        holder.useremail.setText(user.UserEmail);
        holder.campus.setText(user.Campus);
        holder.date.setText(user.Date);
        holder.time.setText(user.Time);


    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView useremail,campus,date,time;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            useremail=itemView.findViewById(R.id.tvuseremail);
            campus=itemView.findViewById(R.id.tvCampus);
            date=itemView.findViewById(R.id.tvdate);
            time=itemView.findViewById(R.id.tvtime);

        }
    }
}
