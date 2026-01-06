package com.school.Cais.Services;

import com.school.Cais.DTOs.Purchases.PurchaseDTO;
import com.school.Cais.DTOs.Purchases.PurchaseUpdateDTO;
import com.school.Cais.Miscellaneous.ErrorHandler;
import com.school.Cais.Models.Purchase;
import com.school.Cais.Repositories.PurchaseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {
    PurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @Transactional
    public PurchaseDTO editById(Long id, PurchaseUpdateDTO dto) {
        Purchase purchase = purchaseRepository.findById(id)
            .orElseGet(() -> ErrorHandler.notFound("purchase"));

        dto.updateEntity(purchase);
        purchaseRepository.save(purchase);
        return PurchaseDTO.fromEntity(purchase);
    }
}
