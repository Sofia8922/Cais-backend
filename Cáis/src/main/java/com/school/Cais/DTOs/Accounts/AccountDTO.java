package com.school.Cais.DTOs.Accounts;

import com.school.Cais.DTOs.CartItems.CartItemDTO;
import com.school.Cais.DTOs.Products.ProductDTO;
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
        List<ProductDTO> favorites,
        List<ProductDTO> recentOrders,
        List<String> roles
) {
    public static AccountDTO fromEntity(Account account) {

        List<CartItemDTO> cartDTOs = account.getCartItems().stream()
                .map(CartItemDTO::fromEntity)
                .toList();

        List<ProductDTO> favoriteDTOs = account.getFavorites().stream()
                .map(ProductDTO::fromEntity)
                .toList();

        List<ProductDTO> recentOrderDTOs = account.getRecentOrders().stream()
                .map(ProductDTO::fromEntity)
                .toList();

        return new AccountDTO(
            account.getId(),
            account.getUsername(),
            account.getAddress(),
            account.getPhoneNumber(),
            cartDTOs,
            favoriteDTOs,
            recentOrderDTOs,
            account.getRoles()
        );
    }
}
