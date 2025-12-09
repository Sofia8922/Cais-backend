package com.school.Cais.Services;

import com.school.Cais.DTOs.Accounts.AccountDTO;
import com.school.Cais.DTOs.Accounts.AccountRegisterDTO;
import com.school.Cais.Models.Account;
import com.school.Cais.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AccountDTO register(AccountRegisterDTO accountRegisterDTO) {
        Account account = accountRegisterDTO.toEntity();

        String password = account.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        account.setPassword(encodedPassword);
        Account savedAccount = accountRepository.save(account);

        return AccountDTO.fromEntity(savedAccount);
    }
}
