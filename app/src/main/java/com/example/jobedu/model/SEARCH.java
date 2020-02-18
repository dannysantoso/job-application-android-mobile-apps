package com.example.jobedu.model;

public class SEARCH {

    String jobid, jobdesk, company, address, salary, type, employerid;

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

    public String getEmployerid() {
        return employerid;
    }

    public void setEmployerid(String employerid) {
        this.employerid = employerid;
    }

    public SEARCH(String jobid, String jobdesk, String company, String address, String salary, String type, String employerid) {
        this.jobid = jobid;
        this.jobdesk = jobdesk;
        this.company = company;
        this.address = address;
        this.salary = salary;
        this.type = type;
        this.employerid = employerid;
    }
}
