package com.school.Cais.Services;

import com.school.Cais.DTOs.Products.ProductCreateDTO;
import com.school.Cais.DTOs.Products.ProductDTO;
import com.school.Cais.Miscellaneous.ErrorHandler;
import com.school.Cais.Models.Product;
import com.school.Cais.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    public ProductDTO createProduct(ProductCreateDTO createDTO) {
        List<Product> existingProducts = productRepository.findAll();
        for(Product existingProduct : existingProducts)
            if(existingProduct.getName().equalsIgnoreCase(createDTO.name()))
                ErrorHandler.alreadyExists("the name \"" + createDTO.name() + "\"");
        Product product = createDTO.toEntity();
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
