package com.example.jobedu.MemberMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.jobedu.Database.DatabaseHelper;
import com.example.jobedu.R;
import com.example.jobedu.adapter.ARTICLEadapter;
import com.example.jobedu.model.ARTICLE;

import java.util.ArrayList;

public class articleList extends AppCompatActivity {

    RecyclerView articlelist;
    RecyclerView.Adapter articleadapter;

    public String memberid;
    String articleid, articletitle, articlecontent;
    ArrayList<ARTICLE> article = new ArrayList<>();

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);

        memberid = getIntent().getStringExtra("MemberID");

        articlelist = findViewById(R.id.articleList);
        articlelist.setHasFixedSize(true);
        articlelist.setLayoutManager(new LinearLayoutManager(this));

        db = new DatabaseHelper(this);
        Cursor c = db.getArticle();
        if (c.getCount()>0){
            if (c.moveToFirst()){
                do{
                    articleid = c.getString(0);
                    articletitle = c.getString(1);
                    articlecontent = c.getString(2);

                    ARTICLE articleclass = new ARTICLE(articleid, articletitle, articlecontent);
                    article.add(articleclass);
                }while(c.moveToNext());
            }
        }else{
            Toast.makeText(articleList.this, "There is no data in database", Toast.LENGTH_SHORT).show();
        }

        articleadapter = new ARTICLEadapter(article,getApplicationContext(), memberid);
        articlelist.setAdapter(articleadapter);
    }
}
