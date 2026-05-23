package com.service;

import com.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AuthenticateService {
    private final Session session;
    public AuthenticateService(Session session) {
        this.session=session;
    }


    public User login(String username, String password) {
        Transaction tx=session.beginTransaction();
        User user=session.createQuery("from User where username=:username and password=:password", User.class)
                        .setParameter("username",username)
                        .setParameter("password",password)
                                .getSingleResult();
        tx.commit();
        return user;
    }
}
