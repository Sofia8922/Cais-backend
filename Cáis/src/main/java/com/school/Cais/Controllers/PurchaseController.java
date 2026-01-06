package com.school.Cais.Controllers;

import com.school.Cais.DTOs.Purchases.PurchaseDTO;
import com.school.Cais.DTOs.Purchases.PurchaseUpdateDTO;
import com.school.Cais.Models.Product;
import com.school.Cais.Services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {
    private final PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) { this.purchaseService = purchaseService; }

    @PutMapping("/id")
    public ResponseEntity<PurchaseDTO> updatePurchase(@PathVariable Long id, @RequestBody PurchaseUpdateDTO dto) {
        return ResponseEntity.ok(purchaseService.editById(id, dto));
    }
}
