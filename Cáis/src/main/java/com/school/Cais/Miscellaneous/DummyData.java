package com.school.Cais.Miscellaneous;

import com.school.Cais.Controllers.*;
import com.school.Cais.DTOs.Accounts.AccountRegisterDTO;
import com.school.Cais.DTOs.Categories.CategoryCreateDTO;
import com.school.Cais.DTOs.Purchases.PurchaseCreateDTO;
import com.school.Cais.DTOs.Products.ProductCreateDTO;
import com.school.Cais.DTOs.Subcategories.SubcategoryCreateDTO;
import com.school.Cais.Services.AccountService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DummyData {
    private boolean starting;
    private final CategoryController categoryController;
    private final ProductController productController;
    private final SubcategoryController subcategoryController;
    private final AccountController accountController;

    @Autowired
    public DummyData(CategoryController categoryController, ProductController productController, SubcategoryController subcategoryController, AccountController accountController) {
        this.categoryController = categoryController;
        this.productController = productController;
        this.subcategoryController = subcategoryController;
        this.accountController = accountController;
    }

    public boolean isStarting() {
        return starting;
    }

    private record cartInfo (long acc, long prod, int amount) {
    }

    @PostConstruct
    public void createData() {

        AccountRegisterDTO[] accountRegisterDTOs = {
            new AccountRegisterDTO("a", "1", List.of("ADMIN")),
            new AccountRegisterDTO("b", "2", List.of("USER"))
        };
        for(AccountRegisterDTO ard : accountRegisterDTOs) {
            accountController.register(ard);
        }
        //----------------------------------------------------
        CategoryCreateDTO[] categoryCreateDTOS = {
            new CategoryCreateDTO("Cheese"),
            new CategoryCreateDTO("Slicer")
        };
        for(CategoryCreateDTO ccd : categoryCreateDTOS) {
            categoryController.createCategory(ccd);
        }
        SubcategoryCreateDTO[] subcategoryCreateDTOS = {
            new SubcategoryCreateDTO("Dutch cheeses", 1L),
            new SubcategoryCreateDTO("Italian cheeses", 1L),
            new SubcategoryCreateDTO("Other cheeses", 1L),
            new SubcategoryCreateDTO("Basic slicers", 2L)
        };
        for(SubcategoryCreateDTO sccd : subcategoryCreateDTOS) {
            subcategoryController.createSubcategory(sccd);
        }
        //----------------------------------------------------
        ProductCreateDTO[] productCreateDTOS = {
            new ProductCreateDTO("Brie", "1 kilo", 3.45f, 40, "broken link here", 3L),
            new ProductCreateDTO("Gouda", "1 kilo", 10.4f, 104, "https://president-professionnel.com/wp-content/uploads/2024/02/gouda-wheel-president.png", 1L),
            new ProductCreateDTO("La Vache Qui Rit", "", 5f, 50, "", 3L),
            new ProductCreateDTO("La Vache Qui Rit (Vegan)", "", 4.5f, 20, "https://www.lavachequirit.ca/wp-content/uploads/2024/01/plant-product-single-768x768.png", 3L),
            new ProductCreateDTO("Mozzarella", "italiano", 4.65f, 3, "https://upload.wikimedia.org/wikipedia/commons/thumb/8/83/Three_mozzarellas_on_the_toast.jpg/1280px-Three_mozzarellas_on_the_toast.jpg", 2L),
            new ProductCreateDTO("Mozzarella Tradizionale", "", 6f, 500, "https://upload.wikimedia.org/wikipedia/commons/thumb/8/83/Three_mozzarellas_on_the_toast.jpg/1280px-Three_mozzarellas_on_the_toast.jpg", 2L),
            new ProductCreateDTO("Standard Cheese Slicer", "The best one", 20f, 1, "https://upload.wikimedia.org/wikipedia/commons/thumb/2/22/Kaasschaaf_2022.jpg/1200px-Kaasschaaf_2022.jpg", 4L)
        };
        for(ProductCreateDTO pcd : productCreateDTOS) {
            productController.createProduct(pcd);
        }
        //---------------------------------------------------
        cartInfo[] cartInfos = {
            new cartInfo(2L, 1L, 1),
            new cartInfo(2L, 5L, 3)
        };
        for(cartInfo ci : cartInfos) {
            accountController.addToCart(ci.acc, ci.prod, ci.amount);
        }
        accountController.checkout(2L);

        cartInfo[] cartInfosNotSold = {
            new cartInfo(2L, 6L, 2),
            new cartInfo(2L, 7L, 1)
        };
        for(cartInfo ci : cartInfosNotSold) {
            accountController.addToCart(ci.acc, ci.prod, ci.amount);
        }
    }
}

