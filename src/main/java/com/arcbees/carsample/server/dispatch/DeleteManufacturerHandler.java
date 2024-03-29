package com.arcbees.carsample.server.dispatch;

import com.arcbees.carsample.server.dao.ManufacturerDao;
import com.arcbees.carsample.shared.dispatch.DeleteManufacturerAction;
import com.arcbees.carsample.shared.dispatch.NoResults;
import com.google.inject.persist.Transactional;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

import javax.inject.Inject;
import javax.inject.Provider;

@Transactional
public class DeleteManufacturerHandler extends AbstractActionHandler<DeleteManufacturerAction, NoResults> {
    private final Provider<ManufacturerDao> manufacturerDaoProvider;

    @Inject
    public DeleteManufacturerHandler(final Provider<ManufacturerDao> manufacturerDaoProvider) {
        super(DeleteManufacturerAction.class);

        this.manufacturerDaoProvider = manufacturerDaoProvider;
    }

    @Override
    public NoResults execute(DeleteManufacturerAction action, ExecutionContext context) throws ActionException {
        manufacturerDaoProvider.get().delete(action.getManufacturer());

        return new NoResults();
    }
}
