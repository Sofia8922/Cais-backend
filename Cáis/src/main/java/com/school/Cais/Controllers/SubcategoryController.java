package com.school.Cais.Controllers;

import com.school.Cais.DTOs.Subcategories.SubcategoryCreateDTO;
import com.school.Cais.DTOs.Subcategories.SubcategoryDTO;
import com.school.Cais.DTOs.Subcategories.SubcategoryUpdateDTO;
import com.school.Cais.Services.SubcategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin("*")
@RequestMapping("/subcategories")
public class SubcategoryController {
    private final SubcategoryService subcategoryService;

    @Autowired
    public SubcategoryController(SubcategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }

    @PostMapping()
    public ResponseEntity<SubcategoryDTO> createSubcategory(@Valid @RequestBody SubcategoryCreateDTO createDTO)
    {
        SubcategoryDTO subDTO = subcategoryService.createSubcategory(createDTO);
        return ResponseEntity.status(HttpStatus.OK).body(subDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubcategoryDTO> updateSubcategory(@PathVariable Long id, @RequestBody SubcategoryUpdateDTO dto) {
        return ResponseEntity.ok(subcategoryService.updateSubcategory(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubcategory(@PathVariable Long id) {
        subcategoryService.deleteSubcategory(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<List<SubcategoryDTO>> getAllSubcategories()
    {
        List<SubcategoryDTO> subcategoryDTOs = subcategoryService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(subcategoryDTOs);
    }
}
