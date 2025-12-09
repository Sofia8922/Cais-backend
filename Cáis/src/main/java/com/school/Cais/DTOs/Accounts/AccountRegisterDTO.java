package com.school.Cais.DTOs.Accounts;

import com.school.Cais.Models.Account;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record AccountRegisterDTO(
        @NotBlank
        String username,
        String password,
        List<String> roles
) {
    public Account toEntity() {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setRoles(roles);
        return account;
    }
}
