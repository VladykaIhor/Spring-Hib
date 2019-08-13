package com.controller;


import com.entity.Code;
import com.entity.Order;
import com.entity.User;
import com.service.CodeService;
import com.service.MailService;
import com.service.OrderService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Optional;

@Controller
@SessionAttributes("order")
public class OrderController {

    private final OrderService orderService;
    private final MailService mailService;
    private final CodeService codeService;
    private final UserService userService;

    @Autowired
    public OrderController(OrderService orderService, MailService mailService,
                           CodeService codeService, UserService userService) {
        this.orderService = orderService;
        this.mailService = mailService;
        this.codeService = codeService;
        this.userService = userService;
    }

    @ModelAttribute("order")
    public Order getOrder() {
        return new Order();

    }

    @PostMapping(value = "/users/orderConfirmation")
    public String getOrderPage(Model model) {
        return "orderConfirmation";
    }

    @GetMapping(value = "/user/order_confirmation")
    public String orderCreator(@SessionAttribute("user") User user,
                               @RequestParam("name") String name,
                               @RequestParam("surname") String surname,
                               @RequestParam("country") String country,
                               @RequestParam("state") String state,
                               @RequestParam("city") String city,
                               @RequestParam("phoneNumber") String phoneNumber,
                               @RequestParam("zip") String zip,
                               @ModelAttribute("order") Order order) {
        order.setName(name);
        order.setSurname(surname);
        order.setCountry(country);
        order.setState(state);
        order.setCity(city);
        order.setPhoneNumber(phoneNumber);
        order.setZip(zip);
        orderService.addOrder(order);
        Code code = new Code(order.getId());
        mailService.sendConfirmationCode(user.getEmail(), code.getCode());
        return "orderPayment";
    }

    @GetMapping("/confirm_order")
    public String orderConfirmation() {
        return "orderConfirmation";
    }

    @PostMapping("/confirm_order")
    public String confirmOrder(@SessionAttribute("user") User user,
                               @RequestParam("code") String codeFromPage,
                               Model model) {

        Long orderIdByUser = orderService.getOrderIdByUser(user);
        Optional<Code> code = codeService.getCode(orderIdByUser);
        if (code.equals(codeFromPage)) {
            model.addAttribute("success!");
            return "index";
        } else {
            model.addAttribute("fail");
            return "orderConfirmation";
        }
    }
}
