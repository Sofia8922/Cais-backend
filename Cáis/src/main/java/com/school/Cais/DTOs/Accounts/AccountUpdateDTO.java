package com.school.Cais.DTOs.Accounts;

import com.school.Cais.Models.Account;

public record AccountUpdateDTO(
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
