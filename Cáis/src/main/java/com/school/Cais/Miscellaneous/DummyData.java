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
            new AccountRegisterDTO("b", "2", List.of("USER")),
            new AccountRegisterDTO("c", "3", List.of("USER"))
        };
        for(AccountRegisterDTO ard : accountRegisterDTOs) {
            accountController.register(ard);
        }
        //----------------------------------------------------
        CategoryCreateDTO[] categoryCreateDTOS = {
            new CategoryCreateDTO("\uD83E\uDDC0 Cheese"),
            new CategoryCreateDTO("Slicer")
        };
        for(CategoryCreateDTO ccd : categoryCreateDTOS) {
            categoryController.createCategory(ccd);
        }
        SubcategoryCreateDTO[] subcategoryCreateDTOS = {
            new SubcategoryCreateDTO("\uD83C\uDDF3\uD83C\uDDF1 Dutch cheeses", 1L),
            new SubcategoryCreateDTO("\uD83C\uDDEE\uD83C\uDDF9 Italian cheeses", 1L),
            new SubcategoryCreateDTO("Other cheeses", 1L),
            new SubcategoryCreateDTO("Basic slicers", 2L)
        };
        for(SubcategoryCreateDTO sccd : subcategoryCreateDTOS) {
            subcategoryController.createSubcategory(sccd);
        }
        //----------------------------------------------------
        ProductCreateDTO[] productCreateDTOS = {
            new ProductCreateDTO("Brie", "1 kg. Brie is a soft cow's-milk cheese named after Brie. The rind is typically eaten, with its flavour depending largely upon the ingredients used and its manufacturing environment.", 3.45f, 40, "broken link here", 3L),
            new ProductCreateDTO("Gouda", "ᛅᛚᛁᛦ᛬ᛘᛁᚾ᛬ᛁᛦᚢ᛬ᛒᚢᚱᚾᛁᛦ᛬ᚠᚱᛁᛅᛚᛋᛁᛦ᛬ᛅᚢᚴ᛬ᛁᛅᚠᚾᛁᛦ᛬ᛅᛏ᛬ᚢᛁᚱᚦᛁᚴᚢ᛬ᛅᚢᚴ᛬ᚱᛁᛏᚢᛘ᛬ᚦᛅᛁᛦ᛬ᛁᛦᚢ᛬ᛅᛚᛁᛦ᛬ᚢᛁᛏᛁ᛬ᚴᚢᛏᛁᛦ᛬ᛅᚢᚴ᛬ᛋᛅᛘᚢᛁᛋᚴᚢ᛬ᛅᚢᚴ᛬ᛋᚴᚢᛚᚢ᛬ᚴᛅᚱᛅ᛬ᚼᚢᛅᛦ᛬ᛏᛁᛚ᛬ᛅᚾᛅᚱᛋ᛬ᛒᚱᚢᚦᚢᚱᛚᛁᚴᛅ ", 10.4f, 104, "https://president-professionnel.com/wp-content/uploads/2024/02/gouda-wheel-president.png", 1L),
            new ProductCreateDTO("La Vache Qui Rit", "", 4.5f, 0, "", 3L),
            new ProductCreateDTO("La Vache Qui Rit (Vegan)", "", 4.5f, 231, "https://www.lavachequirit.ca/wp-content/uploads/2024/01/plant-product-single-768x768.png", 3L),
            new ProductCreateDTO("Mozzarella", "\uD83C\uDDEE\uD83C\uDDF9 italiano \uD83C\uDDEE\uD83C\uDDF9", 4.65f, 3, "https://upload.wikimedia.org/wikipedia/commons/thumb/8/83/Three_mozzarellas_on_the_toast.jpg/1280px-Three_mozzarellas_on_the_toast.jpg", 2L),
            new ProductCreateDTO("Mozzarella Tradizionale", "", 6f, 500, "https://upload.wikimedia.org/wikipedia/commons/thumb/8/83/Three_mozzarellas_on_the_toast.jpg/1280px-Three_mozzarellas_on_the_toast.jpg", 2L),
            new ProductCreateDTO("Standard Cheese Slicer", "チーズナイフは、チーズを切ることに特化したキッチンナイフ。オメガナイフとも呼ばれる。", 20f, 1, "https://upload.wikimedia.org/wikipedia/commons/thumb/2/22/Kaasschaaf_2022.jpg/1200px-Kaasschaaf_2022.jpg", 4L),
            new ProductCreateDTO("Edam cheese", "0.2 kg. Edam is traditionally sold in flat-ended spheres with a pale yellow interior and a coat, or rind, of red paraffin wax. Edam ages and travels well and hardens, instead of spoiling, for an extended time.", 17.5f, 1000, "https://www.thedailymilk.nl/wp-content/uploads/2015/10/Edammer-kaas.jpg", 1L)
        };
        for(ProductCreateDTO pcd : productCreateDTOS) {
            productController.createProduct(pcd);
        }
        //---------------------------------------------------
        cartInfo[] cartInfos = {
            new cartInfo(1L, 2L, 1),
            new cartInfo(1L, 8L, 13),
            new cartInfo(2L, 1L, 1),
            new cartInfo(2L, 5L, 3),
            new cartInfo(2L, 8L, 50),
            new cartInfo(3L, 8L, 20)
        };
        for(cartInfo ci : cartInfos) {
            accountController.addToCart(ci.acc, ci.prod, ci.amount);
        }
        for(long id = 1; id <= 3; id++)
        {
            accountController.checkout(id);
        }
        cartInfo[] cartInfosNotSold = {
           new cartInfo(1L, 2L, 5),
           new cartInfo(2L, 6L, 2),
           new cartInfo(2L, 8L, 15),
           new cartInfo(3L, 7L, 1),
           new cartInfo(3L, 1L, 1),
           new cartInfo(3L, 8L, 20)
        };
        for(cartInfo ci : cartInfosNotSold) {
            accountController.addToCart(ci.acc, ci.prod, ci.amount);
        }
    }
}

