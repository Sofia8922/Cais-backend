package com.school.Cais.DTOs.Subcategories;

import com.school.Cais.DTOs.Categories.CategoryDTO;
import com.school.Cais.Models.Category;
import com.school.Cais.Models.Subcategory;
import jakarta.validation.constraints.NotBlank;

public record SubcategoryDTO(
        @NotBlank
        String name,
        @NotBlank
        CategoryDTO category
) {
    public static SubcategoryDTO fromEntity(Subcategory sub) {
        return new SubcategoryDTO(
            sub.getName(),
            CategoryDTO.fromEntity(sub.getCategory())
        );
    }
}
