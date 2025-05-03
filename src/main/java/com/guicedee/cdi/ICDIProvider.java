package com.guicedee.cdi;

/**
 * Interface for CDI providers in the GuicedEE framework.
 * Implementations of this interface provide access to the CDI bean manager.
 */
public interface ICDIProvider
{
    /**
     * Gets the CDI bean manager.
     * 
     * @return The CDI bean manager
     */
    GuiceCDIBeanManager getBeanManager();
}