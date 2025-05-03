package com.guicedee.cdi.tests;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.guicedee.client.IGuiceContext;
import com.guicedee.guicedinjection.interfaces.IGuiceModule;
import com.guicedee.cdi.CDI;
import com.guicedee.cdi.GuiceCDIBeanManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the CDI utility class.
 */
public class CDITest {

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

    @BeforeAll
    public static void setup() {
        // Register the postgres module
        IGuiceContext.registerModule("guiced.cdi.tests");
        // Register our own TestModule to bind TestBean with the qualifier "testBean"
        IGuiceContext.registerModule(new TestModule());
        IGuiceContext.getContext().inject();
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
