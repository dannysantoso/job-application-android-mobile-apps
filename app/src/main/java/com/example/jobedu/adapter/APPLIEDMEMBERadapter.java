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

import com.example.jobedu.Database.DatabaseHelper;
import com.example.jobedu.EmployerMenu.processingMember;
import com.example.jobedu.R;
import com.example.jobedu.model.MEMBER;

import java.util.ArrayList;

public class APPLIEDMEMBERadapter extends RecyclerView.Adapter<APPLIEDMEMBERadapter.MyViewHolder> {
    ArrayList<MEMBER> applied;

    Context context;

    DatabaseHelper db;

    String jobid;

    String employerid;
    String memberid, status, company;

    public APPLIEDMEMBERadapter(ArrayList<MEMBER> applied, Context context, String employerid, String jobid, String company) {
        this.applied = applied;
        this.context = context;
        this.employerid = employerid;
        this.jobid = jobid;
        this.company = company;
    }

    @NonNull
    @Override
    public APPLIEDMEMBERadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_applied_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull APPLIEDMEMBERadapter.MyViewHolder holder, final int position) {
        db = new DatabaseHelper(context);
        MEMBER memberclass = applied.get(position);
        holder.memberid.setText(memberclass.getMemberid());
        holder.name.setText(memberclass.getMembername());
        holder.phone.setText(memberclass.getMemberphone());
        holder.status.setText(memberclass.getStatus());
        holder.appliedmember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, processingMember.class);
                intent.putExtra("memberid", applied.get(position).getMemberid());
                intent.putExtra("membername", applied.get(position).getMembername());
                intent.putExtra("memberphone", applied.get(position).getMemberphone());
                intent.putExtra("status", applied.get(position).getStatus());
                intent.putExtra("employerid", employerid);
                intent.putExtra("jobid", jobid);
                intent.putExtra("company", company);
                context.startActivity(intent);
            }
        });
        holder.btnreject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memberid = applied.get(position).getMemberid();
                status = applied.get(position).getStatus();
                Integer result = db.rejectMember(memberid, jobid, status);
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
        TextView memberid, name, phone, status;
        Button btnreject;
        public LinearLayout appliedmember;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            memberid = itemView.findViewById(R.id.memberid);
            name = itemView.findViewById(R.id.membername);
            phone = itemView.findViewById(R.id.memberphone);
            appliedmember = itemView.findViewById(R.id.appliedmember);
            status = itemView.findViewById(R.id.status);
            btnreject = itemView.findViewById(R.id.btnreject);
        }
    }
}
