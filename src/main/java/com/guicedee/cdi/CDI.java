package com.guicedee.cdi;

import java.util.ServiceLoader;

/**
 * Utility class for accessing the CDI bean manager.
 * This class provides a convenient way to access the CDI bean manager from anywhere in the application.
 */
public class CDI {
    
    /**
     * Gets the CDI bean manager.
     * 
     * @return The CDI bean manager
     */
    public static GuiceCDIBeanManager getBeanManager() {
        // Use the ServiceLoader to get the ICDIProvider implementation
        ServiceLoader<ICDIProvider> providers = ServiceLoader.load(ICDIProvider.class);
        
        // Get the first provider
        ICDIProvider provider = providers.findFirst()
                .orElseThrow(() -> new IllegalStateException("No ICDIProvider implementation found"));
        
        // Return the bean manager
        return provider.getBeanManager();
    }
    
    /**
     * Gets an instance of the specified type.
     * 
     * @param <T> The type of the bean
     * @param beanType The class of the bean
     * @return An instance of the bean
     */
    public static <T> T getBean(Class<T> beanType) {
        return getBeanManager().getBean(beanType);
    }
    
    /**
     * Gets an instance of the specified type with the specified qualifier.
     * 
     * @param <T> The type of the bean
     * @param beanType The class of the bean
     * @param qualifier The qualifier
     * @return An instance of the bean
     */
    public static <T> T getBean(Class<T> beanType, String qualifier) {
        return getBeanManager().getBean(beanType, qualifier);
    }
    
    /**
     * Checks if a bean of the specified type exists.
     * 
     * @param <T> The type of the bean
     * @param beanType The class of the bean
     * @return true if a bean of the specified type exists, false otherwise
     */
    public static <T> boolean containsBean(Class<T> beanType) {
        return getBeanManager().containsBean(beanType);
    }
}