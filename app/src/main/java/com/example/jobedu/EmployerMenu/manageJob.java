package com.example.jobedu.EmployerMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.jobedu.Database.DatabaseHelper;
import com.example.jobedu.R;
import com.example.jobedu.adapter.MANAGEDadapter;
import com.example.jobedu.model.MANAGED;

import java.util.ArrayList;

public class manageJob extends AppCompatActivity {

    RecyclerView postedjoblist;
    RecyclerView.Adapter managedadapter;

    String employerid;
    String jobid, jobdesk, company, address, salary, type;
    ArrayList<MANAGED> manage = new ArrayList<>();

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_job);

        employerid = getIntent().getStringExtra("EmployerID");

        postedjoblist = findViewById(R.id.jobposted);
        postedjoblist.setHasFixedSize(true);
        postedjoblist.setLayoutManager(new LinearLayoutManager(this));

        db = new DatabaseHelper(this);
        Cursor c = db.getPOSTEDJOB(employerid);
        if (c.getCount()>0){
            if (c.moveToFirst()){
                do{
                    jobid = c.getString(0);
                    jobdesk = c.getString(1);
                    company = c.getString(2);
                    address = c.getString(3);
                    salary = c.getString(4);
                    type = c.getString(5);

                    MANAGED managedclass = new MANAGED(jobid, jobdesk, company, address, salary, type);
                    manage.add(managedclass);
                }while(c.moveToNext());
            }
        }else{
            Toast.makeText(manageJob.this, "There is no data in database", Toast.LENGTH_SHORT).show();
        }

        managedadapter = new MANAGEDadapter(manage,getApplicationContext(), employerid);
        postedjoblist.setAdapter(managedadapter);
    }


}
