package com.school.Cais.Services;

import com.school.Cais.DTOs.Purchases.PurchaseCreateDTO;
import com.school.Cais.DTOs.Purchases.PurchaseDTO;
import com.school.Cais.Miscellaneous.ErrorHandler;
import com.school.Cais.Models.Product;
import com.school.Cais.Models.Purchase;
import com.school.Cais.Repositories.ProductRepository;
import com.school.Cais.Repositories.PurchaseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final ProductRepository productRepository;

    @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository, ProductRepository productRepository) {
        this.purchaseRepository = purchaseRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public PurchaseDTO createOrder(PurchaseCreateDTO createDTO) {
        Purchase purchase = createDTO.toEntity();

        Product product = productRepository.findById(createDTO.productId())
            .orElseGet(ErrorHandler.notFound("product"));

        purchase.setProduct(product);

        Purchase savedPurchase = purchaseRepository.save(purchase);
        return PurchaseDTO.fromEntity(savedPurchase);
    }

    public List<PurchaseDTO> findAll() {
        return purchaseRepository.findAll()
            .stream()
            .map(PurchaseDTO::fromEntity)
            .toList();
    }
}
