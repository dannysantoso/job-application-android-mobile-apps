package com.example.jobedu.MemberMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jobedu.Database.DatabaseHelper;
import com.example.jobedu.R;
import com.example.jobedu.adapter.COMMENTadapter;
import com.example.jobedu.model.COMMENT;

import java.util.ArrayList;

public class detailArticle extends AppCompatActivity {

    String articleid, articletitle, articlecontent;
    TextView Articleid, Articletitle, Articlecontent;
    EditText etcomment;
    Button submit;

    RecyclerView commentlist;
    RecyclerView.Adapter commentadapter;

    String memberid, content;

    ArrayList<COMMENT> comment = new ArrayList<>();

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_article);

        Articleid = findViewById(R.id.articleid);
        Articletitle = findViewById(R.id.articletitle);
        Articlecontent = findViewById(R.id.articlecontent);
        etcomment = findViewById(R.id.comment);
        submit = findViewById(R.id.submit);


        articleid = getIntent().getStringExtra("ID");
        articletitle = getIntent().getStringExtra("Title");
        articlecontent = getIntent().getStringExtra("Content");
        memberid = getIntent().getStringExtra("MemberID");


        Articleid.setText(articleid);
        Articletitle.setText(articletitle);
        Articlecontent.setText(articlecontent);

        commentlist = findViewById(R.id.commentList);
        commentlist.setHasFixedSize(true);
        commentlist.setLayoutManager(new LinearLayoutManager(this));

        db = new DatabaseHelper(this);
        Cursor c = db.getComment(articleid);
        if (c.getCount()>0){
            if (c.moveToFirst()){
                do{
                    memberid = c.getString(0);
                    content = c.getString(1);

                    COMMENT commentclass = new COMMENT(memberid, content);
                    comment.add(commentclass);
                }while(c.moveToNext());
            }
        }else{
            Toast.makeText(detailArticle.this, "There is no data in database", Toast.LENGTH_SHORT).show();
        }

        commentadapter = new COMMENTadapter(comment,getApplicationContext(), articleid, memberid);
        commentlist.setAdapter(commentadapter);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = db.insertComment(etcomment.getText().toString(), memberid, articleid);
                if (result) {

                    Intent intent = new Intent(detailArticle.this, detailArticle.class);
                    intent.putExtra("ID", articleid);
                    intent.putExtra("Title", articletitle);
                    intent.putExtra("Content", articlecontent);
                    intent.putExtra("MemberID", memberid);
                    startActivity(intent);
                }
            }
        });
    }
}
