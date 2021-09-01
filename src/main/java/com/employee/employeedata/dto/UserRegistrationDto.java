package com.employee.employeedata.dto;

public class UserRegistrationDto {
    private String id;
    private String name;
    private String designation;
    private String password;
    
    public UserRegistrationDto(String id, String name, String designation, String password) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.password = password;
    }
    public UserRegistrationDto() {
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
}
