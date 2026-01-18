package com.guicedee.cdi.implementations;

import com.google.inject.gee.NamedAnnotationProvider;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

import java.lang.annotation.Annotation;

/**
 * Converts CDI @Named annotations into Guice Named bindings.
 */
public class NamedAnnotationProvision implements NamedAnnotationProvider
{
    /**
     * Builds a Guice Named annotation from a CDI annotation instance.
     *
     * @param annotationType The CDI annotation instance
     * @return The Guice Named annotation, or null when unsupported
     */
    @Override
    public Named getNamedAnnotation(Annotation annotationType)
    {
        if(annotationType instanceof jakarta.inject.Named name)
            return Names.named(name.value());

        return null;
    }

    /**
     * Builds a Guice Named annotation from a CDI annotation type.
     *
     * @param annotationType The CDI annotation type
     * @return The Guice Named annotation, or null when unsupported
     */
    @Override
    public Named getNamedAnnotation(Class<? extends Annotation> annotationType)
    {
        if(annotationType == jakarta.inject.Named.class)
            return Names.named("bindable");
        return null;
    }
}
