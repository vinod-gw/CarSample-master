package com.arcbees.carsample.server.dao;

import com.arcbees.carsample.shared.domain.User;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UserDao extends BaseDao<User> {
    @Inject
    public UserDao(Provider<EntityManager> entityManagerProvider) {
        super(User.class, entityManagerProvider);
    }

    public User findByUsername(String username) {
        Query query = entityManager().createQuery("select u from User u where u.username = :username");
        query.setParameter("username", username);

        return (User) query.getSingleResult();
    }
}
