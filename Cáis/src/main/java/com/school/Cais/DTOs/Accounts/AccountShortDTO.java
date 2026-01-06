package com.school.Cais.DTOs.Accounts;

import com.school.Cais.DTOs.CartItems.CartItemDTO;
import com.school.Cais.DTOs.Categories.CategoryShortDTO;
import com.school.Cais.DTOs.Products.ProductDTO;
import com.school.Cais.DTOs.Purchases.PurchaseDTO;
import com.school.Cais.Models.Account;
import com.school.Cais.Models.Category;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record AccountShortDTO(
        Long id,
        @NotBlank
        String username,
        String email,
        String address,
        String phoneNumber
) {
    public static AccountShortDTO fromEntity(Account account) {
        return new AccountShortDTO(
                account.getId(),
                account.getUsername(),
                account.getEmail(),
                account.getAddress(),
                account.getPhoneNumber()
        );
    }
}
