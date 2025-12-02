package com.school.Cais.DTOs.Categories;

import com.school.Cais.Models.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryCreateDTO(
        @NotBlank
        String name
) {
    public Category toEntity() {
        Category category = new Category();
        category.setName(name);
        return category;
    }
}
