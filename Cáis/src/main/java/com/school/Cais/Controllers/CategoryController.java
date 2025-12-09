package com.school.Cais.Controllers;

import com.school.Cais.DTOs.Categories.CategoryCreateDTO;
import com.school.Cais.DTOs.Categories.CategoryDTO;
import com.school.Cais.Services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping()
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryCreateDTO createDTO)
    {
        CategoryDTO categoryDTO = categoryService.createCategory(createDTO);
        return ResponseEntity.status(HttpStatus.OK).body(categoryDTO);
    }

    @GetMapping()
    public ResponseEntity<List<CategoryDTO>> getAllCategories()
    {
        List<CategoryDTO> categoryDTOs = categoryService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(categoryDTOs);
    }
}
