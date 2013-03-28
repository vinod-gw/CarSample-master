package com.arcbees.carsample.server.dao;

import com.arcbees.carsample.shared.domain.Rating;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;

public class RatingDao extends BaseDao<Rating> {
    @Inject
    public RatingDao(Provider<EntityManager> entityManagerProvider) {
        super(Rating.class, entityManagerProvider);
    }
}
