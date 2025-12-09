package com.school.Cais.Services;

import com.school.Cais.DTOs.Accounts.AccountDTO;
import com.school.Cais.DTOs.Accounts.AccountRegisterDTO;
import com.school.Cais.DTOs.Accounts.AccountUpdateDTO;
import com.school.Cais.DTOs.Products.ProductDTO;
import com.school.Cais.Miscellaneous.ErrorHandler;
import com.school.Cais.Models.Account;
import com.school.Cais.Repositories.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  AccountService {
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

    public List<AccountDTO> findAll() {
        return accountRepository.findAll()
                .stream()
                .map(AccountDTO::fromEntity)
                .toList();
    }

    public AccountDTO findById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseGet(() -> ErrorHandler.notFound("Account"));
        return AccountDTO.fromEntity(account);
    }

    @Transactional
    public AccountDTO editAccountById(Long id, AccountUpdateDTO dto) {
        Account account = accountRepository.findById(id)
                .orElseGet(() -> ErrorHandler.notFound("Account"));
        dto.updateEntity(account);

        return AccountDTO.fromEntity(account);
    }

    public void deleteAccountById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseGet(() -> ErrorHandler.notFound("Account"));
        accountRepository.delete(account);
    }
}
