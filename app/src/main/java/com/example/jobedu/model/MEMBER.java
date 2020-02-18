package com.example.jobedu.model;

public class MEMBER {
    String memberid;
    String membername;
    String memberphone;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    String status;

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

    public String getMembername() {
        return membername;
    }

    public void setMembername(String membername) {
        this.membername = membername;
    }

    public String getMemberphone() {
        return memberphone;
    }

    public void setMemberphone(String memberphone) {
        this.memberphone = memberphone;
    }

    public MEMBER(String memberid, String membername, String memberphone, String status) {
        this.memberid = memberid;
        this.membername = membername;
        this.memberphone = memberphone;
        this.status = status;
    }
}
