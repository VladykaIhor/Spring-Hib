package com.dao.impl;

import com.dao.CodeDao;
import com.entity.Code;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class CodeDaoImpl implements CodeDao {

    private static final Logger logger = Logger.getLogger(CodeDaoImpl.class);

    @Autowired
    private final SessionFactory getSessionFactory;

    public CodeDaoImpl(SessionFactory getSessionFactory) {
        this.getSessionFactory = getSessionFactory;
    }


    @Override
    public void addCode(Code code) {
        Transaction transaction = null;
        try (Session session = getSessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(code);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(code + "can't be added in DB", e);
        }
    }

    @Override
    public Optional<Code> getCode(Long id) {
        try (Session session = getSessionFactory.openSession()) {
            Code code = session.get(Code.class, id);
            return Optional.of(code);
        } catch (Exception e) {
            logger.error("Try to get code by id was failed!", e);
        }
        return Optional.empty();
    }
}
