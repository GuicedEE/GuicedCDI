package com.guicedee.cdi;

import com.guicedee.client.IGuiceContext;

/**
 * Implementation of ICDIProvider that provides access to the GuiceCDIBeanManager.
 * This class is used as a service provider for the ICDIProvider interface.
 */
public class GuiceCDIProviderImpl implements ICDIProvider {
    
    /**
     * Constructs a new GuiceCDIProviderImpl.
     * This constructor is required for the service provider mechanism.
     */
    public GuiceCDIProviderImpl() {
        // Default constructor required for service provider
    }
    
    /**
     * Gets the CDI bean manager.
     * 
     * @return The CDI bean manager
     */
    @Override
    public GuiceCDIBeanManager getBeanManager() {
        return IGuiceContext.get(GuiceCDIBeanManager.class);
    }
}