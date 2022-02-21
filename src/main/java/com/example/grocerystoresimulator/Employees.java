package com.example.grocerystoresimulator;

public class Employees {

    int eid; String name; int contactNo;
    String dateOfBirth, gender, bloogGrp, address; int salary;

    public void setEid(int eid) {
        this.eid = eid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContactNo(int contactNo) {
        this.contactNo = contactNo;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBloogGrp(String bloogGrp) {
        this.bloogGrp = bloogGrp;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public int getEid() {
        return eid;
    }

    public String getName() {
        return name;
    }

    public int getContactNo() {
        return contactNo;
    }

    public String getGender() {
        return gender;
    }

    public String getBloogGrp() {
        return bloogGrp;
    }

    public String getAddress() {
        return address;
    }

    public int getSalary() {
        return salary;
    }

    public String getHireDate() {
        return hireDate;
    }

    String hireDate;

    Employees(int eid,String name,int contactNo,String dOfbirth,String gender,String bloogGrp,String address,int salary,String hireDate) {
        this.eid=eid;
        this.name=name;
        this.contactNo=contactNo;
        this.dateOfBirth=dOfbirth;
        this.gender=gender;
        this.bloogGrp=bloogGrp;
        this.address=address;
        this.salary=salary;
        this.hireDate=hireDate;
    }

}
