package com.guicedee.cdi.tests;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * A special version of GuiceCDIBeanManager for testing that uses the injector directly instead of IGuiceContext.
 * This avoids the need for a database connection or the VertxPersistenceModule.
 */
@Singleton
public class TestGuiceCDIBeanManager {

    private final Injector injector;

    @Inject
    public TestGuiceCDIBeanManager(Injector injector) {
        this.injector = injector;
    }

    /**
     * Gets an instance of the specified type.
     * 
     * @param <T> The type of the bean
     * @param beanType The class of the bean
     * @return An instance of the bean
     */
    public <T> T getBean(Class<T> beanType) {
        return injector.getInstance(beanType);
    }

    /**
     * Gets an instance of the specified type with the specified qualifier.
     * 
     * @param <T> The type of the bean
     * @param beanType The class of the bean
     * @param qualifier The qualifier annotation
     * @return An instance of the bean
     */
    public <T> T getBean(Class<T> beanType, String qualifier) {
        return injector.getInstance(Key.get(beanType, Names.named(qualifier)));
    }

    /**
     * Gets an instance of the specified type with the specified qualifier annotation.
     * 
     * @param <T> The type of the bean
     * @param beanType The class of the bean
     * @param qualifier The qualifier annotation
     * @return An instance of the bean
     */
    public <T> T getBean(Class<T> beanType, Annotation qualifier) {
        return injector.getInstance(Key.get(beanType, qualifier));
    }

    /**
     * Gets all instances of the specified type.
     * 
     * @param <T> The type of the bean
     * @param beanType The class of the bean
     * @return A set of instances of the bean
     */
    public <T> Set<T> getBeans(Class<T> beanType) {
        Set<T> beans = new HashSet<>();
        beans.add(getBean(beanType));
        return beans;
    }

    /**
     * Checks if a bean of the specified type exists.
     * 
     * @param <T> The type of the bean
     * @param beanType The class of the bean
     * @return true if a bean of the specified type exists, false otherwise
     */
    public <T> boolean containsBean(Class<T> beanType) {
        // Check if the bean is explicitly bound in the injector
        return injector.getBindings().containsKey(Key.get(beanType));
    }
}
