package com.example.shop.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Long productId;

    @ManyToOne(fetch = FetchType.EAGER)      // одна категория - множество продуктов
    @JoinColumn(name = "category_id")       // название столбца в таблице product
    private Category category;

    @NotEmpty(message = "Description should not be empty")
    private String description;

    @NotNull(message = "Input price")
    @Positive(message = "Price should be positive")
    private double price;

    @NotNull(message = "Input balance")
    @PositiveOrZero(message = "Balance should be positive or zero")
    private int balance;

    @OneToMany(mappedBy ="product", cascade = CascadeType.ALL)
    private List<Purchase> purchase = new ArrayList<>();

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Purchase> getPurshase() {
        return purchase;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public void setPurshase(List<Purchase> purchase) {
        this.purchase = purchase;
    }

    public Product() {
    }

    public Product(Category category, String description, double price, int balance) {
        this.category = category;
        this.description = description;
        this.price = price;
        this.balance = balance;
    }
}
