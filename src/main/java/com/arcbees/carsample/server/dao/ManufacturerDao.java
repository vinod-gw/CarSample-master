package com.arcbees.carsample.server.dao;

import com.arcbees.carsample.shared.domain.Manufacturer;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;

public class ManufacturerDao extends BaseDao<Manufacturer> {
    @Inject
    public ManufacturerDao(Provider<EntityManager> entityManagerProvider) {
        super(Manufacturer.class, entityManagerProvider);
    }
}
