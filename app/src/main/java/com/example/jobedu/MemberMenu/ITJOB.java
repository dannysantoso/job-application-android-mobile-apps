package com.example.jobedu.MemberMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.jobedu.Database.DatabaseHelper;
import com.example.jobedu.R;
import com.example.jobedu.adapter.ITadapter;
import com.example.jobedu.model.IT;

import java.util.ArrayList;

public class ITJOB extends AppCompatActivity {

    RecyclerView itjoblist;
    RecyclerView.Adapter itjobadapter;

    String memberid;
    String jobid, jobdesk, company, address, salary, type, employerid;
    ArrayList<IT> it = new ArrayList<>();

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itjob);


        memberid = getIntent().getStringExtra("MemberID");


        itjoblist = findViewById(R.id.itjoblist);
        itjoblist.setHasFixedSize(true);
        itjoblist.setLayoutManager(new LinearLayoutManager(this));

        db = new DatabaseHelper(this);
        Cursor c = db.getITJOB();
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

                    IT itclass = new IT(jobid, jobdesk, company, address, salary, type, employerid);
                    it.add(itclass);
                }while(c.moveToNext());
            }
        }else{
            Toast.makeText(ITJOB.this, "There is no data in database", Toast.LENGTH_SHORT).show();
        }

        itjobadapter = new ITadapter(it,getApplicationContext(), memberid);
        itjoblist.setAdapter(itjobadapter);
    }
}
