package com.guicedee.cdi;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Implementation of ICDIProvider that provides access to the GuiceCDIBeanManager.
 * This class is registered as a singleton in the GuiceCDIModule.
 */
@Singleton
public class GuiceCDIProvider implements ICDIProvider {
    
    private final GuiceCDIBeanManager beanManager;
    
    /**
     * Constructs a new GuiceCDIProvider with the specified bean manager.
     * 
     * @param beanManager The CDI bean manager
     */
    @Inject
    public GuiceCDIProvider(GuiceCDIBeanManager beanManager) {
        this.beanManager = beanManager;
    }
    
    /**
     * Gets the CDI bean manager.
     * 
     * @return The CDI bean manager
     */
    @Override
    public GuiceCDIBeanManager getBeanManager() {
        return beanManager;
    }

}