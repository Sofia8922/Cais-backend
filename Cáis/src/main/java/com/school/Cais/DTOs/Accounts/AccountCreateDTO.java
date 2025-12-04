package com.school.Cais.DTOs.Accounts;

import com.school.Cais.Models.Account;
import com.school.Cais.Models.Category;
import jakarta.validation.constraints.NotBlank;

public record AccountCreateDTO(
        @NotBlank
        String username
) {
    public Account toEntity() {
        Account account = new Account();
        account.setUsername(username);
        return account;
    }
}
