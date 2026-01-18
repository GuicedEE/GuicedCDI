package com.guicedee.cdi.implementations;

import com.google.inject.gee.InjectionPointProvider;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

/**
 * Maps CDI-related annotations to Guice injection point types.
 */
public class InjectionPointProvision implements InjectionPointProvider
{
    /**
     * Detects which CDI annotation drives the injection point for the member.
     *
     * @param member The annotated member to inspect
     * @return The matching annotation type, or null when not applicable
     */
    @Override
    public Class<? extends Annotation> injectionPoint(AnnotatedElement member)
    {
        Annotation a = member.getAnnotation(jakarta.inject.Inject.class);
        if (a != null) {
            return jakarta.inject.Inject.class;
        }

        a = member.getAnnotation(jakarta.inject.Named.class);
        if (a != null) {
            return jakarta.inject.Named.class;
        }

        a = member.getAnnotation(jakarta.annotation.PostConstruct.class);
        if (a != null) {
            return jakarta.annotation.PostConstruct.class;
        }

        return null;
    }
}
