package com.guicedee.cdi.tests;

import com.guicedee.client.IGuiceContext;
import com.guicedee.cdi.CDI;
import com.guicedee.cdi.GuiceCDIBeanManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the CDI utility class.
 */
public class CDITest {

    @BeforeAll
    public static void setup() {
        TestContextInitializer.ensureInitialized();
    }

    @Test
    public void testGetBeanManager() {
        // Get the bean manager through the CDI utility class
        GuiceCDIBeanManager beanManager = CDI.getBeanManager();
        assertNotNull(beanManager, "Bean manager should not be null");

        // Verify that it's the same instance as the one from IGuiceContext
        GuiceCDIBeanManager beanManagerFromContext = IGuiceContext.get(GuiceCDIBeanManager.class);
        assertSame(beanManager, beanManagerFromContext, "Bean manager instances should be the same");
    }

    @Test
    public void testGetBean() {
        // Get a bean through the CDI utility class
        GuiceCDIBeanManager beanManager = CDI.getBean(GuiceCDIBeanManager.class);
        assertNotNull(beanManager, "Bean manager should not be null");

        // Verify that it's the same instance as the one from IGuiceContext
        GuiceCDIBeanManager beanManagerFromContext = IGuiceContext.get(GuiceCDIBeanManager.class);
        assertSame(beanManager, beanManagerFromContext, "Bean manager instances should be the same");
    }

    @Test
    public void testGetBeanWithQualifier() {
        // Get a bean with a qualifier through the CDI utility class
        TestBean bean = CDI.getBean(TestBean.class, "testBean");
        assertNotNull(bean, "Bean should not be null");
        assertEquals("test", bean.getName(), "Bean name should match");
    }

    @Test
    public void testContainsBean() {
        // Check if a bean exists through the CDI utility class
        assertTrue(CDI.containsBean(GuiceCDIBeanManager.class), "Bean manager should exist");
        // When using Guice as the CDI provider, containsBean always returns true
        assertTrue(CDI.containsBean(NonExistentBean.class), "When using Guice as the CDI provider, containsBean always returns true");
    }

    /**
     * A non-existent bean class for testing.
     */
    public static class NonExistentBean {
    }
}
