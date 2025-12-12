package com.school.Cais.DTOs.Subcategories;

import com.school.Cais.DTOs.Categories.CategoryShortDTO;
import com.school.Cais.Models.Subcategory;
import jakarta.validation.constraints.NotBlank;

public record SubcategoryDTO(
        Long id,
        @NotBlank
        String name,
        @NotBlank
        CategoryShortDTO category
) {
    public static SubcategoryDTO fromEntity(Subcategory sub) {
        return new SubcategoryDTO(
            sub.getId(),
            sub.getName(),
                CategoryShortDTO.fromEntity(sub.getCategory())
        );
    }
}
