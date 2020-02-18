package com.example.jobedu.model;

public class COMMENT {
    String memberid, content;

    public COMMENT(String memberid, String content) {
        this.memberid = memberid;
        this.content = content;
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
