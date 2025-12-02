package com.school.Cais.DTOs.Products;

import com.school.Cais.Models.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductDTO(
        @NotBlank
        String name,
        String description,
        @NotNull
        Float price,
        @NotNull
        int stock,
        String imageLink
) {
    public static ProductDTO fromEntity(Product product) {
        return new ProductDTO(
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            product.getStock(),
            product.getImageLink()
        );
    }
}
