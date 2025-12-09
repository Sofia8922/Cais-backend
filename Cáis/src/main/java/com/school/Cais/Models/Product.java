package com.school.Cais.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    private String name;
    private String description;
    @NotNull
    private Float price;
    @NotNull
    private int stock;
    @ManyToOne
    @JoinColumn(name = "subcategory_id")
    private Subcategory subcategory;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Purchase> purchaseList = new ArrayList<>();
    private String imageLink;

    public Product() {
    }

    public Product(String name, String description, Float price, int stock, String imageLink) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.imageLink = imageLink;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public List<Purchase> getPurchaseList() {
        return purchaseList;
    }

    public void addPurchaseToList(Purchase purchase) {
        this.purchaseList.add(purchase);
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }
}
