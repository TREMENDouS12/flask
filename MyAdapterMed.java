package com.example.navscrreens;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterMed extends RecyclerView.Adapter<MyAdapterMed.MyViewHolder>{
    Context context;
    ArrayList<MedRec> MedRecArrayList;

    public MyAdapterMed(Context context, ArrayList<MedRec> medRecArrayList) {
        this.context = context;
        MedRecArrayList = medRecArrayList;
    }

    @NonNull
    @Override
    public MyAdapterMed.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.medrecview,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterMed.MyViewHolder holder, int position) {

        MedRec MedRec = MedRecArrayList.get(position);

        holder.date.setText(MedRec.Date);
        holder.illness.setText(MedRec.Illness);
        holder.practitioner.setText(MedRec.Practitioner);
        holder.prescribedMed.setText(MedRec.PrescribedMedicine);
        holder.studEmail.setText(MedRec.StudentEmail);

    }

    @Override
    public int getItemCount() {
        return MedRecArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView date, illness, practitioner, prescribedMed, studEmail;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.tvDate);
            illness = itemView.findViewById(R.id.tvIllness);
            practitioner = itemView.findViewById(R.id.tvPractitioner);
            prescribedMed = itemView.findViewById(R.id.tvPrescribedMed);
            studEmail = itemView.findViewById(R.id.tvStudEmail);

        }
    }
}