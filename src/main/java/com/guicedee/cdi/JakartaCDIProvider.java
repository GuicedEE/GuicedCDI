package com.guicedee.cdi;

import jakarta.enterprise.inject.spi.CDI;
import jakarta.enterprise.inject.spi.CDIProvider;

/**
 * Implementation of Jakarta CDIProvider that provides access to the GuicedEE CDI implementation.
 * This class is used to bridge between Jakarta CDI and GuicedEE's CDI implementation.
 */
public class JakartaCDIProvider implements CDIProvider {
    
    /**
     * Gets the CDI instance.
     * 
     * @return The CDI instance
     */
    @Override
    public CDI<Object> getCDI() {
        return GuicedCDI.getInstance();
    }
}