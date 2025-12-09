package com.school.Cais.DTOs.Accounts;

import com.school.Cais.Models.Account;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record AccountDTO(
        @NotBlank
        String username,
        List<String> roles
) {
    public static AccountDTO fromEntity(Account account) {
        return new AccountDTO(
            account.getUsername(),
            account.getRoles()
        );
    }
}
