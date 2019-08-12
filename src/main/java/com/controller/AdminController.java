package com.controller;

import com.entity.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "admin/users", method = RequestMethod.GET)
    public String allUsers(Model model) {
        model.addAttribute("allUsers", userService.listUsers());
        return "users";
    }

    @PostMapping(path = {"/admin/remove/users"})
    public String deleteUser(@RequestParam("id") Long id) {
        userService.remove(id);
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "admin/register", method = RequestMethod.GET)
    public String registerPage(Model model) {
        return "register";
    }

    @RequestMapping(value = "admin/register", method = RequestMethod.POST)
    public String register(@RequestParam("login") String login,
                           @RequestParam("email") String email,
                           @RequestParam("password") String password,
                           @RequestParam("repeatPassword") String repeatPassword,
                           @RequestParam("role") String role, Model model) {
        if (password.equals(repeatPassword) && login != null) {
            User user = new User(login, email, password, role);
            userService.add(user);
        } else {

        }
        return "redirect:/admin/users";
    }

    @GetMapping(value = "/admin/edit/users")
    public String getUserEditPage(@RequestParam("id") Long id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("login", userService.getUserById(id).get().getLogin());
        model.addAttribute("password", userService.getUserById(id).get().getPassword());
        return "redirect:/admin/users";
    }
}
