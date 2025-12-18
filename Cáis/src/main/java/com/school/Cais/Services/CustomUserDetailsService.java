package com.school.Cais.Services;

import com.school.Cais.DTOs.Accounts.AccountDTO;
import com.school.Cais.Miscellaneous.ErrorHandler;
import com.school.Cais.Models.Account;
import com.school.Cais.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;

    @Autowired
    public CustomUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) {
        Account account = accountRepository.findByUsername(name);
        if(account == null) ErrorHandler.notFound("account");

        var authorities = account.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .toList();

        return org.springframework.security.core.userdetails.User.builder()
                .username(account.getUsername())
                .password(account.getPassword())
                .authorities(authorities)
//                .roles(account.getRoles().toArray(new String[0]))
                .build();
    }

    public List<AccountDTO> findAll() {
        return accountRepository.findAll()
                .stream()
                .map(AccountDTO::fromEntity)
                .toList();
    }
}
