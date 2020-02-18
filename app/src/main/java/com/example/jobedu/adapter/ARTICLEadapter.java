package com.example.jobedu.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobedu.Database.DatabaseHelper;
import com.example.jobedu.MemberMenu.detailArticle;
import com.example.jobedu.R;
import com.example.jobedu.model.ARTICLE;

import java.util.ArrayList;

public class ARTICLEadapter extends RecyclerView.Adapter<ARTICLEadapter.MyViewHolder> {

    ArrayList<ARTICLE> article;

    Context context;

    DatabaseHelper db;

    String memberid;

    public ARTICLEadapter(ArrayList<ARTICLE> article, Context context, String memberid) {
        this.article = article;
        this.context = context;
        this.memberid = memberid;
    }

    @NonNull
    @Override
    public ARTICLEadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ARTICLEadapter.MyViewHolder holder, final int position) {
        db = new DatabaseHelper(context);
        ARTICLE articleclass = article.get(position);
        holder.articleid.setText(articleclass.getArticleID());
        holder.articletitle.setText(articleclass.getArticletitle());
        holder.articlecontent.setText(articleclass.getArticlecontent());
        holder.articlelist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, detailArticle.class);
                intent.putExtra("ID", article.get(position).getArticleID());
                intent.putExtra("Title", article.get(position).getArticletitle());
                intent.putExtra("Content", article.get(position).getArticlecontent());
                intent.putExtra("MemberID", memberid);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return article.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView articleid, articletitle, articlecontent;
        public LinearLayout articlelist;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            articleid = itemView.findViewById(R.id.articleid);
            articletitle = itemView.findViewById(R.id.articletitle);
            articlecontent = itemView.findViewById(R.id.articlecontent);
            articlelist = itemView.findViewById(R.id.articleList);
        }
    }
}
