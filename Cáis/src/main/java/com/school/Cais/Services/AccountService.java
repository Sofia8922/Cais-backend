package com.school.Cais.Services;

import com.school.Cais.DTOs.Accounts.AccountDTO;
import com.school.Cais.DTOs.Accounts.AccountLoginRequestDTO;
import com.school.Cais.DTOs.Accounts.AccountRegisterDTO;
import com.school.Cais.DTOs.Accounts.AccountUpdateDTO;
import com.school.Cais.Miscellaneous.Constants;
import com.school.Cais.Miscellaneous.ErrorHandler;
import com.school.Cais.Models.Account;
import com.school.Cais.Models.CartItem;
import com.school.Cais.Models.Product;
import com.school.Cais.Models.Purchase;
import com.school.Cais.Repositories.AccountRepository;
import com.school.Cais.Repositories.ProductRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class  AccountService {
    private final AccountRepository accountRepository;
    private final ProductRepository productRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AccountService(AccountRepository accountRepository, ProductRepository productRepository, PasswordEncoder passwordEncoder, EmailService emailService, AuthenticationManager authenticationManager) {
        this.accountRepository = accountRepository;
        this.productRepository = productRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.authenticationManager = authenticationManager;
    }

    public AccountDTO register(AccountRegisterDTO accountRegisterDTO) {
        if (accountRepository.existsByUsername(accountRegisterDTO.username())) {
            throw new IllegalArgumentException("not allowed");
        }
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

    @Transactional
    public AccountDTO createPurchase(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseGet(() -> ErrorHandler.notFound("Account"));

        List<CartItem> cartItems = new ArrayList<>(account.getCartItems());

        emailService.sendPurchaseEmail(account);

        for (CartItem item : cartItems) {
            Product product = item.getProduct();
            int newStock = product.getStock() - item.getQuantity();
            if (newStock < 0) {
                throw new RuntimeException("Not enough stock for product: " + product.getName());
            }
            product.setStock(newStock);
            productRepository.save(product);

            Purchase purchase = new Purchase();
            purchase.setName(item.getName());
            purchase.setAmount(item.getQuantity());
            purchase.setProduct(item.getProduct());
            purchase.setStatus(Constants.DeliveryStatus.PROCESSING);

            account.getRecentOrders().add(purchase);
        }

        account.getCartItems().clear();
        accountRepository.save(account);
        return AccountDTO.fromEntity(account);
    }

    public AccountDTO removeAllFromCart(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseGet(() -> ErrorHandler.notFound("Account"));

        account.getCartItems().clear();
        accountRepository.save(account);
        return AccountDTO.fromEntity(account);
    }

    public AccountDTO login(AccountLoginRequestDTO dto, HttpServletRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.username(), dto.password())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            request.getSession(true)
            .setAttribute(
                    HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                    SecurityContextHolder.getContext()
            );
        } catch (Exception e) {
            System.out.println("Authentication failed: " + e.getClass() + " - " + e.getMessage());
            throw new RuntimeException("invalid username or password");
        }
        Account account = accountRepository.findByUsernameIgnoreCase(dto.username());

//        if (account == null || !passwordEncoder.matches(password, account.getPassword())) {
//            ErrorHandler.wrong("account or password");
//        }

        return AccountDTO.fromEntity(account);
    }
}
