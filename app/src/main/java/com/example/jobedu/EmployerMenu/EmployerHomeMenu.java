package com.example.jobedu.EmployerMenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jobedu.MemberMenu.ITJOB;
import com.example.jobedu.MemberMenu.JOBTYPE;
import com.example.jobedu.R;

public class EmployerHomeMenu extends AppCompatActivity {

    String employerID;
    Button btnpostjob, btnmanagejob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_home_menu);

        employerID = getIntent().getStringExtra("EmployerID");

        btnpostjob = findViewById(R.id.btnpostjob);
        btnmanagejob = findViewById(R.id.btnmanage);

        btnpostjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmployerHomeMenu.this, postJob.class);
                intent.putExtra("EmployerID",employerID);
                startActivity(intent);
            }
        });

        btnmanagejob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmployerHomeMenu.this, manageJob.class);
                intent.putExtra("EmployerID",employerID);
                startActivity(intent);
            }
        });
    }
}

