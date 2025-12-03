package com.school.Cais.Services;

import com.school.Cais.DTOs.Products.ProductCreateDTO;
import com.school.Cais.DTOs.Products.ProductDTO;
import com.school.Cais.Miscellaneous.ErrorHandler;
import com.school.Cais.Models.Category;
import com.school.Cais.Models.Product;
import com.school.Cais.Models.Subcategory;
import com.school.Cais.Repositories.ProductRepository;
import com.school.Cais.Repositories.SubcategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final SubcategoryRepository subcategoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, SubcategoryRepository subcategoryRepository)
    {
        this.productRepository = productRepository;
        this.subcategoryRepository = subcategoryRepository;
    }

    @Transactional
    public ProductDTO createProduct(ProductCreateDTO createDTO) {
        List<Product> existingProducts = productRepository.findAll();
        for(Product existingProduct : existingProducts)
            if(existingProduct.getName().equalsIgnoreCase(createDTO.name()))
                ErrorHandler.alreadyExists("the name \"" + createDTO.name() + "\"");

        Product product = createDTO.toEntity();

        Subcategory subcategory = subcategoryRepository.findById(createDTO.subcategoryId())
                .orElseGet(() -> ErrorHandler.notFound("subcategory"));

        product.setSubcategory(subcategory);

        Product savedProduct = productRepository.save(product);
        return ProductDTO.fromEntity(savedProduct);
    }

    public List<ProductDTO> findAll() {
        return productRepository.findAll()
                .stream()
                .map(ProductDTO::fromEntity)
                .toList();
    }
}
