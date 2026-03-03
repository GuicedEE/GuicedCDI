package com.guicedee.cdi.tests;

import com.guicedee.cdi.GuiceCDIBeanManager;
import com.guicedee.client.IGuiceContext;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A simple test for the GuiceCDIBeanManager that doesn't require a database connection.
 */
public class SimpleCDIBeanManagerTest {

    @BeforeAll
    public static void setup() {
        TestContextInitializer.ensureInitialized();
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
}
