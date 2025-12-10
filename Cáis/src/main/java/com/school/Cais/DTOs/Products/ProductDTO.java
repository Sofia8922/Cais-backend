package com.school.Cais.DTOs.Products;

import com.school.Cais.DTOs.Purchases.PurchaseDTO;
import com.school.Cais.DTOs.Purchases.PurchaseShortDTO;
import com.school.Cais.DTOs.Subcategories.SubcategoryDTO;
import com.school.Cais.Models.Product;
import com.school.Cais.Models.Subcategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

public record ProductDTO(
        Long id,
        @NotBlank
        String name,
        String description,
        @NotNull
        Float price,
        @NotNull
        int stock,
        String imageLink,
        SubcategoryDTO subcategory
//        List<PurchaseShortDTO> purchases

) {
    public static ProductDTO fromEntity(Product product) {
        return new ProductDTO(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            product.getStock(),
            product.getImageLink(),
            SubcategoryDTO.fromEntity(product.getSubcategory())
        );
    }
}
