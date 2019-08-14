package com.controller;

import com.entity.Cart;
import com.entity.Product;
import com.entity.User;
import com.service.CartService;
import com.service.ProductService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Optional;

@Controller
@SessionAttributes({"user"})
public class CartController {

    private final CartService cartService;
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public CartController(CartService cartService, ProductService productService, UserService userService) {
        this.cartService = cartService;
        this.productService = productService;
        this.userService = userService;
    }

    @PostMapping(value = {"/user/products/add_to_cart"})
    public String addToCartButton(@AuthenticationPrincipal User user,
                                  @RequestParam("id") Long id) {
        Optional<Product> product = productService.getById(id);
        if (cartService.getCartByUser(user).isPresent()) {
            cartService.addProductToCart(user, product.get());
        } else {
            cartService.createCart(user);
            cartService.addProductToCart(user, product.get());
        }
        return "redirect:/user/products";
    }
}
