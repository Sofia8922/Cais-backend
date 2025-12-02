package com.school.Cais.Miscellaneous;

import com.school.Cais.Controllers.ProductController;
import com.school.Cais.DTOs.Products.ProductCreateDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DummyData {
    private boolean starting;
    private final ProductController productController;

    @Autowired
    public DummyData(ProductController productController)
    {
        this.productController = productController;
    }

    public boolean isStarting() {
        return starting;
    }

    @PostConstruct
    public void createData() {
        ProductCreateDTO[] productCreateDTOS = {
                new ProductCreateDTO("Brie"),
                new ProductCreateDTO("Gouda"),
                new ProductCreateDTO("La Vache Qui Rit"),
                new ProductCreateDTO("La Vache Qui Rit (Vegan)"),
                new ProductCreateDTO("Mozzarella"),
                new ProductCreateDTO("Mozzarella Tradizionale")
        };
        for(ProductCreateDTO acd : productCreateDTOS) {
            productController.createProduct(acd);
        }
    }
}
