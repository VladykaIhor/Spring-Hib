package com.service.impl;

import com.dao.OrderDao;
import com.entity.Order;
import com.entity.User;
import com.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private final OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Transactional
    @Override
    public void addOrder(Order order) {
        orderDao.addOrder(order);
    }

    @Transactional
    @Override
    public Long getOrderIdByUser(User user) {
        return orderDao.getLastOrderForUser(user).get().getId();
    }
}
