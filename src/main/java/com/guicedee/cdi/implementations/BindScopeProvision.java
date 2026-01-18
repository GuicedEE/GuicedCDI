package com.guicedee.cdi.implementations;

import com.google.inject.gee.BindScopeProvider;
import com.google.inject.Binder;

import static com.google.inject.Scopes.SINGLETON;

/**
 * Registers CDI scope annotations with Guice's singleton scope.
 */
public class BindScopeProvision implements BindScopeProvider
{
    /**
     * Binds CDI scope annotations to Guice's singleton scope.
     *
     * @param binder The Guice binder to configure
     */
    @Override
    public void bindScope(Binder binder)
    {
        binder.bindScope(jakarta.inject.Singleton.class, SINGLETON);
        binder.bindScope(jakarta.enterprise.context.ApplicationScoped.class, SINGLETON);
    }
}
