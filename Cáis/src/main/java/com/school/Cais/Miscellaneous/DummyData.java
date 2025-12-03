package com.school.Cais.Miscellaneous;

import com.school.Cais.Controllers.CategoryController;
import com.school.Cais.Controllers.PurchaseController;
import com.school.Cais.Controllers.ProductController;
import com.school.Cais.Controllers.SubcategoryController;
import com.school.Cais.DTOs.Categories.CategoryCreateDTO;
import com.school.Cais.DTOs.Purchases.PurchaseCreateDTO;
import com.school.Cais.DTOs.Products.ProductCreateDTO;
import com.school.Cais.DTOs.Subcategories.SubcategoryCreateDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DummyData {
    private boolean starting;
    private final CategoryController categoryController;
    private final ProductController productController;
    private final PurchaseController purchaseController;
    private final SubcategoryController subcategoryController;

    @Autowired
    public DummyData(CategoryController categoryController, ProductController productController, PurchaseController purchaseController, SubcategoryController subcategoryController) {
        this.categoryController = categoryController;
        this.productController = productController;
        this.purchaseController = purchaseController;
        this.subcategoryController = subcategoryController;
    }

    public boolean isStarting() {
        return starting;
    }

    @PostConstruct
    public void createData() {
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
            new ProductCreateDTO("Mozzarella", "italiano", 4.65f, 0, "https://1.bp.blogspot.com/-YJ0fMjTkhk8/Uuejy9zgm2I/AAAAAAAAGFQ/DulK9iYhezc/s1600/Mozzarella.jpg", 2L),
            new ProductCreateDTO("Mozzarella Tradizionale", "", 6f, 500, "https://1.bp.blogspot.com/-YJ0fMjTkhk8/Uuejy9zgm2I/AAAAAAAAGFQ/DulK9iYhezc/s1600/Mozzarella.jpg", 2L),
            new ProductCreateDTO("Standard Cheese Slicer", "The best one", 20f, 0, "", 4L)
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
        for(PurchaseCreateDTO prcd : purchaseCreateDTOS) {
            purchaseController.createPurchase(prcd);
        }
    }
}
