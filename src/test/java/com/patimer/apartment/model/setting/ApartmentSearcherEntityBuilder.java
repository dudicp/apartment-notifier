package com.patimer.apartment.model.setting;

import com.patimer.apartment.searcher.SearcherType;

public class ApartmentSearcherEntityBuilder
{
    private String suffix = "local/רחובות/רחובות%20המדע";
    private SearcherType searcherType = SearcherType.Madlan;

    public ApartmentSearcherEntityBuilder withSuffix(String suffix)
    {
        this.suffix = suffix;
        return this;
    }

    public ApartmentSearcherEntityBuilder withSearcherType(SearcherType searcherType)
    {
        this.searcherType = searcherType;
        return this;
    }

    public ApartmentSearcherEntity build()
    {
        return new ApartmentSearcherEntity(suffix, searcherType);
    }
}
