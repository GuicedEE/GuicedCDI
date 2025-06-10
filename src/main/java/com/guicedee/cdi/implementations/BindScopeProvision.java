package com.guicedee.cdi.implementations;

import com.google.inject.gee.BindScopeProvider;
import com.google.inject.Binder;

import static com.google.inject.Scopes.SINGLETON;

public class BindScopeProvision implements BindScopeProvider
{
    @Override
    public void bindScope(Binder binder)
    {
        binder.bindScope(jakarta.inject.Singleton.class, SINGLETON);
        binder.bindScope(jakarta.enterprise.context.ApplicationScoped.class, SINGLETON);
    }
}
