package com.example.jobedu.model;

public class ARTICLE {

    String articleID, articletitle, articlecontent;

    public ARTICLE(String articleID, String articletitle, String articlecontent) {
        this.articleID = articleID;
        this.articletitle = articletitle;
        this.articlecontent = articlecontent;
    }

    public String getArticleID() {
        return articleID;
    }

    public void setArticleID(String articleID) {
        this.articleID = articleID;
    }

    public String getArticletitle() {
        return articletitle;
    }

    public void setArticletitle(String articletitle) {
        this.articletitle = articletitle;
    }

    public String getArticlecontent() {
        return articlecontent;
    }

    public void setArticlecontent(String articlecontent) {
        this.articlecontent = articlecontent;
    }
}
