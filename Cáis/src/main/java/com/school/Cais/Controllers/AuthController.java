package com.school.Cais.Controllers;

import com.school.Cais.DTOs.Accounts.AccountDTO;
import com.school.Cais.DTOs.Accounts.AccountRegisterDTO;
import com.school.Cais.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AccountService accountService;

    @Autowired
    public AuthController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountDTO> register(@RequestBody AccountRegisterDTO accountRegisterDTO) {
        AccountDTO account = accountService.register(accountRegisterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }

//    login here V
}
