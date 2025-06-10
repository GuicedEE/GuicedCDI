package com.guicedee.cdi;

import com.google.inject.Key;
import com.google.inject.name.Names;
import com.guicedee.client.IGuiceContext;
import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.enterprise.util.TypeLiteral;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

/**
 * A minimal implementation of Jakarta CDI that delegates to GuicedEE's CDI implementation.
 * This class is used to bridge between Jakarta CDI and GuicedEE's CDI implementation.
 */
public class GuicedCDI extends CDI<Object> {

    private static final GuicedCDI INSTANCE = new GuicedCDI();

    /**
     * Gets the singleton instance of GuicedCDI.
     * 
     * @return The singleton instance
     */
    public static GuicedCDI getInstance() {
        return INSTANCE;
    }

    /**
     * Private constructor to enforce singleton pattern.
     */
    private GuicedCDI() {
        // Private constructor to enforce singleton pattern
    }

    /**
     * A private inner class that implements Instance<T> and delegates to IGuiceContext.get().
     * 
     * @param <T> The type of the bean
     */
    private static class GuicedCDIInstance<T> implements Instance<T> {
        private final Class<T> type;
        private final Annotation[] qualifiers;

        /**
         * Constructs a new GuicedCDIInstance with the specified type and qualifiers.
         * 
         * @param type The type of the bean
         * @param qualifiers The qualifiers
         */
        public GuicedCDIInstance(Class<T> type, Annotation... qualifiers) {
            this.type = type;
            this.qualifiers = qualifiers;
        }

        /**
         * Gets an instance of the specified type.
         * 
         * @param <U> The type of the bean
         * @param subtype The class of the bean
         * @param qualifiers The qualifiers
         * @return An instance of the bean
         */
        @Override
        public <U extends T> Instance<U> select(Class<U> subtype, Annotation... qualifiers) {
            return new GuicedCDIInstance<>(subtype, qualifiers);
        }

        /**
         * Gets an instance of the specified type.
         * 
         * @param <U> The type of the bean
         * @param subtype The type literal of the bean
         * @param qualifiers The qualifiers
         * @return An instance of the bean
         */
        @Override
        public <U extends T> Instance<U> select(TypeLiteral<U> subtype, Annotation... qualifiers) {
            throw new UnsupportedOperationException("TypeLiteral not supported");
        }

        /**
         * Gets an instance of the specified type.
         * 
         * @param qualifiers The qualifiers
         * @return An instance of the bean
         */
        @Override
        public Instance<T> select(Annotation... qualifiers) {
            return new GuicedCDIInstance<>(type, qualifiers);
        }

        /**
         * Checks if the instance is unsatisfied.
         * 
         * @return true if the instance is unsatisfied, false otherwise
         */
        @Override
        public boolean isUnsatisfied() {
            return false;
        }

        /**
         * Checks if the instance is ambiguous.
         * 
         * @return true if the instance is ambiguous, false otherwise
         */
        @Override
        public boolean isAmbiguous() {
            return false;
        }

        /**
         * Destroys the specified instance.
         * 
         * @param instance The instance to destroy
         */
        @Override
        public void destroy(T instance) {
            // No-op, as Guice doesn't support bean destruction
        }

        /**
         * Gets a handle for the instance.
         * 
         * @return A handle for the instance
         */
        @Override
        public Handle<T> getHandle() {
            throw new UnsupportedOperationException("Not implemented");
        }

        /**
         * Gets handles for the instance.
         * 
         * @return Handles for the instance
         */
        @Override
        public Iterable<? extends Handle<T>> handles() {
            throw new UnsupportedOperationException("Not implemented");
        }

        /**
         * Gets the instance.
         * 
         * @return The instance
         */
        @Override
        public T get() {
            if (qualifiers.length == 0) {
                return IGuiceContext.get(type);
            } else {
                return IGuiceContext.get(Key.get(type, qualifiers[0]));
            }
        }

        /**
         * Gets an iterator for the instance.
         * 
         * @return An iterator for the instance
         */
        @Override
        public Iterator<T> iterator() {
            return Collections.singleton(get()).iterator();
        }
    }

    /**
     * Gets the bean manager.
     * 
     * @return The bean manager
     */
    @Override
    public BeanManager getBeanManager() {
        // Return the concrete implementation of GuiceCDIBeanManagerAdapter
        return IGuiceContext.get(GuiceCDIBeanManagerAdapterImpl.class);
    }

    /**
     * Gets an instance of the specified type.
     * 
     * @param <U> The type of the bean
     * @param subtype The class of the bean
     * @param qualifiers The qualifiers
     * @return An instance of the bean
     */
    @Override
    public <U extends Object> Instance<U> select(Class<U> subtype, Annotation... qualifiers) {
        return new GuicedCDIInstance<>(subtype, qualifiers);
    }

    /**
     * Gets an instance of the specified type.
     * 
     * @param <U> The type of the bean
     * @param subtype The type literal of the bean
     * @param qualifiers The qualifiers
     * @return An instance of the bean
     */
    @Override
    public <U extends Object> Instance<U> select(TypeLiteral<U> subtype, Annotation... qualifiers) {
        throw new UnsupportedOperationException("TypeLiteral not supported");
    }

    /**
     * Gets an instance of the specified type.
     * 
     * @param qualifiers The qualifiers
     * @return An instance of the bean
     */
    @Override
    public Instance<Object> select(Annotation... qualifiers) {
        return new GuicedCDIInstance<>(Object.class, qualifiers);
    }

    /**
     * Checks if the instance is unsatisfied.
     * 
     * @return true if the instance is unsatisfied, false otherwise
     */
    @Override
    public boolean isUnsatisfied() {
        return false;
    }

    /**
     * Checks if the instance is ambiguous.
     * 
     * @return true if the instance is ambiguous, false otherwise
     */
    @Override
    public boolean isAmbiguous() {
        return false;
    }

    /**
     * Destroys the specified instance.
     * 
     * @param instance The instance to destroy
     */
    @Override
    public void destroy(Object instance) {
        // No-op, as Guice doesn't support bean destruction
    }

    /**
     * Gets a handle for the instance.
     * 
     * @return A handle for the instance
     */
    @Override
    public Handle<Object> getHandle() {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * Gets handles for the instance.
     * 
     * @return Handles for the instance
     */
    @Override
    public Iterable<? extends Handle<Object>> handles() {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * Gets the instance.
     * 
     * @return The instance
     */
    @Override
    public Object get() {
        return IGuiceContext.get(Object.class);
    }

    /**
     * Gets an iterator for the instance.
     * 
     * @return An iterator for the instance
     */
    @Override
    public Iterator<Object> iterator() {
        // Return an iterator over the singleton instance
        return Collections.singleton(get()).iterator();
    }
}
