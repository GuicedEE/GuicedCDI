package com.guicedee.cdi.tests;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.guicedee.client.services.lifecycle.IGuiceModule;

/**
 * Shared Guice module for all IGuiceContext-based CDI tests.
 * Binds a {@link TestBean} instance with the {@code @Named("testBean")} qualifier.
 */
public class SharedTestModule extends AbstractModule implements IGuiceModule<SharedTestModule> {

    @Override
    protected void configure() {
        bind(TestBean.class).annotatedWith(Names.named("testBean")).toInstance(new TestBean("test"));
    }

    @Override
    public Integer sortOrder() {
        return 10;
    }
}

