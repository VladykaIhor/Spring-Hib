package com.controller;

import com.entity.Product;
import com.entity.User;
import com.service.ProductService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @ModelAttribute("user")
    public User setUpUserForm() {
        return new User();
    }

    @GetMapping(value = "/")
    public String init() {
        return "index";
    }

    @GetMapping(value = "/asd")
    public String init11() {
        productService.add(new Product("shtuka", "shtuka", 100.0));
        productService.add(new Product("shtuka2", "shtuka1", 100.0));

        userService.add(new User("test", "test@test", "test", "admin"));
        userService.add(new User("asd", "asd@asd", "asd", "user"));
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute("user") User user, Model model) {
        Optional<User> userFromBase = userService.authentication(user);
        model.addAttribute(userFromBase);
        if (userFromBase.isPresent()) {
            if (userFromBase.get().getRole().equals("admin")) {
                return "redirect:/admin/users";
            } else if (userFromBase.get().getRole().equals("user")) {
                model.addAttribute("products", productService.getAll());
                return "productsUser";
            }
        }
        return "index";
    }

    @GetMapping(path = {"/logout"})
    public String logout() {
        return "redirect:/";
    }
}