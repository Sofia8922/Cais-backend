package com.school.Cais.Services;

import com.school.Cais.DTOs.Products.ProductCreateDTO;
import com.school.Cais.DTOs.Products.ProductDTO;
import com.school.Cais.DTOs.Products.ProductUpdateDTO;
import com.school.Cais.Miscellaneous.ErrorHandler;
import com.school.Cais.Models.Category;
import com.school.Cais.Models.Product;
import com.school.Cais.Models.Subcategory;
import com.school.Cais.Repositories.ProductRepository;
import com.school.Cais.Repositories.SubcategoryRepository;
import jakarta.transaction.Transactional;
import jdk.jshell.spi.ExecutionControl;
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

    @Transactional
    public ProductDTO updateStock(Long productId, int amount) {
        Product product = productRepository.findById(productId)
                .orElseGet(() -> ErrorHandler.notFound("product"));

        int newStock = product.getStock() + amount;
        if(newStock < 0)
            ErrorHandler.soldOut(product.getName(), product.getStock());

        product.setStock(newStock);
        Product savedProduct = productRepository.save(product);
        return ProductDTO.fromEntity(savedProduct);
    }

    @Transactional
    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseGet(() -> ErrorHandler.notFound("product"));
        return ProductDTO.fromEntity(product);
    }

    @Transactional
    public ProductDTO editById(Long id, ProductUpdateDTO dto) {
        Product product = productRepository.findById(id)
                .orElseGet(() -> ErrorHandler.notFound("product"));

        dto.updateEntity(product);

        if (subcategoryRepository != null) {
            Subcategory subcategory = subcategoryRepository.findById(dto.subcategoryId())
                    .orElseGet(() -> ErrorHandler.notFound("subcategory"));
            product.setSubcategory(subcategory);
        }

        return ProductDTO.fromEntity(product);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseGet(() -> ErrorHandler.notFound("product"));
        productRepository.delete(product);
    }
}
