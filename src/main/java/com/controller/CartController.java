package com.controller;

import com.entity.Cart;
import com.entity.Product;
import com.entity.User;
import com.service.CartService;
import com.service.ProductService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Optional;

@Controller
@SessionAttributes({"user"})
public class CartController {

    @Autowired
    private final CartService cartService;
    private final ProductService productService;
    private final UserService userService;

    public CartController(CartService cartService, ProductService productService, UserService userService) {
        this.cartService = cartService;
        this.productService = productService;
        this.userService = userService;
    }

    @PostMapping(value = {"/user/products/add_to_cart"})
    public String addToCartButton(@SessionAttribute("user") User user,
                                  @RequestParam("id") Long id) {
        Optional<User> currentUser = userService.getUserByLogin(user.getLogin());
        Optional<Product> product = productService.getById(id);
        if (cartService.getCartByUser(currentUser.get()).isPresent()) {
            Optional<Cart> cartByUser = cartService.getCartByUser(currentUser.get());
        } else {
            cartService.createCart(currentUser.get());
        }
        cartService.addProductToCart(currentUser.get(), product.get());
        Cart cart = cartService.getCartByUser(currentUser.get()).get();
        int sizeOfACart = cartService.getSizeOfACart(cart);
        return "redirect:/user/products";
    }
}
