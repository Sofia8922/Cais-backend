package com.school.Cais.Miscellaneous;

import com.school.Cais.Controllers.*;
import com.school.Cais.DTOs.Accounts.AccountRegisterDTO;
import com.school.Cais.DTOs.Categories.CategoryCreateDTO;
import com.school.Cais.DTOs.Products.ProductCreateDTO;
import com.school.Cais.DTOs.Subcategories.SubcategoryCreateDTO;
import com.school.Cais.Miscellaneous.Enums.Role;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DummyData {
    private static boolean starting;
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

    public static boolean isStarting() {
        return starting;
    }

    private record cartInfo (long acc, long prod, int amount) {
    }

    @PostConstruct
    public void createData() {
        starting = true;

        AccountRegisterDTO[] accountRegisterDTOs = {
            new AccountRegisterDTO("A", Constants.Password, Constants.UserEmail, List.of(Role.ADMIN)),
            new AccountRegisterDTO("B", Constants.Password, Constants.UserEmail, List.of(Role.USER)),
            new AccountRegisterDTO("C", Constants.Password, Constants.UserEmail, List.of(Role.USER))
        };
        for(AccountRegisterDTO ard : accountRegisterDTOs) {
            accountController.register(ard);
        }
        //----------------------------------------------------
        CategoryCreateDTO[] categoryCreateDTOS = {
            new CategoryCreateDTO("\uD83E\uDDC0 Cheeses"),
            new CategoryCreateDTO("\uD83D\uDD2A Slicers")
        };
        for(CategoryCreateDTO ccd : categoryCreateDTOS) {
            categoryController.createCategory(ccd);
        }
        SubcategoryCreateDTO[] subcategoryCreateDTOS = {
            new SubcategoryCreateDTO("\uD83C\uDDF3\uD83C\uDDF1 Dutch Cheeses", 1L),
            new SubcategoryCreateDTO("\uD83C\uDDEE\uD83C\uDDF9 Italian Cheeses", 1L),
            new SubcategoryCreateDTO("\uD83E\uDDC0 Other Cheeses", 1L),
            new SubcategoryCreateDTO("\uD83D\uDD2A Basic Slicers", 2L)
        };
        for(SubcategoryCreateDTO sccd : subcategoryCreateDTOS) {
            subcategoryController.createSubcategory(sccd);
        }
        //----------------------------------------------------
        ProductCreateDTO[] productCreateDTOS = {
            new ProductCreateDTO("Brie", "1 kg. Brie is a soft cow's-milk cheese named after Brie. The rind is typically eaten, with its flavour depending largely upon the ingredients used and its manufacturing environment.", 3.45f, 40, "broken link here", 3L),
            new ProductCreateDTO("Bavarian Blue Cheese", "", 19.5f, 250, "https://upload.wikimedia.org/wikipedia/commons/2/24/Bavaria_blu_01_WikiCheese_Lokal_K.jpg", 3L),
            new ProductCreateDTO("Gouda", "ᛅᛚᛁᛦ᛬ᛘᛁᚾ᛬ᛁᛦᚢ᛬ᛒᚢᚱᚾᛁᛦ᛬ᚠᚱᛁᛅᛚᛋᛁᛦ᛬ᛅᚢᚴ᛬ᛁᛅᚠᚾᛁᛦ᛬ᛅᛏ᛬ᚢᛁᚱᚦᛁᚴᚢ᛬ᛅᚢᚴ᛬ᚱᛁᛏᚢᛘ᛬ᚦᛅᛁᛦ᛬ᛁᛦᚢ᛬ᛅᛚᛁᛦ᛬ᚢᛁᛏᛁ᛬ᚴᚢᛏᛁᛦ᛬ᛅᚢᚴ᛬ᛋᛅᛘᚢᛁᛋᚴᚢ᛬ᛅᚢᚴ᛬ᛋᚴᚢᛚᚢ᛬ᚴᛅᚱᛅ᛬ᚼᚢᛅᛦ᛬ᛏᛁᛚ᛬ᛅᚾᛅᚱᛋ᛬ᛒᚱᚢᚦᚢᚱᛚᛁᚴᛅ ", 10.4f, 104, "https://president-professionnel.com/wp-content/uploads/2024/02/gouda-wheel-president.png", 1L),
            new ProductCreateDTO("Mascarpone", "", 10f, 470, "https://ichu.com.hk/wp-content/uploads/2025/02/mascarpone-cheese-recipe-1739314048.jpg", 2L),
            new ProductCreateDTO("La Vache Qui Rit", "", 4.5f, 0, "", 3L),
            new ProductCreateDTO("La Vache Qui Rit (Vegan)", "", 4.5f, 231, "https://www.lavachequirit.ca/wp-content/uploads/2024/01/plant-product-single-768x768.png", 3L),
            new ProductCreateDTO("Mozzarella", "\uD83C\uDDEE\uD83C\uDDF9 italiano \uD83C\uDDEE\uD83C\uDDF9", 4.65f, 3, "https://upload.wikimedia.org/wikipedia/commons/thumb/8/83/Three_mozzarellas_on_the_toast.jpg/1280px-Three_mozzarellas_on_the_toast.jpg", 2L),
            new ProductCreateDTO("Mozzarella Tradizionale", "", 6f, 500, "https://upload.wikimedia.org/wikipedia/commons/thumb/8/83/Three_mozzarellas_on_the_toast.jpg/1280px-Three_mozzarellas_on_the_toast.jpg", 2L),
            new ProductCreateDTO("Standard Cheese Slicer", "チーズナイフは、チーズを切ることに特化したキッチンナイフ。オメガナイフとも呼ばれる。", 20f, 1, "https://upload.wikimedia.org/wikipedia/commons/thumb/2/22/Kaasschaaf_2022.jpg/1200px-Kaasschaaf_2022.jpg", 4L),
            new ProductCreateDTO("Edam Cheese", "0.2 kg. Edam is traditionally sold in flat-ended spheres with a pale yellow interior and a coat, or rind, of red paraffin wax. Edam ages and travels well and hardens, instead of spoiling, for an extended time.", 17.5f, 1000, "https://www.thedailymilk.nl/wp-content/uploads/2015/10/Edammer-kaas.jpg", 1L),
            new ProductCreateDTO("Sbinz", "Swiss cheese", 8f, 0, "https://upload.wikimedia.org/wikipedia/commons/b/b3/Sbr_product_0020_bc.jpg", 3L),
            new ProductCreateDTO("Boerenkaas", "", 3f, 0, "https://cheeseinabox.nl/wp-content/uploads/2023/12/Boerenkaas-Jong-1.jpg", 1L),
            new ProductCreateDTO("Emmental", "", 3f, 920, "https://upload.wikimedia.org/wikipedia/commons/3/38/Emmentaler_Premier_Cru.jpg", 3L),
            new ProductCreateDTO("Maasdam", "Holland!", 3.5f, 0, "https://upload.wikimedia.org/wikipedia/commons/2/25/Maasdam-cheese.jpg", 1L),
            new ProductCreateDTO("Old Amsterdam", "", 1.9f, 0, "https://upload.wikimedia.org/wikipedia/commons/1/16/Vintage_Old_Amsterdam_cheese%2C_Amsterdam_%282023%29_01.jpg", 1L),
            new ProductCreateDTO("Harter Käse", "From Germany \uD83C\uDDE9\uD83C\uDDEA", 2.4f, 0, "https://upload.wikimedia.org/wikipedia/commons/0/08/Harzer_K%C3%A4se_12_WikiCheese_Lokal_K.jpg", 3L),
            new ProductCreateDTO("Smoked Austrian", "Not From Germany \uD83C\uDDE9\uD83C\uDDEA", 3f, 921, "https://upload.wikimedia.org/wikipedia/commons/b/b9/Smoked_Austrian_cheese.jpg", 3L),
            new ProductCreateDTO("Schimmelkäse", "Austrian", 5f, 401, "https://upload.wikimedia.org/wikipedia/commons/8/86/Gr%C3%BCnschimmelk%C3%A4se_%C3%96sterkron.jpg", 3L),
        };
        for(ProductCreateDTO pcd : productCreateDTOS) {
            productController.createProduct(pcd);
        }
        //---------------------------------------------------
        cartInfo[] cartInfos = {
            new cartInfo(1L, 3L, 1),
            new cartInfo(1L, 10L, 13),
            new cartInfo(2L, 2L, 1),
            new cartInfo(2L, 7L, 3),
            new cartInfo(2L, 10L, 50),
            new cartInfo(3L, 10L, 20)
        };
        for(cartInfo ci : cartInfos) {
            accountController.addToCart(ci.acc, ci.prod, ci.amount);
        }
        for(long id = 1; id <= 3; id++)
        {
            accountController.checkout(id);
        }
        cartInfo[] cartInfosNotSold = {
           new cartInfo(1L, 3L, 5),
           new cartInfo(2L, 8L, 2),
           new cartInfo(2L, 10L, 15),
           new cartInfo(3L, 9L, 1),
           new cartInfo(3L, 2L, 1),
           new cartInfo(3L, 10L, 20)
        };
        for(cartInfo ci : cartInfosNotSold) {
            accountController.addToCart(ci.acc, ci.prod, ci.amount);
        }

        starting = false;
    }
}

