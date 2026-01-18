package com.guicedee.cdi.implementations;

import com.google.inject.gee.BindingAnnotationProvider;
import jakarta.inject.Qualifier;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * Supplies CDI qualifier annotations to Guice's binding annotation registry.
 */
public class BindingAnnotationsProvision implements BindingAnnotationProvider
{
    /**
     * Returns the annotation types treated as binding annotations.
     *
     * @return The list of binding annotation marker types
     */
    @Override
    public List<Class<? extends Annotation>> getBindingAnnotations()
    {
        return List.of(Qualifier.class);
    }
}
