package com.school.Cais.DTOs.Purchases;

import com.school.Cais.DTOs.Products.ProductShortDTO;
import com.school.Cais.Miscellaneous.Constants;
import com.school.Cais.Models.Purchase;
import jakarta.validation.constraints.NotNull;

public record PurchaseShortDTO(
        @NotNull
        int amount,
        @NotNull
        Constants.DeliveryStatus status
) {
    public static PurchaseShortDTO fromEntity(Purchase purchase) {
        return new PurchaseShortDTO(
            purchase.getAmount(),
            purchase.getStatus()
        );
    }
}
