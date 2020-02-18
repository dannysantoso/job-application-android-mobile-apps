package com.example.jobedu.MemberMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.jobedu.Database.DatabaseHelper;
import com.example.jobedu.R;
import com.example.jobedu.adapter.DESIGNadapter;
import com.example.jobedu.model.DESIGN;

import java.util.ArrayList;

public class DESIGNJOB extends AppCompatActivity {

    RecyclerView designjoblist;
    RecyclerView.Adapter designjobadapter;

    String memberid;
    String jobid, jobdesk, company, address, salary, type, employerid;
    ArrayList<DESIGN> design = new ArrayList<>();

    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designjob);

        memberid = getIntent().getStringExtra("MemberID");

        designjoblist = findViewById(R.id.designjoblist);
        designjoblist.setHasFixedSize(true);
        designjoblist.setLayoutManager(new LinearLayoutManager(this));

        db = new DatabaseHelper(this);
        Cursor c = db.getDESIGNJOB();
        if (c.getCount()>0){
            if (c.moveToFirst()){
                do{
                    jobid = c.getString(0);
                    jobdesk = c.getString(1);
                    company = c.getString(2);
                    address = c.getString(3);
                    salary = c.getString(4);
                    type = c.getString(5);
                    employerid = c.getString(6);

                    DESIGN designclass = new DESIGN(jobid, jobdesk, company, address, salary, type, employerid);
                    design.add(designclass);
                }while(c.moveToNext());
            }
        }else{
            Toast.makeText(DESIGNJOB.this, "There is no data in database", Toast.LENGTH_SHORT).show();
        }

        designjobadapter = new DESIGNadapter(design,getApplicationContext(), memberid);
        designjoblist.setAdapter(designjobadapter);
    }
}
