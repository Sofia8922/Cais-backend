package com.school.Cais.Controllers;

import com.school.Cais.DTOs.Accounts.AccountDTO;
import com.school.Cais.DTOs.Categories.CategoryCreateDTO;
import com.school.Cais.DTOs.Categories.CategoryDTO;
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
    private final CustomUserDetailsService accountService;

    @Autowired
    public AccountController(CustomUserDetailsService userDetailsService) {
        this.accountService = userDetailsService;
    }

    @GetMapping()
    public ResponseEntity<List<AccountDTO>> getAllCategories()
    {
        List<AccountDTO> accountDTOs = accountService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(accountDTOs);
    }
}
