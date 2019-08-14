package com.dao;

import com.entity.Cart;
import com.entity.Product;
import com.entity.User;

import java.util.List;
import java.util.Optional;

public interface CartDao {

    void createCart(User user);

    List<Product> getProducts();

    Optional<Cart> getCartByUser(User user);

    void addProductToCart(User user, Product product);

    int getSizeOfACart(Cart cart);

    void resetBasket(User user);

}
