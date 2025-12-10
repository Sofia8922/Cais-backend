package com.school.Cais.Services;

import com.school.Cais.DTOs.Purchases.PurchaseCreateDTO;
import com.school.Cais.DTOs.Purchases.PurchaseDTO;
import com.school.Cais.Miscellaneous.Constants;
import com.school.Cais.Miscellaneous.ErrorHandler;
import com.school.Cais.Models.Account;
import com.school.Cais.Models.CartItem;
import com.school.Cais.Models.Product;
import com.school.Cais.Models.Purchase;
import com.school.Cais.Repositories.AccountRepository;
import com.school.Cais.Repositories.ProductRepository;
import com.school.Cais.Repositories.PurchaseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseService {

    //do we really need this anymore??
//    private final PurchaseRepository purchaseRepository;
//    private final ProductRepository productRepository;
//    private final ProductService productService;
    private final AccountRepository accountRepository;

    @Autowired
    public PurchaseService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }



}
