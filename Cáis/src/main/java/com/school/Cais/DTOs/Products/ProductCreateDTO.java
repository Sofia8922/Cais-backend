package com.school.Cais.DTOs.Products;

import com.school.Cais.Models.Product;
import jakarta.validation.constraints.NotBlank;

public record ProductCreateDTO(
        @NotBlank
        String name
) {
    public Product toEntity() {
        Product product = new Product();
        product.setName(name);
        return product;
    }
}
