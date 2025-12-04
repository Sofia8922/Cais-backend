package com.school.Cais.DTOs.Products;

import com.school.Cais.Models.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductCreateDTO(
        @NotBlank
        String name,
        String description,
        @NotNull
        Float price,
        @NotNull
        int stock,
        String imageLink,
        @NotNull
        Long subcategoryId
) {
    public Product toEntity() {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setStock(stock);
        product.setImageLink(imageLink);
        return product;
    }
}
