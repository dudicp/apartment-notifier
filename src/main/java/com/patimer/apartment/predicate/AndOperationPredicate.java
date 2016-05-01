package com.patimer.apartment.predicate;

import org.apache.commons.lang.Validate;

import java.util.Set;
import java.util.function.Predicate;

public class AndOperationPredicate<T> implements Predicate<T>
{
    private Set<Predicate<T>> predicates;

    public AndOperationPredicate(Set<Predicate<T>> predicates)
    {
        Validate.notEmpty(predicates);
        this.predicates = predicates;
    }

    @Override
    public boolean test(T t)
    {
        for(Predicate<T> predicate : predicates)
        {
            if(!predicate.test(t))
                return false;
        }

        return true;
    }
}
