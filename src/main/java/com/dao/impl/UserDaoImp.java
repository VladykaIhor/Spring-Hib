package com.dao.impl;

import com.dao.UserDao;
import com.entity.User;
import com.utils.SaltHashingUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImp implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoImp.class);

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        byte[] salt = SaltHashingUtil.getSalt();
        user.setSalt(salt);
        user.setPassword(SaltHashingUtil.saltAndHashPassword(user.getPassword(), user.getSalt()));
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> listUsers() {
        @SuppressWarnings("unchecked")
        TypedQuery<User> userTypedQuery = sessionFactory.getCurrentSession().createQuery("from User");
        return userTypedQuery.getResultList();
    }

    @Override
    public void remove(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(getUserById(id).get());
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Error while removing user from DB", e);
        }
    }


    @Override
    public Optional<User> getUserByLogin(String login) {
        try {
            Query query = sessionFactory.getCurrentSession()
                    .createQuery("FROM User where login = :login");
            query.setParameter("login", login);
            User user = (User) query.getResultList().get(0);
            return Optional.of(user);
        } catch (Exception e) {
            logger.error("There was an error while getting user by login", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        try {
            Query query = sessionFactory.getCurrentSession()
                    .createQuery("FROM User where id = :id");
            query.setParameter("id", id);
            User user = (User) query.getSingleResult();
            return Optional.ofNullable(user);
        } catch (Exception e) {
            logger.error("There was an error while getting user by login", e);
        }
        return Optional.empty();
    }

    @Override
    public void updateUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction;
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Failed to update user, ", e);
        }
    }
}
