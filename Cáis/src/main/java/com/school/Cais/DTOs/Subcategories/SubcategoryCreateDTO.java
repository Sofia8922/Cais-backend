package com.school.Cais.DTOs.Subcategories;

import com.school.Cais.Models.Subcategory;
import jakarta.validation.constraints.NotBlank;

public record SubcategoryCreateDTO(
        @NotBlank
        String name,
//        @NotBlank
        Long categoryId
) {
    public Subcategory toEntity() {
        Subcategory sub = new Subcategory();
        sub.setName(name);
        return sub;
    }
}
