package com.school.Cais.DTOs.Subcategories;

import com.school.Cais.Models.Subcategory;

public record SubcategoryUpdateDTO(
        String name
) {
    public void updateEntity(Subcategory subcategory) {
        if (name != null) subcategory.setName(name);
    }
}
