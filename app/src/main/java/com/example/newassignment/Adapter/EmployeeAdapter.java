package com.example.newassignment.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newassignment.ModelList.EmployeList;
import com.example.newassignment.R;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder>
{
    Context context;
    List<EmployeList> list;

    public EmployeeAdapter(Context context, List<EmployeList> list)
    {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new EmployeeAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_employeeist, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.txt_userId.setText("Id :- "+list.get(position).getId());
        holder.txt_username.setText(list.get(position).getEmployee_name());
        holder.txt_userage.setText("Age :- "+list.get(position).getEmployee_age());
        holder.txt_userSalary.setText("Salary :- "+list.get(position).getEmployee_salary());
        Glide.with(context).load(list.get(position).getProfile_image()).placeholder(R.drawable.noimage).into(holder.iv_userimage);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txt_username,txt_userId,txt_userage,txt_userSalary;
        ImageView iv_userimage;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            txt_username=itemView.findViewById(R.id.txt_username);
            txt_userId=itemView.findViewById(R.id.txt_userId);
            txt_userage=itemView.findViewById(R.id.txt_userage);
            txt_userSalary=itemView.findViewById(R.id.txt_userSalary);
            iv_userimage=itemView.findViewById(R.id.iv_userimage);
        }
    }
}
