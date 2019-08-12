package com.service.impl;

import com.dao.CartDao;
import com.entity.Cart;
import com.entity.Product;
import com.entity.User;
import com.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private final CartDao cartDao;

    public CartServiceImpl(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    @Transactional
    @Override
    public void addProductToCart(User user, Product product) {
        cartDao.addProductToCart(user, product);
    }

    @Transactional
    @Override
    public List<Product> getCartProducts() {
        return cartDao.getProducts();
    }

    @Transactional
    @Override
    public void createCart(User user) {
        cartDao.createCart(user);
    }

    @Transactional
    @Override
    public Optional<Cart> getCartByUser(User user) {
        return cartDao.getCartByUser(user);

    }

    @Transactional
    @Override
    public int getSizeOfACart(Cart cart) {
        return cartDao.getSizeOfACart(cart);
    }
}
