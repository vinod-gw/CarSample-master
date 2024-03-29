package com.arcbees.carsample.server.dispatch;

import com.arcbees.carsample.server.dao.CarDao;
import com.arcbees.carsample.shared.dispatch.GetResult;
import com.arcbees.carsample.shared.dispatch.SaveCarAction;
import com.arcbees.carsample.shared.domain.Car;
import com.google.inject.persist.Transactional;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

import javax.inject.Inject;

@Transactional
public class SaveCarHandler extends AbstractActionHandler<SaveCarAction, GetResult<Car>> {
    private final CarDao carDao;

    @Inject
    public SaveCarHandler(final CarDao carDao) {
        super(SaveCarAction.class);

        this.carDao = carDao;
    }

    @Override
    public GetResult<Car> execute(SaveCarAction action, ExecutionContext context)
            throws ActionException {
        Car car = carDao.put(action.getCar());

        return new GetResult<Car>(car);
    }
}
