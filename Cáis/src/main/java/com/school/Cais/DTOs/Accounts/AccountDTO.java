package com.school.Cais.DTOs.Accounts;

import com.school.Cais.Models.Account;
import jakarta.validation.constraints.NotBlank;

public record AccountDTO(
        @NotBlank
        String username
) {
    public static AccountDTO fromEntity(Account account) {
        return new AccountDTO(
            account.getUsername()
        );
    }
}
