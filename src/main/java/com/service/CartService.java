package com.service;

import com.entity.Cart;
import com.entity.Product;
import com.entity.User;

import java.util.List;
import java.util.Optional;

public interface CartService {

    void addProductToCart(User user, Product product);

    List<Product> getCartProducts();

    void createCart(User user);

    Optional<Cart> getCartByUser(User user);

    int getSizeOfACart(Cart cart);

}
