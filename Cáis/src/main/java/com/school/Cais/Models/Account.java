package com.school.Cais.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String username;
    private String email;
    @NotBlank
    private String password;
    private String address;
    private String phoneNumber;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "account_favorites",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> favorites = new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "account_recent_orders",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> recentOrders = new ArrayList<>();
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    public Account() {
    }

    public Account(String username, String password, List<String> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public List<Product> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Product> favorites) {
        this.favorites = favorites;
    }

    public List<Product> getRecentOrders() {
        return recentOrders;
    }

    public void setRecentOrders(List<Product> recentOrders) {
        this.recentOrders = recentOrders;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public void addToCart(Product product, int amount) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getId().equals(product.getId())) {
                item.setQuantity(item.getQuantity() + amount);
                return;
            }
        }
        cartItems.add(new CartItem(this, product, amount));
    }

    public void removeFromCart(Product product, int amount) {
        cartItems.removeIf(item -> {
            if (item.getProduct().getId().equals(product.getId())) {
                if (item.getQuantity() > amount) {
                    item.setQuantity(item.getQuantity() - amount);
                    return false;
                } else {
                    return true;
                }
            }
            return false;
        });
    }

    public void addToFavorites(Product product) {
        if (!favorites.contains(product)) {
            favorites.add(product);
        }
    }

    public void removeFromFavorites(Product product) {
        favorites.remove(product);
    }
}
