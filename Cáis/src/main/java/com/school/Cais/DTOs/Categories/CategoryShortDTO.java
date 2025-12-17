package com.school.Cais.DTOs.Categories;

import com.school.Cais.Models.Category;
import jakarta.validation.constraints.NotBlank;

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
