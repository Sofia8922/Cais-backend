package com.school.Cais.DTOs.Purchases;

import com.school.Cais.Miscellaneous.Constants;
import com.school.Cais.Models.Purchase;
import jakarta.validation.constraints.NotNull;

public record PurchaseUpdateDTO(
        @NotNull
        Constants.DeliveryStatus status
) {
    public void updateEntity(Purchase purchase) {
        purchase.setStatus(status);
    }
}
