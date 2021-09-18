package com.example.shop.models;

import javax.persistence.*;

@Entity
@Table(name = "purshase")
public class Purshase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="purshase_id")
    private Long purshaseId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="product_id")
    private Product product;

    private int count;
    @Column(name="total_price")
    private double totalPrice;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="employee_id")
    private Employee employee;

    public Long getPurshaseId() {
        return purshaseId;
    }

    public void setPurshaseId(Long purshaseId) {
        this.purshaseId = purshaseId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Purshase() {
    }

    public Purshase(Product product, int count, double totalPrice, Employee employee) {
        this.product = product;
        this.count = count;
        this.totalPrice = totalPrice;
        this.employee = employee;
    }
}