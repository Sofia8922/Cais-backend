package com.school.Cais.DTOs.Categories;

import com.school.Cais.DTOs.Subcategories.SubcategoryShortDTO;
import com.school.Cais.Models.Category;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record CategoryDTO(
        @NotBlank
        String name,
        List<SubcategoryShortDTO> subcategoryList
) {
    public static CategoryDTO fromEntity(Category category) {
        return new CategoryDTO(
            category.getName(),
            category.getSubcategoryList().stream().map(SubcategoryShortDTO::fromEntity).toList()
        );
    }
}
