package com.dao.impl;

import com.dao.ProductDao;
import com.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@SuppressWarnings("unchecked")
@Repository
public class ProductDaoImpl implements ProductDao {

    private final SessionFactory getSessionFactory;

    public ProductDaoImpl(SessionFactory getSessionFactory) {
        this.getSessionFactory = getSessionFactory;
    }


    @Override
    public List<Product> getAll() {
        TypedQuery<Product> productTypedQuery = getSessionFactory.getCurrentSession().createQuery("FROM Product");
        List<Product> resultList = productTypedQuery.getResultList();
        return resultList;
    }

    @Override
    public void remove(Long id) {
        getSessionFactory.getCurrentSession().delete(getById(id).get());
    }

    @Override
    public Optional<Product> getById(Long id) {
        try (Session session = getSessionFactory.openSession()) {
            Product product = session.get(Product.class, id);
            if (Objects.isNull(product)) {
                return Optional.empty();
            } else {
                return Optional.of(product);
            }
        } catch (Exception e) {

        }
        return Optional.empty();
    }

    @Override
    public void add(Product product) {
        getSessionFactory.getCurrentSession().save(product);
    }

    @Override
    public void update(Product product) {
        try (Session session = getSessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update("product", product);
            transaction.commit();
        } catch (Exception e) {

        }
    }
}


