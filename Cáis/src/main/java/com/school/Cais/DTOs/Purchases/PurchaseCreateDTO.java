package com.school.Cais.DTOs.Purchases;

import com.school.Cais.Miscellaneous.Constants;
import com.school.Cais.Models.Purchase;
import jakarta.validation.constraints.NotNull;

public record PurchaseCreateDTO(
        @NotNull
        int amount,
        @NotNull
        Long productId
) {
    public Purchase toEntity() {
        Purchase purchase = new Purchase();
        purchase.setAmount(amount);
        purchase.setStatus(Constants.DeliveryStatus.PROCESSING);
        return purchase;
    }
}
