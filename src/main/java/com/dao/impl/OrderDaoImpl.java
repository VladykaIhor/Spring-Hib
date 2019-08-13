package com.dao.impl;

import com.dao.OrderDao;
import com.entity.Order;
import com.entity.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class OrderDaoImpl implements OrderDao {

    private static final Logger logger = Logger.getLogger(OrderDaoImpl.class);

    @Autowired
    private final SessionFactory getSessionFactory;

    public OrderDaoImpl(SessionFactory getSessionFactory) {
        this.getSessionFactory = getSessionFactory;
    }

    @Override
    public void addOrder(Order order) {
        Transaction transaction = null;
        try (Session session = getSessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(order + "order can't be added to DB");
        }
    }

    @Override
    public Optional<Order> getLastOrderForUser(User user) {
        try (Session session = getSessionFactory.openSession()) {
            Query query = session.createQuery("from Order where Order.id = :user order by id desc");
            query.setParameter("user", user);
            query.setMaxResults(1);
            Order order = (Order) query.uniqueResult();
            return Optional.of(order);
        } catch (Exception e) {
            logger.error("Try to get basket for user " + user + " was failed!", e);
        }
        return Optional.empty();
    }
}
