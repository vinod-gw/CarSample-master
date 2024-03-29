package com.arcbees.carsample.server.dispatch;

import com.arcbees.carsample.server.service.ReportService;
import com.arcbees.carsample.shared.dispatch.GetAverageCarRatingByManufacturerAction;
import com.arcbees.carsample.shared.dispatch.GetResults;
import com.arcbees.carsample.shared.dto.ManufacturerRatingDto;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

import javax.inject.Inject;
import java.util.List;

/**
 * A simple report that lists average car rating by manufacturer.
 */
public class GetAverageCarRatingByManufacturerHandler
        extends AbstractActionHandler<GetAverageCarRatingByManufacturerAction, GetResults<ManufacturerRatingDto>> {

    private final ReportService reportService;

    @Inject
    public GetAverageCarRatingByManufacturerHandler(final ReportService reportService) {
        super(GetAverageCarRatingByManufacturerAction.class);
        this.reportService = reportService;
    }

    @Override
    public GetResults<ManufacturerRatingDto> execute(GetAverageCarRatingByManufacturerAction action,
            ExecutionContext context)
            throws ActionException {
        List<ManufacturerRatingDto> results = this.reportService.getAverageCarRatingByManufacturer();
        return new GetResults<ManufacturerRatingDto>(results);
    }

}
