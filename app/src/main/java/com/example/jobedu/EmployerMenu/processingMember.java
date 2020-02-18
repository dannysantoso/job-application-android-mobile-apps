package com.example.jobedu.EmployerMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jobedu.Database.DatabaseHelper;
import com.example.jobedu.R;

public class processingMember extends AppCompatActivity {

    String memberid, membername, memberphone, jobid, employerid, status, company;
    TextView id, name, phone, memberstatus;
    EditText date;
    Button btninterview;
    DatabaseHelper db;

    SmsManager sms;
    int permission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processing_member);
        db = new DatabaseHelper(this);

        id = findViewById(R.id.memberid);
        name = findViewById(R.id.membername);
        phone = findViewById(R.id.memberphone);
        memberstatus = findViewById(R.id.memberstatus);
        btninterview = findViewById(R.id.btninterview);
        date = findViewById(R.id.date);

        memberid = getIntent().getStringExtra("memberid");
        membername = getIntent().getStringExtra("membername");
        memberphone = getIntent().getStringExtra("memberphone");
        jobid = getIntent().getStringExtra("jobid");
        employerid = getIntent().getStringExtra("employerid");
        status = getIntent().getStringExtra("status");
        company = getIntent().getStringExtra("company");

        id.setText(memberid);
        name.setText(membername);
        phone.setText(memberphone);
        memberstatus.setText(status);

        sms = SmsManager.getDefault();
        permission = ContextCompat.checkSelfPermission(processingMember.this, Manifest.permission.SEND_SMS);

        if(permission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(processingMember.this, new String[]{Manifest.permission.SEND_SMS},1);
        }

        btninterview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = db.updateStatus(memberid, jobid, status, date.getText().toString(), "interview");

                if (isUpdate){

                    sms.sendTextMessage(memberphone, null, "Congratulation you've got interview call in "+company+", in"+date.getText().toString(), null, null);

                    Intent intent = new Intent(processingMember.this, manageJob.class);
                    intent.putExtra("EmployerID",employerid);
                    startActivity(intent);
                }
            }
        });


    }
}
