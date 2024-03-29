package com.arcbees.carsample.server.dispatch;

import com.arcbees.carsample.server.dao.RatingDao;
import com.arcbees.carsample.shared.dispatch.GetResult;
import com.arcbees.carsample.shared.dispatch.SaveRatingAction;
import com.arcbees.carsample.shared.domain.Rating;
import com.google.inject.persist.Transactional;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

import javax.inject.Inject;

@Transactional
public class SaveRatingHandler extends AbstractActionHandler<SaveRatingAction, GetResult<Rating>> {
    private final RatingDao ratingDao;

    @Inject
    public SaveRatingHandler(final RatingDao ratingDao) {
        super(SaveRatingAction.class);

        this.ratingDao = ratingDao;
    }

    @Override
    public GetResult<Rating> execute(SaveRatingAction action, ExecutionContext context) throws ActionException {
        Rating rating = ratingDao.put(action.getRating());

        return new GetResult<Rating>(rating);
    }
}
