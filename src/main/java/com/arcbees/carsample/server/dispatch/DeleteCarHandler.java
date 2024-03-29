package com.arcbees.carsample.server.dispatch;

import com.arcbees.carsample.server.dao.CarDao;
import com.arcbees.carsample.shared.dispatch.DeleteCarAction;
import com.arcbees.carsample.shared.dispatch.NoResults;
import com.google.inject.persist.Transactional;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

import javax.inject.Inject;

@Transactional
public class DeleteCarHandler extends AbstractActionHandler<DeleteCarAction, NoResults> {
    private final CarDao carDao;

    @Inject
    public DeleteCarHandler(final CarDao carDao) {
        super(DeleteCarAction.class);

        this.carDao = carDao;
    }

    @Override
    public NoResults execute(DeleteCarAction action, ExecutionContext context) throws ActionException {
        carDao.delete(action.getCar());

        return new NoResults();
    }
}
