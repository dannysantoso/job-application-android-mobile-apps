package com.example.jobedu.MemberMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.jobedu.Database.DatabaseHelper;
import com.example.jobedu.R;
import com.example.jobedu.adapter.APPLIEDadapter;
import com.example.jobedu.model.APPLIED;

import java.util.ArrayList;

public class appliedList extends AppCompatActivity {

    RecyclerView appliedlist;
    RecyclerView.Adapter appliedadapter;

    public String memberid;
    String jobid, jobdesk, company, address, salary, status, date;
    ArrayList<APPLIED> applied = new ArrayList<>();

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applied_list);


        memberid = getIntent().getStringExtra("MemberID");


        appliedlist = findViewById(R.id.appliedlist);
        appliedlist.setHasFixedSize(true);
        appliedlist.setLayoutManager(new LinearLayoutManager(this));

        db = new DatabaseHelper(this);
        Cursor c = db.getAppliedJob(memberid);
        if (c.getCount()>0){
            if (c.moveToFirst()){
                do{
                    jobid = c.getString(0);
                    jobdesk = c.getString(1);
                    company = c.getString(2);
                    address = c.getString(3);
                    salary = c.getString(4);
                    status = c.getString(5);
                    date = c.getString(6);


                    APPLIED appliedclass = new APPLIED(jobid, jobdesk, company, address, salary, status, date);
                    applied.add(appliedclass);
                }while(c.moveToNext());
            }
        }else{
            Toast.makeText(appliedList.this, "There is no data in database", Toast.LENGTH_SHORT).show();
        }

        appliedadapter = new APPLIEDadapter(applied,getApplicationContext(), memberid);
        appliedlist.setAdapter(appliedadapter);
    }


}
