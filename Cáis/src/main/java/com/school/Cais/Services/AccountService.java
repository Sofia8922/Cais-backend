package com.school.Cais.Services;

import com.school.Cais.DTOs.Accounts.AccountDTO;
import com.school.Cais.DTOs.Accounts.AccountRegisterDTO;
import com.school.Cais.DTOs.Accounts.AccountUpdateDTO;
import com.school.Cais.DTOs.Products.ProductDTO;
import com.school.Cais.Miscellaneous.ErrorHandler;
import com.school.Cais.Models.Account;
import com.school.Cais.Models.CartItem;
import com.school.Cais.Models.Product;
import com.school.Cais.Repositories.AccountRepository;
import com.school.Cais.Repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  AccountService {
    private final AccountRepository accountRepository;
    private final ProductRepository productRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountService(AccountRepository accountRepository, ProductRepository productRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.productRepository = productRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AccountDTO register(AccountRegisterDTO accountRegisterDTO) {
        Account account = accountRegisterDTO.toEntity();
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
        return AccountDTO.fromEntity(account);
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

    @Transactional
    public AccountDTO addToCart(Long accountId, Long productId, int amount) {
        Account account = accountRepository.findById(accountId)
                .orElseGet(() -> ErrorHandler.notFound("Account"));

        Product product = productRepository.findById(productId)
                .orElseGet(() -> ErrorHandler.notFound("Product"));

        account.addToCart(product, amount);
//        CartItem cartItem = account.getCartItems().stream()
//                        .filter(ci -> ci.getProduct().getId().equals(productId))
//                        .findFirst()
//                        .orElse(null);
//
//        if (cartItem != null) {
//            cartItem.setQuantity(cartItem.getQuantity() + amount);
//        } else {
//            account.getCartItems().add(new CartItem(account, product, amount));
//        }
        accountRepository.save(account);
        return AccountDTO.fromEntity(account);
    }

    @Transactional
    public AccountDTO removeFromCart(Long accountId, Long productId, int amount) {
        Account account = accountRepository.findById(accountId)
                .orElseGet(() -> ErrorHandler.notFound("Account"));

        Product product = productRepository.findById(productId)
                .orElseGet(() -> ErrorHandler.notFound("Product"));

        account.removeFromCart(product, amount);
//        account.getCartItems().removeIf(cartItem -> {
//            if (cartItem.getProduct().getId().equals(productId)) {
//                if (cartItem.getQuantity() > amount) {
//                    cartItem.setQuantity(cartItem.getQuantity() - amount);
//                    return false;
//                } else {
//                    return true;
//                }
//            }
//            return true;
//        });
        accountRepository.save(account);
        return AccountDTO.fromEntity(account);
    }

    public AccountDTO addToFavorites(Long accountId, Long productId) {
        Account account = accountRepository.findById(accountId)
                .orElseGet(() -> ErrorHandler.notFound("Account"));

        Product product = productRepository.findById(productId)
                .orElseGet(() -> ErrorHandler.notFound("Product"));

        account.addToFavorites(product);
        accountRepository.save(account);

        return AccountDTO.fromEntity(account);
    }

    public AccountDTO removeFromFavorites(Long accountId, Long productId) {
        Account account = accountRepository.findById(accountId)
                .orElseGet(() -> ErrorHandler.notFound("Account"));

        Product product = productRepository.findById(productId)
                .orElseGet(() -> ErrorHandler.notFound("Product"));

        account.removeFromFavorites(product);
        accountRepository.save(account);

        return AccountDTO.fromEntity(account);
    }
}
