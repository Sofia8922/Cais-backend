package com.school.Cais.DTOs.Purchases;

import com.school.Cais.DTOs.Products.ProductDTO;
import com.school.Cais.DTOs.Products.ProductShortDTO;
import com.school.Cais.Miscellaneous.Constants;
import com.school.Cais.Models.Purchase;
import jakarta.validation.constraints.NotNull;

public record PurchaseDTO(
        Long id,
        @NotNull
        int amount,
        double unitPrice,
        @NotNull
        Constants.DeliveryStatus status,
        @NotNull
        ProductShortDTO productDTO
) {
    public static PurchaseDTO fromEntity(Purchase purchase) {
        return new PurchaseDTO(
            purchase.getId(),
            purchase.getAmount(),
            purchase.getUnitPrice(),
            purchase.getStatus(),
            ProductShortDTO.fromEntity(purchase.getProduct())
        );
    }
}
