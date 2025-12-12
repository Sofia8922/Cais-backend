package com.school.Cais.DTOs.Subcategories;

import com.school.Cais.DTOs.Categories.CategoryDTO;
import com.school.Cais.Models.Subcategory;
import jakarta.validation.constraints.NotBlank;

public record SubcategoryShortDTO(
        Long id,
        @NotBlank
        String name
) {
    public static SubcategoryShortDTO fromEntity(Subcategory sub) {
        return new SubcategoryShortDTO(
            sub.getId(),
            sub.getName()
        );
    }
}
