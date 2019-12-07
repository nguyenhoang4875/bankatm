package com.ltm.entities;

public class Employee {
    private Integer id;
    private  String name;
    private String dob;
    private String department;

    public Employee() {
    }

    public Employee(Integer id, String name, String dob, String department) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.department = department;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
       return  String.join(" - ",name,dob,department);
    }
}
