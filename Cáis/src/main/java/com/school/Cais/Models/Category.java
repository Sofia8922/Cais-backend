package com.school.Cais.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue
    Long id;
    @NotBlank
    String name;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    List<Subcategory> subcategoryList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("Category.setName CALLED WITH: " + name);
        this.name = name;
    }
}
