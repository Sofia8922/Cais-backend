package com.school.Cais.Controllers;

import com.school.Cais.DTOs.Accounts.AccountDTO;
import com.school.Cais.DTOs.Accounts.AccountRegisterDTO;
import com.school.Cais.DTOs.Accounts.AccountUpdateDTO;
import com.school.Cais.DTOs.Categories.CategoryCreateDTO;
import com.school.Cais.DTOs.Categories.CategoryDTO;
import com.school.Cais.Services.AccountService;
import com.school.Cais.Services.CategoryService;
import com.school.Cais.Services.CustomUserDetailsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
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

    @PostMapping("/{id}")
    public ResponseEntity<AccountDTO> editAccountById(@PathVariable Long id, @RequestBody AccountUpdateDTO dto) {
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
