package com.guicedee.cdi.implementations;

import com.google.inject.gee.ScopeAnnotationProvider;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * Registers Jakarta CDI scope meta-annotations with Guice's scope annotation checker.
 * This allows Guice to recognize {@code @jakarta.inject.Scope} and
 * {@code @jakarta.enterprise.context.NormalScope} as valid scope markers,
 * enabling {@code bindScope()} for annotations like {@code @Singleton}
 * and {@code @ApplicationScoped}.
 */
public class ScopeAnnotationProvision implements ScopeAnnotationProvider
{
    /**
     * Returns the Jakarta CDI scope meta-annotation types.
     *
     * @return The list of scope annotation marker types
     */
    @Override
    public List<Class<? extends Annotation>> getScopeAnnotations()
    {
        return List.of(
                jakarta.inject.Scope.class,
                jakarta.enterprise.context.NormalScope.class
        );
    }
}

