package com.dao;

import com.entity.Order;
import com.entity.User;

import java.util.Optional;

public interface OrderDao {

    public void addOrder(Order order);

    public Optional<Order> getLastOrderForUser(User user);
}
