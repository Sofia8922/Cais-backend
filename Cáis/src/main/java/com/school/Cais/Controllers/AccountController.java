package com.school.Cais.Controllers;

import com.school.Cais.DTOs.Accounts.*;
import com.school.Cais.Services.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin("*")
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/register")
    public ResponseEntity<AccountDTO> register(@RequestBody AccountRegisterDTO accountRegisterDTO) {
        AccountDTO account = accountService.register(accountRegisterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }

    @PostMapping("/login")
    public ResponseEntity<AccountDTO> login(@RequestBody AccountLoginRequestDTO dto, HttpServletRequest request) {
        AccountDTO account = accountService.login(dto, request);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        request.getSession().invalidate();
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Logged out");
    }

    @GetMapping()
    public ResponseEntity<List<AccountDTO>> getAllAccount() {
        List<AccountDTO> accountDTOs = accountService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(accountDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.findById(id));
    }

    @PostMapping("/{accountId}/cart/{productId}")
    public ResponseEntity<AccountDTO> addToCart(@PathVariable Long accountId, @PathVariable Long productId, @RequestParam(defaultValue = "1") int amount) {
        return ResponseEntity.ok(accountService.addToCart(accountId, productId, amount));
    }

    @DeleteMapping("/{accountId}/cart/{productId}")
    public ResponseEntity<AccountDTO> removeFromCart(@PathVariable Long accountId, @PathVariable Long productId, @RequestParam(defaultValue = "1") int amount) {
        return ResponseEntity.ok(accountService.removeFromCart(accountId, productId, amount));
    }

    @PostMapping("/{accountId}/cart")
    public ResponseEntity<AccountDTO> clearAllFromCart(@PathVariable Long accountId) {
        return ResponseEntity.ok(accountService.removeAllFromCart(accountId));
    }

    @PostMapping("/{accountId}/favorites/{productId}")
    public ResponseEntity<AccountDTO> addToFavorites(@PathVariable Long accountId, @PathVariable Long productId) {
        return ResponseEntity.ok(accountService.addToFavorites(accountId, productId));
    }

    @DeleteMapping("/{accountId}/favorites/{productId}")
    public ResponseEntity<AccountDTO> removeFromFavorites(@PathVariable Long accountId, @PathVariable Long productId) {
        return ResponseEntity.ok(accountService.removeFromFavorites(accountId, productId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDTO> editAccountById(@PathVariable Long id, @RequestBody AccountUpdateDTO dto) {
        System.out.println(dto);
        return ResponseEntity.ok(accountService.editAccountById(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccountById(@PathVariable Long id) {
        accountService.deleteAccountById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/checkout")
    public ResponseEntity<AccountDTO> checkout(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.createPurchase(id));
    }
}
