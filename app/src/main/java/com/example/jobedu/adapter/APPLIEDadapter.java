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
import com.example.jobedu.model.APPLIED;

import java.util.ArrayList;

public class APPLIEDadapter extends RecyclerView.Adapter<APPLIEDadapter.MyViewHolder> {
    ArrayList<APPLIED> applied;

    Context context;

    DatabaseHelper db;

    String jobid, status;

    String memberid;



    public APPLIEDadapter(ArrayList<APPLIED> applied, Context context, String memberid) {
        this.applied = applied;
        this.context = context;
        this.memberid = memberid;
    }

    @NonNull
    @Override
    public APPLIEDadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.applied_list_data,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull APPLIEDadapter.MyViewHolder holder, final int position) {
        db = new DatabaseHelper(context);
        APPLIED appliedclass = applied.get(position);
        holder.JobID.setText(appliedclass.getJobID());
        holder.JobDesk.setText(appliedclass.getJobDesk());
        holder.Company.setText(appliedclass.getCompany());
        holder.Address.setText(appliedclass.getAddress());
        holder.Salary.setText(appliedclass.getSalary());
        holder.Status.setText(appliedclass.getStatus());
        holder.Date.setText(appliedclass.getDate());
        holder.cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jobid = applied.get(position).getJobID();
                status = applied.get(position).getStatus();
                Integer result = db.deleteData(jobid, memberid, status);
                if (result > 0){
                    applied.remove(applied.get(position));
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return applied.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView JobID, JobDesk, Company, Address, Salary, Status, Date;
        Button cancelbtn;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            JobID = itemView.findViewById(R.id.jobid);
            JobDesk = itemView.findViewById(R.id.jobdesk);
            Company = itemView.findViewById(R.id.company);
            Address = itemView.findViewById(R.id.address);
            Salary = itemView.findViewById(R.id.salary);
            Status = itemView.findViewById(R.id.status);
            Date = itemView.findViewById(R.id.date);
            cancelbtn = itemView.findViewById(R.id.cancelbutton);
        }
    }
}