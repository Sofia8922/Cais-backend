package com.school.Cais.DTOs.Categories;

import com.school.Cais.DTOs.Subcategories.SubcategoryDTO;
import com.school.Cais.DTOs.Subcategories.SubcategoryShortDTO;
import com.school.Cais.Models.Category;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record CategoryDTO(
        Long id,
        @NotBlank
        String name,
        List<SubcategoryShortDTO> subcategories
) {
    public static CategoryDTO fromEntity(Category category) {
        return new CategoryDTO(
            category.getId(),
            category.getName(),
            category.getSubcategoryList().stream().map(SubcategoryShortDTO::fromEntity).toList()
        );
    }
}
