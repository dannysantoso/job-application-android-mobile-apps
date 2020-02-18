package com.example.jobedu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobedu.Database.DatabaseHelper;
import com.example.jobedu.R;
import com.example.jobedu.model.COMMENT;

import java.util.ArrayList;

public class COMMENTadapter extends RecyclerView.Adapter<COMMENTadapter.MyViewHolder> {

    ArrayList<COMMENT> comment;

    Context context;

    DatabaseHelper db;

    String memberid, articleid;


    public COMMENTadapter(ArrayList<COMMENT> comment, Context context, String articleid, String memberid) {
        this.comment = comment;
        this.context = context;
        this.articleid = articleid;
        this.memberid = memberid;
    }

    @NonNull
    @Override
    public COMMENTadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull COMMENTadapter.MyViewHolder holder, int position) {
        db = new DatabaseHelper(context);
        COMMENT commentclass = comment.get(position);
        holder.Memberid.setText(commentclass.getMemberid());
        holder.Content.setText(commentclass.getContent());
    }

    @Override
    public int getItemCount() {
        return comment.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Memberid, Content;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Memberid = itemView.findViewById(R.id.memberid);
            Content = itemView.findViewById(R.id.content);
        }
    }
}
