package com.school.Cais.DTOs.Accounts;

import com.school.Cais.Miscellaneous.Enums.Role;
import com.school.Cais.Models.Account;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record AccountRegisterDTO(
        @NotBlank
        String username,
        @NotBlank
        String password,
        @NotBlank
        @Email(message = "Email not valid")
        String email,
        List<Role> roles
) {
    public Account toEntity() {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setEmail(email);
        account.setRoles(roles);
        return account;
    }
}
