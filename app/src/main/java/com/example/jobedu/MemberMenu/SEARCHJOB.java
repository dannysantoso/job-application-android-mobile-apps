package com.example.jobedu.MemberMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.jobedu.Database.DatabaseHelper;
import com.example.jobedu.R;
import com.example.jobedu.adapter.SEARCHadapter;
import com.example.jobedu.model.SEARCH;

import java.util.ArrayList;

public class SEARCHJOB extends AppCompatActivity {

    RecyclerView searchjoblist;
    RecyclerView.Adapter searchjobadapter;

    String memberid, searchdata;
    String jobid, jobdesk, company, address, salary, type, employerid;
    ArrayList<SEARCH> search = new ArrayList<>();

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchjob);

        memberid = getIntent().getStringExtra("MemberID");
        searchdata = getIntent().getStringExtra("searchdata");

        searchjoblist = findViewById(R.id.searchjoblist);
        searchjoblist.setHasFixedSize(true);
        searchjoblist.setLayoutManager(new LinearLayoutManager(this));

        db = new DatabaseHelper(this);
        Cursor c = db.getSEARCHJOB(searchdata);
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

                    SEARCH searchclass = new SEARCH(jobid, jobdesk, company, address, salary, type, employerid);
                    search.add(searchclass);
                }while(c.moveToNext());
            }
        }else{
            Toast.makeText(SEARCHJOB.this, "There is no data in database", Toast.LENGTH_SHORT).show();
        }

        searchjobadapter = new SEARCHadapter(search,getApplicationContext(), memberid);
        searchjoblist.setAdapter(searchjobadapter);
    }
}
