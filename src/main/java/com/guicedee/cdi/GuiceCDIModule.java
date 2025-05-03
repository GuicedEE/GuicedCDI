package com.guicedee.cdi;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.guicedee.guicedinjection.interfaces.IGuiceModule;

/**
 * A Guice module that registers the CDI bean manager.
 * This module is responsible for making the GuiceCDIBeanManager available for injection.
 * 
 * This module is automatically loaded by the GuicedEE framework through the ServiceLoader mechanism,
 * as it is registered as a provider of IGuiceModule in the module-info.java file.
 * 
 * The GuiceCDIBeanManager is registered as a singleton, so the same instance will be injected
 * everywhere it is requested.
 * 
 * Usage:
 * You don't need to explicitly install this module, as it's automatically loaded.
 * Just inject the GuiceCDIBeanManager where you need it:
 * 
 * {@code @Inject private GuiceCDIBeanManager beanManager;}
 */
public class GuiceCDIModule extends AbstractModule implements IGuiceModule<GuiceCDIModule> {

    @Override
    protected void configure() {
        bind(GuiceCDIBeanManager.class).in(Singleton.class);
        bind(ICDIProvider.class).to(GuiceCDIProvider.class).in(Singleton.class);



        //todo jakarta.enterprise.inject.spi.CDI.setCDIProvider(); in the guice module
    }

    @Override
    public Integer sortOrder() {
        return Integer.MAX_VALUE - 200; // Load after most modules but before some
    }
}
