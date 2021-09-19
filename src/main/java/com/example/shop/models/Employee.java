package com.example.shop.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="employee_id")
    private Long employeeId;

    @NotEmpty(message = "Firstname should not be empty")
    @Size(min = 2, max = 30, message = "Firstname size should be between 2 and 30")
    private String firstname;

    @NotEmpty(message = "Lastname should not be empty")
    @Size(min = 2, max = 30, message = "Lastname size should be between 2 and 30")
    private String lastname;

    @NotEmpty(message = "Rank should not be empty")
    private String rank;

    @NotEmpty(message = "Password should not be empty")
    private String password;

    @OneToMany(mappedBy ="employee", cascade = CascadeType.ALL)
    private List<Purchase> purchase = new ArrayList<>();

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Purchase> getPurshase() {
        return purchase;
    }

    public void setPurshase(List<Purchase> purchase) {
        this.purchase = purchase;
    }

    public Employee() {
    }

    public Employee(String firstname, String lastname, String rank, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.rank = rank;
        this.password = password;
    }
}