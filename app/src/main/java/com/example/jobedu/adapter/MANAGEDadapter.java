package com.example.jobedu.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobedu.EmployerMenu.detailmanageJob;
import com.example.jobedu.EmployerMenu.updatePostJob;
import com.example.jobedu.R;
import com.example.jobedu.model.MANAGED;

import java.util.ArrayList;

public class MANAGEDadapter extends RecyclerView.Adapter<MANAGEDadapter.MyViewHolder> {

    ArrayList<MANAGED> manage;

    Context context;

    String jobid;

    String employerid;


    public MANAGEDadapter(ArrayList<MANAGED> manage, Context context, String employerid) {
        this.manage = manage;
        this.context = context;
        this.employerid = employerid;
    }

    @NonNull
    @Override
    public MANAGEDadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.jobposted_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MANAGEDadapter.MyViewHolder holder, final int position) {
        MANAGED managedclass = manage.get(position);
        holder.Jobid.setText(managedclass.getJobid());
        holder.Jobdesk.setText(managedclass.getJobdesk());
        holder.Company.setText(managedclass.getCompany());
        holder.Address.setText(managedclass.getAddress());
        holder.Salary.setText(managedclass.getSalary());
        holder.Type.setText(managedclass.getType());
        holder.jobposted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, detailmanageJob.class);
                intent.putExtra("JobID", manage.get(position).getJobid());
                intent.putExtra("Jobdesk", manage.get(position).getJobdesk());
                intent.putExtra("Company", manage.get(position).getCompany());
                intent.putExtra("Address", manage.get(position).getAddress());
                intent.putExtra("Salary", manage.get(position).getSalary());
                intent.putExtra("Type", manage.get(position).getType());
                intent.putExtra("EmployerID", employerid);
                context.startActivity(intent);
            }
        });
        holder.btnUpdateJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, updatePostJob.class);
                intent.putExtra("JobID", manage.get(position).getJobid());
                intent.putExtra("Jobdesk", manage.get(position).getJobdesk());
                intent.putExtra("Company", manage.get(position).getCompany());
                intent.putExtra("Address", manage.get(position).getAddress());
                intent.putExtra("Salary", manage.get(position).getSalary());
                intent.putExtra("Type", manage.get(position).getType());
                intent.putExtra("EmployerID", employerid);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return manage.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        Button btnUpdateJob;
        TextView Jobid, Jobdesk, Company, Address, Salary, Type;
        public LinearLayout jobposted;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                Jobid = itemView.findViewById(R.id.jobid);
                Jobdesk = itemView.findViewById(R.id.jobdesk);
                Company = itemView.findViewById(R.id.company);
                Address = itemView.findViewById(R.id.address);
                Salary = itemView.findViewById(R.id.salary);
                Type = itemView.findViewById(R.id.type);
                jobposted = itemView.findViewById(R.id.jobposted);
                btnUpdateJob = itemView.findViewById(R.id.btnupdatejob);
            }


    }
}
