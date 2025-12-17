package com.school.Cais.Services;

import com.school.Cais.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
