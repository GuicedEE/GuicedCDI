package com.guicedee.cdi.tests;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;
import com.guicedee.client.IGuiceContext;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the TestGuiceCDIBeanManagerWithInjector class.
 */
public class TestGuiceCDIBeanManagerTest {

    private static Injector injector;

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
     * A test module that binds the TestBean with a qualifier.
     */
    public static class TestModule extends AbstractModule {
        @Override
        protected void configure() {
            bind(TestBean.class).annotatedWith(Names.named("testBean")).toInstance(new TestBean("test"));
            bind(TestGuiceCDIBeanManagerWithInjector.class);
        }
    }

    @BeforeAll
    public static void setup() {
        // Create the injector directly
        injector = Guice.createInjector(new TestModule());
    }

    @Test
    public void testGetBean() {
        // Get the bean manager directly from the injector
        TestGuiceCDIBeanManagerWithInjector beanManager = injector.getInstance(TestGuiceCDIBeanManagerWithInjector.class);
        assertNotNull(beanManager, "Bean manager should not be null");

        // Get a bean through the bean manager
        TestGuiceCDIBeanManagerWithInjector beanFromManager = beanManager.getBean(TestGuiceCDIBeanManagerWithInjector.class);
        assertNotNull(beanFromManager, "Bean from manager should not be null");
        assertSame(beanManager, beanFromManager, "Bean manager instances should be the same");
    }

    @Test
    public void testGetBeanWithQualifier() {
        // Get the bean manager directly from the injector
        TestGuiceCDIBeanManagerWithInjector beanManager = injector.getInstance(TestGuiceCDIBeanManagerWithInjector.class);
        assertNotNull(beanManager, "Bean manager should not be null");

        // Get a bean with a qualifier through the bean manager
        TestBean bean = beanManager.getBean(TestBean.class, "testBean");
        assertNotNull(bean, "Bean should not be null");
        assertEquals("test", bean.getName(), "Bean name should match");
    }

    @Test
    public void testContainsBean() {
        // Get the bean manager directly from the injector
        TestGuiceCDIBeanManagerWithInjector beanManager = injector.getInstance(TestGuiceCDIBeanManagerWithInjector.class);
        assertNotNull(beanManager, "Bean manager should not be null");

        // Check if a bean exists through the bean manager
        assertTrue(beanManager.containsBean(TestGuiceCDIBeanManagerWithInjector.class), "Bean manager should exist");
    }
}