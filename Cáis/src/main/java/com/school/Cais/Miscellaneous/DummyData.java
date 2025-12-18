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
    private final AccountService accountService;

    @Autowired
    public DummyData(CategoryController categoryController, ProductController productController, SubcategoryController subcategoryController, AccountService accountService) {
        this.categoryController = categoryController;
        this.productController = productController;
        this.subcategoryController = subcategoryController;
        this.accountService = accountService;
    }

    public boolean isStarting() {
        return starting;
    }

    @PostConstruct
    public void createData() {

        accountService.register(
                new AccountRegisterDTO("a", "1", List.of("ADMIN"))
        );
        accountService.register(
                new AccountRegisterDTO("b", "2", List.of("USER"))
        );


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
            new ProductCreateDTO("Brie", "1 kilo", 3.45f, 4, "broken link here", 3L),
            new ProductCreateDTO("Gouda", "1 kilo", 10.4f, 10, "https://president-professionnel.com/wp-content/uploads/2024/02/gouda-wheel-president.png", 1L),
            new ProductCreateDTO("La Vache Qui Rit", "", 5f, 5, "", 3L),
            new ProductCreateDTO("La Vache Qui Rit (Vegan)", "", 4.5f, 2, "https://www.lavachequirit.ca/wp-content/uploads/2024/01/plant-product-single-768x768.png", 3L),
            new ProductCreateDTO("Mozzarella", "italiano", 4.65f, 0, "https://upload.wikimedia.org/wikipedia/commons/thumb/8/83/Three_mozzarellas_on_the_toast.jpg/1280px-Three_mozzarellas_on_the_toast.jpg", 2L),
            new ProductCreateDTO("Mozzarella Tradizionale", "", 6f, 500, "https://upload.wikimedia.org/wikipedia/commons/thumb/8/83/Three_mozzarellas_on_the_toast.jpg/1280px-Three_mozzarellas_on_the_toast.jpg", 2L),
            new ProductCreateDTO("Standard Cheese Slicer", "The best one", 20f, 0, "https://upload.wikimedia.org/wikipedia/commons/thumb/2/22/Kaasschaaf_2022.jpg/1200px-Kaasschaaf_2022.jpg", 4L)
        };
        for(ProductCreateDTO pcd : productCreateDTOS) {
            productController.createProduct(pcd);
        }
        //---------------------------------------------------
        PurchaseCreateDTO[] purchaseCreateDTOS = {
            new PurchaseCreateDTO(2, 2L),
            new PurchaseCreateDTO(1, 2L),
            new PurchaseCreateDTO(4, 6L)
        };
//        for(PurchaseCreateDTO prcd : purchaseCreateDTOS) {
//            purchaseController.createPurchase(prcd);
//        }
        try {
            accountService.addToCart(1L, 2L, 1);
            accountService.addToCart(1L, 1L, 5);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
