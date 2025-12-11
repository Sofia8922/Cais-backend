package com.school.Cais.Services;

import com.school.Cais.DTOs.Subcategories.SubcategoryDTO;
import com.school.Cais.DTOs.Subcategories.SubcategoryCreateDTO;
import com.school.Cais.DTOs.Subcategories.SubcategoryUpdateDTO;
import com.school.Cais.Miscellaneous.ErrorHandler;
import com.school.Cais.Models.Category;
import com.school.Cais.Models.Subcategory;
import com.school.Cais.Repositories.CategoryRepository;
import com.school.Cais.Repositories.SubcategoryRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubcategoryService {
    private final SubcategoryRepository subcategoryRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public SubcategoryService(SubcategoryRepository subcategoryRepository, CategoryRepository categoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public SubcategoryDTO createSubcategory(SubcategoryCreateDTO createDTO) {
        Subcategory sub = createDTO.toEntity();

        Category category = categoryRepository.findById(createDTO.categoryId())
            .orElseGet(() -> ErrorHandler.notFound("category"));

        sub.setCategory(category);

        Subcategory savedSub = subcategoryRepository.save(sub);
        return SubcategoryDTO.fromEntity(savedSub);
    }

    public List<SubcategoryDTO> findAll() {
        return subcategoryRepository.findAll()
            .stream()
            .map(SubcategoryDTO::fromEntity)
            .toList();
    }

    @Transactional
    public SubcategoryDTO updateSubcategory(Long id, SubcategoryUpdateDTO dto) {
        Subcategory subcategory = subcategoryRepository.findById(id)
                .orElseGet(() -> ErrorHandler.notFound("Subcategory"));

        if (dto.categoryId() != null) {
            Category category = categoryRepository.findById(dto.categoryId())
                    .orElseGet(() -> ErrorHandler.notFound("Category"));

            subcategory.setCategory(category);
        }
        subcategoryRepository.save(subcategory);

        return SubcategoryDTO.fromEntity(subcategory);
    }

    @Transactional
    public void deleteSubcategory(Long id) {
        Subcategory subcategory = subcategoryRepository.findById(id)
                .orElseGet(() -> ErrorHandler.notFound("Subcategory"));
        subcategoryRepository.delete(subcategory);
    }
}
