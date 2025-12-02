package com.school.Cais.DTOs.Products;

import com.school.Cais.Models.Product;
import jakarta.validation.constraints.NotBlank;

public record ProductDTO(
        @NotBlank
        String name
) {
    public static ProductDTO fromEntity(Product product) {
        return new ProductDTO(
            product.getName()
        );
    }
}
