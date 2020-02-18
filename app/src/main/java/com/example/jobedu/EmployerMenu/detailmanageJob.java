package com.example.jobedu.EmployerMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jobedu.Database.DatabaseHelper;
import com.example.jobedu.R;
import com.example.jobedu.adapter.APPLIEDMEMBERadapter;
import com.example.jobedu.model.MEMBER;

import java.util.ArrayList;

public class detailmanageJob extends AppCompatActivity {

    RecyclerView appliedmember;
    RecyclerView.Adapter appliedmemberadapter;

    String jobid, jobdesk, company, address, salary, type, employerid, status;
    TextView Jobid, Jobdesk, Company, Address, Salary, Type;

    String memberid, name, phone;

    ArrayList<MEMBER> applied = new ArrayList<>();

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailmanage_job);


        Jobid = findViewById(R.id.jobid);
        Jobdesk = findViewById(R.id.jobdesk);
        Company = findViewById(R.id.company);
        Address = findViewById(R.id.address);
        Salary = findViewById(R.id.salary);
        Type = findViewById(R.id.type);

        jobid = getIntent().getStringExtra("JobID");
        jobdesk = getIntent().getStringExtra("Jobdesk");
        company = getIntent().getStringExtra("Company");
        address = getIntent().getStringExtra("Address");
        salary = getIntent().getStringExtra("Salary");
        type = getIntent().getStringExtra("Type");
        employerid = getIntent().getStringExtra("EmployerID");

        Jobid.setText(jobid);
        Jobdesk.setText(jobdesk);
        Company.setText(company);
        Address.setText(address);
        Salary.setText(salary);
        Type.setText(type);

        appliedmember = findViewById(R.id.appliedmember);
        appliedmember.setHasFixedSize(true);
        appliedmember.setLayoutManager(new LinearLayoutManager(this));

        db = new DatabaseHelper(this);
        Cursor c = db.getAppliedMember(jobid);
        if (c.getCount()>0){
            if (c.moveToFirst()){
                do{
                    memberid = c.getString(0);
                    name = c.getString(1);
                    phone = c.getString(2);
                    status = c.getString(3);


                    MEMBER memberclass = new MEMBER(memberid, name, phone, status);
                    applied.add(memberclass);
                }while(c.moveToNext());
            }
        }else{
            Toast.makeText(detailmanageJob.this, "There is no data in database", Toast.LENGTH_SHORT).show();
        }

        appliedmemberadapter = new APPLIEDMEMBERadapter(applied,getApplicationContext(), employerid, jobid, company);
        appliedmember.setAdapter(appliedmemberadapter);
    }
}
