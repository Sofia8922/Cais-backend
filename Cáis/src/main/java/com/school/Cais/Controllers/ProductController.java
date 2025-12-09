package com.school.Cais.Controllers;

import com.school.Cais.DTOs.Products.ProductCreateDTO;
import com.school.Cais.DTOs.Products.ProductDTO;
import com.school.Cais.DTOs.Products.ProductUpdateDTO;
import com.school.Cais.Services.ProductService;
import com.school.Cais.Services.SubcategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final SubcategoryService subcategoryService;

    @Autowired
    public ProductController(ProductService productService, SubcategoryService subcategoryService) {
        this.productService = productService;
        this.subcategoryService = subcategoryService;
    }

    @PostMapping()
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductCreateDTO createDTO)
    {
        ProductDTO productDTO = productService.createProduct(createDTO);
        return ResponseEntity.status(HttpStatus.OK).body(productDTO);
    }

    @GetMapping()
    public ResponseEntity<List<ProductDTO>> getAllProducts()
    {
        List<ProductDTO> productDTOS = productService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(productDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping("/sub/{id}")
    public ResponseEntity<List<ProductDTO>> getProductBySubcategory(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findBySubcat(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> editProduct(@PathVariable Long id, @RequestBody ProductUpdateDTO dto) {
        System.out.println("a");
        return ResponseEntity.ok(productService.editById(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
