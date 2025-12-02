package com.school.Cais.Controllers;

import com.school.Cais.DTOs.Purchases.PurchaseCreateDTO;
import com.school.Cais.DTOs.Purchases.PurchaseDTO;
import com.school.Cais.Services.PurchaseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/purchases")
public class PurchaseController {
    private final PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping()
    public ResponseEntity<PurchaseDTO> createPurchase(@Valid @RequestBody PurchaseCreateDTO createDTO)
    {
        PurchaseDTO oderDTO = purchaseService.createOrder(createDTO);
        return ResponseEntity.status(HttpStatus.OK).body(oderDTO);
    }

    @GetMapping()
    public ResponseEntity<List<PurchaseDTO>> getAllPurchases()
    {
        List<PurchaseDTO> purchaseDTOS = purchaseService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(purchaseDTOS);
    }
}
