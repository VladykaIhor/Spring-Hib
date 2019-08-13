package com.dao.impl;

import com.dao.CartDao;
import com.entity.Cart;
import com.entity.Product;
import com.entity.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class CartDaoImpl implements CartDao {

    private static final Logger logger = Logger.getLogger(CartDaoImpl.class);

    private final SessionFactory getSessionFactory;

    public CartDaoImpl(SessionFactory getSessionFactory) {
        this.getSessionFactory = getSessionFactory;
    }

    @Override
    public void createCart(User user) {
        Transaction transaction = null;
        Cart cart = new Cart(user, new ArrayList<>());
        try (Session session = getSessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(cart);
            transaction.commit();
            logger.info("Created a shopping cart for " + user);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(cart + "can't be updated in DB", e);
        }
    }

    @Override
    public List<Product> getProducts() {
        try (Session session = getSessionFactory.openSession()) {
            Query query = session.createQuery("from Product where id =: id");
            List<Product> list = query.list();
            return list;
        } catch (Exception e) {
            logger.error("Failed to get all products", e);
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<Cart> getCartByUser(User user) {
        try (Session session = getSessionFactory.openSession()) {
            Query query = session.createQuery("from Cart where user = :user");
            query.setParameter("user", user);
            Cart cart = (Cart) query.getResultList().get(0);
            return Optional.of(cart);
        } catch (Exception e) {
            logger.error("Error when can try to get basket for user " + user, e);
        }
        return Optional.empty();
    }

    @Override
    public void addProductToCart(User user, Product product) {
        Transaction transaction = null;
        Optional<Cart> cart = getCartByUser(user);
        try (Session session = getSessionFactory.openSession()) {
            transaction = session.beginTransaction();
            cart.get().setProducts(product);
            session.update(cart.get());
            transaction.commit();
            logger.info("Product for user basket " + user + " added to DB");
        } catch (Exception e) {
            logger.error("Failed to add product to basket");
        }
    }

    @Override
    public int getSizeOfACart(Cart cart) {
        return cart.getSizeOfCart();
    }

    @Override
    public void resetBasket(User user) {
        Transaction transaction = null;
        Cart cart = getCartByUser(user).get();
        try (Session session = getSessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.remove(cart);
            transaction.commit();
            logger.info("The cart for user was reset");
        } catch (Exception e) {
            if (transaction != null) {
                logger.error("Failed to reset the cart for user", e);
                transaction.rollback();
            }
        }
    }
}
