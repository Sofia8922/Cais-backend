package com.school.Cais.DTOs.Categories;

import com.school.Cais.DTOs.Subcategories.SubcategoryShortDTO;
import com.school.Cais.Models.Category;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record CategoryShortDTO(
        Long id,
        @NotBlank
        String name
) {
    public static CategoryShortDTO fromEntity(Category category) {
        return new CategoryShortDTO(
            category.getId(),
            category.getName()
        );
    }
}
