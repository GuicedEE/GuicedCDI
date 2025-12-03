package com.guicedee.cdi;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.guicedee.client.services.lifecycle.IGuiceModule;
import jakarta.enterprise.inject.spi.CDI;

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
 * This module also sets the Jakarta CDI provider to use our Guice implementation.
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

        // Bind the concrete implementation of GuiceCDIBeanManagerAdapter
        bind(GuiceCDIBeanManagerAdapterImpl.class).in(Singleton.class);
        bind(jakarta.enterprise.inject.spi.BeanManager.class).to(GuiceCDIBeanManagerAdapterImpl.class);

        bind(JakartaCDIProvider.class).in(Singleton.class);
        // Set the Jakarta CDI provider to use our Guice implementation
        try {
            CDI.setCDIProvider(new JakartaCDIProvider());
        } catch (IllegalStateException e) {
            // Provider already set, ignore
        }
    }

    @Override
    public Integer sortOrder() {
        return Integer.MAX_VALUE - 200; // Load after most modules but before some
    }
}
