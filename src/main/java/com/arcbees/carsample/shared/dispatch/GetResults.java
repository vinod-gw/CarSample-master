package com.arcbees.carsample.shared.dispatch;

import com.arcbees.carsample.shared.dto.Dto;
import com.gwtplatform.dispatch.shared.Result;

import java.util.List;

public class GetResults<T extends Dto> implements Result {
    private List<T> results;

    @SuppressWarnings("unused")
    protected GetResults() {
    }

    public GetResults(List<T> results) {
        this.results = results;
    }

    public List<T> getResults() {
        return results;
    }
}
