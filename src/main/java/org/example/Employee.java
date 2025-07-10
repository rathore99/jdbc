package org.example;

import java.sql.Date;

public class Employee {

    private int id;
    private String fisrtName;

    public Employee() {
    }

    private String lastName;
    private String email;
    private String department;
    private String mobileNumber;


    private double salary;
    private String jobId;
    private Date joiningDate;


    public String getJobId() {
        return jobId;
    }

    public Employee(int id, String fisrtName, String lastName, String email,  String mobileNumber,Date joiningDate,  String jobId,double salary, String department) {
        this.id = id;
        this.fisrtName = fisrtName;
        this.lastName = lastName;
        this.email = email;
        this.department = department;
        this.mobileNumber = mobileNumber;
        this.salary = salary;
        this.jobId = jobId;
        this.joiningDate = joiningDate;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFisrtName() {
        return fisrtName;
    }

    public void setFisrtName(String fisrtName) {
        this.fisrtName = fisrtName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public int getId() { return id; }

    public String getEmail() { return email; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", fisrtName='" + fisrtName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", department='" + department + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", salary=" + salary +
                ", jobId='" + jobId + '\'' +
                ", joiningDate=" + joiningDate +
                '}';
    }
}
