package com.school.Cais.DTOs.Purchases;

import com.school.Cais.DTOs.Accounts.AccountDTO;
import com.school.Cais.DTOs.Accounts.AccountRegisterDTO;
import com.school.Cais.DTOs.Accounts.AccountShortDTO;
import com.school.Cais.Miscellaneous.Constants;
import com.school.Cais.Models.Purchase;
import jakarta.validation.constraints.NotNull;

public record PurchaseShortDTO(
        Long id,
        @NotNull
        int amount,
        @NotNull
        Constants.DeliveryStatus status,
        AccountShortDTO user
) {
    public static PurchaseShortDTO fromEntity(Purchase purchase) {
        return new PurchaseShortDTO(
            purchase.getId(),
            purchase.getAmount(),
            purchase.getStatus(),
            AccountShortDTO.fromEntity(purchase.getUser())
        );
    }
}
