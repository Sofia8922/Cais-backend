package com.school.Cais.Services;

import com.school.Cais.DTOs.Categories.CategoryCreateDTO;
import com.school.Cais.DTOs.Categories.CategoryDTO;
import com.school.Cais.Models.Category;
import com.school.Cais.Repositories.CategoryRepository;
import com.school.Cais.Repositories.SubcategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public CategoryDTO createCategory(CategoryCreateDTO createDTO) {
        Category category = createDTO.toEntity();
        Category savedCategory = categoryRepository.save(category);
        return CategoryDTO.fromEntity(savedCategory);
    }

    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll()
            .stream()
            .map(CategoryDTO::fromEntity)
            .toList();
    }
}
