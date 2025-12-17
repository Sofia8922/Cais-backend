package com.school.Cais.Services;

import com.school.Cais.DTOs.Categories.CategoryCreateDTO;
import com.school.Cais.DTOs.Categories.CategoryDTO;
import com.school.Cais.DTOs.Categories.CategoryUpdateDTO;
import com.school.Cais.Miscellaneous.ErrorHandler;
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
    private final SubcategoryRepository subcategoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, SubcategoryRepository subcategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.subcategoryRepository = subcategoryRepository;
    }

    @Transactional
    public CategoryDTO createCategory(CategoryCreateDTO createDTO) {
        Category category = createDTO.toEntity();
        Category savedCategory = categoryRepository.save(category);
        return CategoryDTO.fromEntity(savedCategory);
    }

    @Transactional
    public CategoryDTO updateCategory(Long id, CategoryUpdateDTO dto) {
        Category category = categoryRepository.findById(id)
                .orElseGet(() -> ErrorHandler.notFound("Category"));

        dto.updateEntity(category);
        categoryRepository.save(category);
        return CategoryDTO.fromEntity(category);
    }

    @Transactional
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseGet(() -> ErrorHandler.notFound("Category"));
        categoryRepository.delete(category);
    }

    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll()
            .stream()
            .map(CategoryDTO::fromEntity)
            .toList();
    }
}
