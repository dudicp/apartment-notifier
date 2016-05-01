package com.patimer.apartment.model.setting;

import com.patimer.apartment.searcher.SearcherType;

public class ApartmentSearcherEntity
{
    private String suffix;
    private SearcherType searcherType;

    public ApartmentSearcherEntity(String suffix, SearcherType searcherType)
    {
        this.suffix = suffix;
        this.searcherType = searcherType;
    }

    public String getSuffix()
    {
        return suffix;
    }

    public SearcherType getSearcherType()
    {
        return searcherType;
    }
}
