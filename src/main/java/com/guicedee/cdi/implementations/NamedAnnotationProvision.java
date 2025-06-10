package com.guicedee.cdi.implementations;

import com.google.inject.gee.NamedAnnotationProvider;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

import java.lang.annotation.Annotation;

public class NamedAnnotationProvision implements NamedAnnotationProvider
{
    @Override
    public Named getNamedAnnotation(Annotation annotationType)
    {
        if(annotationType instanceof jakarta.inject.Named name)
            return Names.named(name.value());

        return null;
    }

    @Override
    public Named getNamedAnnotation(Class<? extends Annotation> annotationType)
    {
        if(annotationType == jakarta.inject.Named.class)
            return Names.named("bindable");
        return null;
    }
}
