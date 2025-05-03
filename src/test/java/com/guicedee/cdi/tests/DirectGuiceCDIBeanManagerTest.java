package com.guicedee.cdi.tests;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A simple test for the TestGuiceCDIBeanManager that uses Guice directly instead of IGuiceContext.
 * This avoids the need for a database connection or the VertxPersistenceModule.
 */
public class DirectGuiceCDIBeanManagerTest {

    private static Injector injector;

    /**
     * A test module that binds the TestBean with a qualifier.
     */
    public static class TestModule extends AbstractModule {
        @Override
        protected void configure() {
            bind(TestBean.class).annotatedWith(Names.named("testBean")).toInstance(new TestBean("test"));
            bind(TestGuiceCDIBeanManager.class);
        }
    }

    @BeforeAll
    public static void setup() {
        // Create the injector directly
        injector = Guice.createInjector(new TestModule());
    }

    @Test
    public void testGetBean() {
        // Get the bean manager
        TestGuiceCDIBeanManager beanManager = injector.getInstance(TestGuiceCDIBeanManager.class);
        assertNotNull(beanManager, "Bean manager should not be null");

        // Test getting a bean by class
        TestGuiceCDIBeanManager beanFromManager = beanManager.getBean(TestGuiceCDIBeanManager.class);
        assertNotNull(beanFromManager, "Bean from manager should not be null");
        assertSame(beanManager, beanFromManager, "Bean manager instances should be the same");

        // Test getting a bean with a qualifier
        TestBean retrievedBean = beanManager.getBean(TestBean.class, "testBean");
        assertNotNull(retrievedBean, "Retrieved bean should not be null");
        assertEquals("test", retrievedBean.getName(), "Bean name should match");
    }

    @Test
    public void testContainsBean() {
        // Get the bean manager
        TestGuiceCDIBeanManager beanManager = injector.getInstance(TestGuiceCDIBeanManager.class);
        assertNotNull(beanManager, "Bean manager should not be null");

        // Test containsBean with an existing bean
        assertTrue(beanManager.containsBean(TestGuiceCDIBeanManager.class), "Bean manager should contain itself");
    }

    /**
     * A simple test bean class.
     */
    public static class TestBean {
        private final String name;

        public TestBean(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * A non-existent bean class for testing.
     */
    public static class NonExistentBean {
    }
}
