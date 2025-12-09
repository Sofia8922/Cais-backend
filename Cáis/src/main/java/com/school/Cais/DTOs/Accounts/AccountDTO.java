package com.school.Cais.DTOs.Accounts;

import com.school.Cais.DTOs.CartItems.CartItemDTO;
import com.school.Cais.Models.Account;
import com.school.Cais.Models.CartItem;
import com.school.Cais.Models.Product;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record AccountDTO(
        Long id,
        @NotBlank
        String username,
        String address,
        String phoneNumber,
        List<CartItemDTO> cart,
        List<Long> favoritesProductIds,
        List<Long> recentOrderIds,
        List<String> roles
) {
    public static AccountDTO fromEntity(Account account) {

        List<CartItemDTO> cartDTOs = account.getCartItems().stream()
                .map(ci -> new CartItemDTO(ci.getProduct().getId(), ci.getQuantity()))
                .toList();

        return new AccountDTO(
            account.getId(),
            account.getUsername(),
            account.getAddress(),
            account.getPhoneNumber(),
            cartDTOs,
            account.getFavorites().stream().map(Product::getId).toList(),
            account.getRecentOrders().stream().map(Product::getId).toList(),
            account.getRoles()
        );
    }
}
