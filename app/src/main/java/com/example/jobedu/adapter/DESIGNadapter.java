package com.example.jobedu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobedu.Database.DatabaseHelper;
import com.example.jobedu.R;
import com.example.jobedu.model.DESIGN;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DESIGNadapter extends RecyclerView.Adapter<DESIGNadapter.MyViewHolder>  {
    ArrayList<DESIGN> design;

    Context context;

    DatabaseHelper db;

    String jobid;

    String memberid;

    public DESIGNadapter(ArrayList<DESIGN> design, Context context, String memberid) {
        this.design = design;
        this.context = context;
        this.memberid = memberid;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.designjob_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        db = new DatabaseHelper(context);
        DESIGN designclass = design.get(position);
        holder.Jobid.setText(designclass.getJobid());
        holder.Jobdesk.setText(designclass.getJobdesk());
        holder.Company.setText(designclass.getCompany());
        holder.Address.setText(designclass.getAddress());
        holder.Salary.setText(designclass.getSalary());
        holder.Type.setText(designclass.getType());
        holder.Employerid.setText(designclass.getEmployerid());
        holder.applybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jobid = design.get(position).getJobid();
                boolean result = db.applyJob(jobid, memberid, "waiting", getDate());
                if (result) {
                    design.remove(design.get(position));
                    notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return design.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Jobid, Jobdesk, Company, Address, Salary, Type, Employerid;
        Button applybutton;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Jobid = itemView.findViewById(R.id.jobid);
            Jobdesk = itemView.findViewById(R.id.jobdesk);
            Company = itemView.findViewById(R.id.company);
            Address = itemView.findViewById(R.id.address);
            Salary = itemView.findViewById(R.id.salary);
            Type = itemView.findViewById(R.id.type);
            Employerid = itemView.findViewById(R.id.employerid);
            applybutton = itemView.findViewById(R.id.applyButton);
        }
    }

    public String getDate(){

        DateFormat df = new SimpleDateFormat("dd/MM/yy");
        Date dateobj = new Date();
        String currentdate = df.format(dateobj);
        return currentdate;
    }
}
