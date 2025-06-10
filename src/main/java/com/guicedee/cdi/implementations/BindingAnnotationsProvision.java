package com.guicedee.cdi.implementations;

import com.google.inject.gee.BindingAnnotationProvider;
import jakarta.inject.Qualifier;

import java.lang.annotation.Annotation;
import java.util.List;

public class BindingAnnotationsProvision implements BindingAnnotationProvider
{
    @Override
    public List<Class<? extends Annotation>> getBindingAnnotations()
    {
        return List.of(Qualifier.class);
    }
}
