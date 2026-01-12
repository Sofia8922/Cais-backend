package com.school.Cais.DTOs.Accounts;

import com.school.Cais.Models.Account;
import jakarta.validation.constraints.NotBlank;

public record AccountUpdateDTO(
    @NotBlank
    String username,
    String email,
    String address,
    String phoneNumber
) {
    public void updateEntity(Account account) {
        if (username != null) account.setUsername(username);
        if (email != null) account.setEmail(email);
        if (address != null) account.setAddress(address);
        if (phoneNumber != null) account.setPhoneNumber(phoneNumber);
    }
}
