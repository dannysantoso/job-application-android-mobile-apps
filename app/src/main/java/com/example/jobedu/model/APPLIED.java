package com.example.jobedu.model;

public class APPLIED {

    String JobID;
    String JobDesk;
    String Company;
    String Address;
    String Salary;
    String Date;

    public String getJobID() {
        return JobID;
    }

    public void setJobID(String jobID) {
        JobID = jobID;
    }

    public String getJobDesk() {
        return JobDesk;
    }

    public void setJobDesk(String jobDesk) {
        JobDesk = jobDesk;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    String Status;

    public APPLIED(String jobID, String jobDesk, String company, String address, String salary, String status, String date) {
        JobID = jobID;
        JobDesk = jobDesk;
        Company = company;
        Address = address;
        Salary = salary;
        Status = status;
        Date = date;
    }
}
