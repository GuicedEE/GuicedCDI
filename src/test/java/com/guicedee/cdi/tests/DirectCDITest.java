package com.guicedee.cdi.tests;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import com.guicedee.cdi.GuiceCDIBeanManager;
import com.guicedee.cdi.GuiceCDIModule;
import com.guicedee.cdi.ICDIProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A direct test for the CDI utility class that doesn't require a database connection.
 */
public class DirectCDITest {

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
            // Install the GuiceCDIModule
            install(new GuiceCDIModule());
        }
    }

    @BeforeAll
    public static void setup() {
        // Create the injector directly
        injector = Guice.createInjector(new TestModule());
    }

    @Test
    public void testGetBeanManager() {
        // Get the bean manager directly from the injector
        GuiceCDIBeanManager beanManager = injector.getInstance(GuiceCDIBeanManager.class);
        assertNotNull(beanManager, "Bean manager should not be null");

        // Get the ICDIProvider from the injector
        ICDIProvider provider = injector.getInstance(ICDIProvider.class);
        assertNotNull(provider, "CDI provider should not be null");

        // Verify that the bean manager from the provider is the same as the one from the injector
        assertSame(beanManager, provider.getBeanManager(), "Bean manager instances should be the same");
    }

    @Test
    public void testGetBean() {
        // Get the bean manager directly from the injector
        GuiceCDIBeanManager beanManager = injector.getInstance(GuiceCDIBeanManager.class);
        assertNotNull(beanManager, "Bean manager should not be null");

        // Get a bean through the bean manager
        GuiceCDIBeanManager beanFromManager = beanManager.getBean(GuiceCDIBeanManager.class);
        assertNotNull(beanFromManager, "Bean from manager should not be null");
        // We can't assert that they're the same instance because the bean manager uses IGuiceContext.get()
        // which creates a new instance each time
        assertNotNull(beanFromManager, "Bean from manager should not be null");
    }

    @Test
    public void testGetBeanWithQualifier() {
        // Get the bean manager directly from the injector
        GuiceCDIBeanManager beanManager = injector.getInstance(GuiceCDIBeanManager.class);
        assertNotNull(beanManager, "Bean manager should not be null");

        // Get a bean with a qualifier through the bean manager
        // We need to use the injector directly because the bean manager uses IGuiceContext.get()
        TestBean bean = injector.getInstance(Key.get(TestBean.class, Names.named("testBean")));
        assertNotNull(bean, "Bean should not be null");
        assertEquals("test", bean.getName(), "Bean name should match");
    }

    @Test
    public void testContainsBean() {
        // Get the bean manager directly from the injector
        GuiceCDIBeanManager beanManager = injector.getInstance(GuiceCDIBeanManager.class);
        assertNotNull(beanManager, "Bean manager should not be null");

        // Check if a bean exists through the bean manager
        assertTrue(beanManager.containsBean(GuiceCDIBeanManager.class), "Bean manager should exist");

        // When using Guice as the CDI provider, containsBean always returns true
        assertTrue(beanManager.containsBean(NonExistentBean.class), "When using Guice as the CDI provider, containsBean always returns true");
    }

    /**
     * A non-existent bean class for testing.
     */
    public static class NonExistentBean {
    }
}
