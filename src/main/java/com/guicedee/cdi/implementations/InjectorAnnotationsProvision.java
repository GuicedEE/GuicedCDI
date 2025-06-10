package com.guicedee.cdi.implementations;

import com.google.inject.gee.InjectorAnnotationsProvider;

import java.lang.annotation.Annotation;

public class InjectorAnnotationsProvision implements InjectorAnnotationsProvider
{
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
