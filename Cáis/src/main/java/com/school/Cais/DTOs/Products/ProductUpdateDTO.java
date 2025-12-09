package com.school.Cais.DTOs.Products;

import com.school.Cais.Models.Product;
import com.school.Cais.Models.Subcategory;

public record ProductUpdateDTO(
    String name,
    String description,
    Float price,
    int stock,
    String imageLink,
    Long subcategoryId
) {
    public void updateEntity(Product product) {
        if (name != null) product.setName(name);
        if (description != null) product.setDescription(description);
        if (price != null) product.setPrice(price);
        if (stock >= 0) product.setStock(stock);
        if (imageLink != null) product.setImageLink(imageLink);
    }
}
