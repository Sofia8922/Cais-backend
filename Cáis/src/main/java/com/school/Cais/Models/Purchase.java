package com.school.Cais.Models;

import com.school.Cais.Miscellaneous.Constants;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Purchase {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private int amount;
    @NotNull
    private Constants.DeliveryStatus status;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Long getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Constants.DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(Constants.DeliveryStatus status) {
        this.status = status;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
