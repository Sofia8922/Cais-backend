package com.school.Cais.DTOs.Categories;

import com.school.Cais.Models.Category;
import jakarta.validation.constraints.NotBlank;

public record CategoryDTO(
        Long id,
        @NotBlank
        String name
) {
    public static CategoryDTO fromEntity(Category category) {
        return new CategoryDTO(
            category.getId(),
            category.getName()
        );
    }
}
