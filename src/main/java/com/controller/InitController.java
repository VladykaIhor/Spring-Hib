package com.controller;

import com.entity.Product;
import com.entity.User;
import com.service.ProductService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@SessionAttributes("user")
public class InitController {

    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public InitController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/init")
    public String init() {
        User admin = new User("test", "test@test", "test", "admin");
        User user = new User("user", "yngwar95@gmail.com", "user", "user");
        userService.add(admin);
        userService.add(user);
        return "index";
    }

    @ModelAttribute("user")
    public User setUpUserForm() {
        return new User();
    }

    @GetMapping("/")
    public String login(@AuthenticationPrincipal User user) {
        if (user == null) {
            return "redirect:/login";
        } else if ("ROLE_ADMIN".equals(user.getRole())) {
            return "redirect:/admin/user";
        } else {
            return "redirect:/user/products";
        }
    }

    @GetMapping(path = {"/logout"})
    public String logout() {
        return "redirect:/";
    }
}
