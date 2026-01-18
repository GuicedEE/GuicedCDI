package com.guicedee.cdi.implementations;

import com.google.inject.gee.InjectorAnnotationsProvider;

import java.lang.annotation.Annotation;

/**
 * Identifies annotations that represent injection points.
 */
public class InjectorAnnotationsProvision implements InjectorAnnotationsProvider
{
    /**
     * Checks whether the annotation marks an injection point.
     *
     * @param annotationType The annotation type to check
     * @return true if the annotation is a CDI injection marker, false otherwise
     */
    @Override
    public boolean isInjectorAnnotation(Class<? extends Annotation> annotationType)
    {
        if (annotationType == jakarta.inject.Inject.class)
        {
            return true;
        }

        return false;
    }
}
