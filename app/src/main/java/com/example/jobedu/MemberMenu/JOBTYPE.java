package com.example.jobedu.MemberMenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jobedu.R;

public class JOBTYPE extends AppCompatActivity {

    EditText etsearch;
    Button it, design, article, applied, search;
    String memberid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobtype);

        memberid = getIntent().getStringExtra("MemberID");

        it = findViewById(R.id.btnit);
        design = findViewById(R.id.btndesign);
        article = findViewById(R.id.btnarticle);
        applied = findViewById(R.id.btnapplied);
        search = findViewById(R.id.btnsearch);
        etsearch = findViewById(R.id.etsearch);

        it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JOBTYPE.this, ITJOB.class);
                intent.putExtra("MemberID",memberid);
                startActivity(intent);
            }
        });

        design.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JOBTYPE.this,DESIGNJOB.class);
                intent.putExtra("MemberID",memberid);
                startActivity(intent);
            }
        });

        article.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JOBTYPE.this, articleList.class);
                intent.putExtra("MemberID",memberid);
                startActivity(intent);
            }
        });

        applied.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JOBTYPE.this, appliedList.class);
                intent.putExtra("MemberID",memberid);
                startActivity(intent);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JOBTYPE.this, SEARCHJOB.class);
                intent.putExtra("MemberID",memberid);
                intent.putExtra("searchdata",etsearch.getText().toString());
                startActivity(intent);
            }
        });


    }
}
