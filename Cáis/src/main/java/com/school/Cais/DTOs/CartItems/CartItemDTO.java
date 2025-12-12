package com.school.Cais.DTOs.CartItems;

import com.school.Cais.DTOs.Products.ProductDTO;
import com.school.Cais.Models.CartItem;

public record CartItemDTO(
        Long productId,
        ProductDTO product,
        int quantity
) {
    public static CartItemDTO fromEntity(CartItem i) {
        return new CartItemDTO(
                i.getId(),
                ProductDTO.fromEntity(i.getProduct()),
                i.getQuantity()
        );
    }
}
