package com.example.shop.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "report_purchase")
public class ReportPurchase {
    @Id
    @Column(name = "purchase_id")
    private Long purchaseId;

    @Column(name = "category_name")
    private String categoryName;

    private String description;
    private int count;

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "employee_firstname")
    private String employeeFirstName;

    @Column(name = "employee_lastname")
    private String employeeLastName;

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ReportPurchase() {
    }

    public ReportPurchase(Long purchaseId, String categoryName, String description, int count,
                          double totalPrice, String employeeFirstName, String employeeLastName) {
        this.purchaseId = purchaseId;
        this.categoryName = categoryName;
        this.description = description;
        this.count = count;
        this.totalPrice = totalPrice;
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
    }
}
