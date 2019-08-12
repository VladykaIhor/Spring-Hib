package com.service;

import com.entity.Order;
import com.entity.User;

public interface OrderService {

    void addOrder(Order order);

    Long getOrderIdByUser(User user);
}
