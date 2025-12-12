package com.school.Cais.DTOs.Categories;

import com.school.Cais.Models.Category;

public record CategoryUpdateDTO(
    String name
) {
    public void updateEntity(Category category) {
        if (name != null) category.setName(name);
    }
}
