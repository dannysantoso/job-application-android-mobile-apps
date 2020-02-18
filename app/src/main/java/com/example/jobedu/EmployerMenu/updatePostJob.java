package com.example.jobedu.EmployerMenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jobedu.Database.DatabaseHelper;
import com.example.jobedu.R;

public class updatePostJob extends AppCompatActivity {

    String employerid, jobid;
    EditText jobdesk, company, address, salary, type;
    Button update;
    TextView Jdesk,Company,Address,Salary,Type;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_post_job);
        db = new DatabaseHelper(this);

        employerid = getIntent().getStringExtra("EmployerID");
        jobid = getIntent().getStringExtra("JobID");



        jobdesk = findViewById(R.id.etJobDesk);
        company = findViewById(R.id.etCompany);
        address = findViewById(R.id.etAddress);
        salary = findViewById(R.id.etSalary);
        type = findViewById(R.id.etType);
        update = findViewById(R.id.btnUpdate);

        Jdesk = findViewById(R.id.tvjobdesk);
        Company = findViewById(R.id.tvcompany);
        Address = findViewById(R.id.tvaddress);
        Salary = findViewById(R.id.tvsalary);
        Type = findViewById(R.id.tvtype);

        Jdesk.setText(getIntent().getStringExtra("Jobdesk"));
        Company.setText(getIntent().getStringExtra("Company"));
        Address.setText(getIntent().getStringExtra("Address"));
        Salary.setText(getIntent().getStringExtra("Salary"));
        Type.setText(getIntent().getStringExtra("Type"));


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = db.updateJobPost(jobid, jobdesk.getText().toString(), company.getText().toString(), address.getText().toString(), salary.getText().toString(), type.getText().toString());
                if (isUpdate){
                    Intent intent = new Intent(updatePostJob.this, manageJob.class);
                    intent.putExtra("EmployerID",employerid);
                    startActivity(intent);
                }
            }
        });


    }
}
