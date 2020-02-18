package com.example.jobedu.model;

public class MANAGED {

    String jobid, jobdesk, company, address, salary, type;

    public MANAGED(String jobid, String jobdesk, String company, String address, String salary, String type) {
        this.jobid = jobid;
        this.jobdesk = jobdesk;
        this.company = company;
        this.address = address;
        this.salary = salary;
        this.type = type;
    }

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }

    public String getJobdesk() {
        return jobdesk;
    }

    public void setJobdesk(String jobdesk) {
        this.jobdesk = jobdesk;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
