package com.example.jobedu.EmployerMenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jobedu.Database.DatabaseHelper;
import com.example.jobedu.R;

public class postJob extends AppCompatActivity {

    String employerid;
    EditText jobdesk, company, address, salary, type;
    Button post;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_job);
        db = new DatabaseHelper(this);


        employerid = getIntent().getStringExtra("EmployerID");
        jobdesk = findViewById(R.id.etJobDesk);
        company = findViewById(R.id.etCompany);
        address = findViewById(R.id.etAddress);
        salary = findViewById(R.id.etSalary);
        type = findViewById(R.id.etType);
        post = findViewById(R.id.btnPost);

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = db.insertJob(jobdesk.getText().toString(), company.getText().toString(), address.getText().toString(), salary.getText().toString(), type.getText().toString(), employerid);
                if (result) {

                    Toast.makeText(postJob.this, "Insertion Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(postJob.this, manageJob.class);
                    intent.putExtra("EmployerID",employerid);
                    startActivity(intent);
                }
            }
        });


    }

}
