package com.guicedee.cdi.tests;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.guicedee.cdi.GuiceCDIBeanManager;
import com.guicedee.client.IGuiceContext;
import com.guicedee.client.services.lifecycle.IGuiceModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A simple test for the GuiceCDIBeanManager that doesn't require a database connection.
 */
public class SimpleCDIBeanManagerTest {

    /**
     * A test module that binds the TestBean with a qualifier.
     */
    public static class TestModule extends AbstractModule implements IGuiceModule<TestModule> {
        @Override
        protected void configure() {
            bind(TestBean.class).annotatedWith(Names.named("testBean")).toInstance(new TestBean("test"));
        }

        @Override
        public Integer sortOrder() {
            return 10;
        }
    }

    @BeforeAll
    public static void setup() {
        // Register the test module
        IGuiceContext.registerModule(new TestModule());
        // Register the postgres module
        IGuiceContext.registerModule("guiced.cdi.tests");
        IGuiceContext.getContext().inject();
    }

    @Test
    public void testGetBean() {
        // Get the bean manager
        GuiceCDIBeanManager beanManager = IGuiceContext.get(GuiceCDIBeanManager.class);
        assertNotNull(beanManager, "Bean manager should not be null");

        // Test getting a bean by class
        GuiceCDIBeanManager beanFromManager = beanManager.getBean(GuiceCDIBeanManager.class);
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
        GuiceCDIBeanManager beanManager = IGuiceContext.get(GuiceCDIBeanManager.class);
        assertNotNull(beanManager, "Bean manager should not be null");

        // Test containsBean with an existing bean
        assertTrue(beanManager.containsBean(GuiceCDIBeanManager.class), "Bean manager should contain itself");
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
}
