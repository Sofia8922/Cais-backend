package com.school.Cais.DTOs.Products;

import com.school.Cais.DTOs.Subcategories.SubcategoryDTO;
import com.school.Cais.Models.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductShortDTO(
        @NotBlank
        String name,
        String description,
        @NotNull
        Float price,
        @NotNull
        int stock,
        String imageLink,
        @NotNull
        SubcategoryDTO subcategory
) {
    public static ProductShortDTO fromEntity(Product product) {
        return new ProductShortDTO(
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            product.getStock(),
            product.getImageLink(),
            SubcategoryDTO.fromEntity(product.getSubcategory())
        );
    }
}
