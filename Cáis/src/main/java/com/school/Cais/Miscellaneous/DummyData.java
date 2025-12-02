package com.school.Cais.Miscellaneous;

import com.school.Cais.Controllers.PurchaseController;
import com.school.Cais.Controllers.ProductController;
import com.school.Cais.DTOs.Purchases.PurchaseCreateDTO;
import com.school.Cais.DTOs.Products.ProductCreateDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DummyData {
    private boolean starting;
    private final ProductController productController;
    private final PurchaseController purchaseController;

    @Autowired
    public DummyData(ProductController productController, PurchaseController purchaseController) {
        this.productController = productController;
        this.purchaseController = purchaseController;
    }

    public boolean isStarting() {
        return starting;
    }

    @PostConstruct
    public void createData() {
        ProductCreateDTO[] productCreateDTOS = {
            new ProductCreateDTO("Brie", "1 kilo", 3.45f, 4, "broken link here"),
            new ProductCreateDTO("Gouda", "1 kilo", 10.4f, 10, "https://president-professionnel.com/wp-content/uploads/2024/02/gouda-wheel-president.png"),
            new ProductCreateDTO("La Vache Qui Rit", "", 5f, 5, ""),
            new ProductCreateDTO("La Vache Qui Rit (Vegan)", "", 4.5f, 2, "https://www.lavachequirit.ca/wp-content/uploads/2024/01/plant-product-single-768x768.png"),
            new ProductCreateDTO("Mozzarella", "italiano", 4.65f, 10, "https://1.bp.blogspot.com/-YJ0fMjTkhk8/Uuejy9zgm2I/AAAAAAAAGFQ/DulK9iYhezc/s1600/Mozzarella.jpg"),
            new ProductCreateDTO("Mozzarella Tradizionale", "", 6f, 10, "https://1.bp.blogspot.com/-YJ0fMjTkhk8/Uuejy9zgm2I/AAAAAAAAGFQ/DulK9iYhezc/s1600/Mozzarella.jpg")
        };
        for(ProductCreateDTO pcd : productCreateDTOS) {
            productController.createProduct(pcd);
        }
        //---------------------------------------------------
        PurchaseCreateDTO[] purchaseCreateDTOS = {
                new PurchaseCreateDTO(2, 1L),
                new PurchaseCreateDTO(1, 1L)
        };
        for(PurchaseCreateDTO prcd : purchaseCreateDTOS) {
            purchaseController.createPurchase(prcd);
        }
    }
}
