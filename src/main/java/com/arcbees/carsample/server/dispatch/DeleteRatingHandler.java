package com.arcbees.carsample.server.dispatch;

import com.arcbees.carsample.server.dao.RatingDao;
import com.arcbees.carsample.shared.dispatch.DeleteRatingAction;
import com.arcbees.carsample.shared.dispatch.NoResults;
import com.google.inject.persist.Transactional;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

import javax.inject.Inject;

@Transactional
public class DeleteRatingHandler extends AbstractActionHandler<DeleteRatingAction, NoResults> {
    private final RatingDao ratingDao;

    @Inject
    public DeleteRatingHandler(final RatingDao ratingDao) {
        super(DeleteRatingAction.class);

        this.ratingDao = ratingDao;
    }

    @Override
    public NoResults execute(DeleteRatingAction action, ExecutionContext context) throws ActionException {
        ratingDao.delete(action.getRating());

        return new NoResults();
    }
}
