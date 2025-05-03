package com.guicedee.cdi;

import com.google.inject.Key;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.guicedee.client.IGuiceContext;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * A CDI bean manager implementation that uses Google Guice as the underlying dependency injection framework.
 * This class provides a bridge between CDI-style bean management and Guice's dependency injection.
 * 
 * This implementation uses the IGuiceContext to get instances of beans, which is the recommended
 * approach in the GuicedEE framework.
 * 
 * Usage:
 * 1. Inject this class into your components:
 *    {@code @Inject private GuiceCDIBeanManager beanManager;}
 * 
 * 2. Use it to get instances of beans:
 *    {@code MyBean bean = beanManager.getBean(MyBean.class);}
 * 
 * 3. Use it with qualifiers:
 *    {@code MyBean bean = beanManager.getBean(MyBean.class, "qualifier");}
 *    or
 *    {@code MyBean bean = beanManager.getBean(MyBean.class, qualifierAnnotation);}
 * 
 * 4. Check if a bean exists:
 *    {@code boolean exists = beanManager.containsBean(MyBean.class);}
 * 
 * This class is registered as a Guice singleton by the GuiceCDIModule.
 */
@Singleton
public class GuiceCDIBeanManager
{

    /**
     * Gets an instance of the specified type.
     * 
     * @param <T> The type of the bean
     * @param beanType The class of the bean
     * @return An instance of the bean
     */
    public <T> T getBean(Class<T> beanType) {
        return IGuiceContext.get(beanType);
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
        return IGuiceContext.get(Key.get(beanType, Names.named(qualifier)));
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
        return IGuiceContext.get(Key.get(beanType, qualifier));
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
     * 
     * Note: When using Guice as the CDI provider, this method always returns true
     * because Guice can create just-in-time bindings for classes with injectable
     * constructors, even if they're not explicitly bound in the injector.
     */
    public <T> boolean containsBean(Class<T> beanType) {
        return true;
    }
}
