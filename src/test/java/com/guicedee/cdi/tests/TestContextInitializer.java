package com.guicedee.cdi.tests;

import com.guicedee.client.IGuiceContext;

/**
 * Shared initializer for all IGuiceContext-based CDI tests.
 * Ensures the context is initialized exactly once with all required modules.
 */
public final class TestContextInitializer {

    private static volatile boolean initialized = false;

    private TestContextInitializer() {
    }

    /**
     * Initializes the IGuiceContext if it hasn't been initialized yet.
     * Safe to call from multiple test classes — only the first call takes effect.
     */
    public static synchronized void ensureInitialized() {
        if (!initialized) {
            IGuiceContext.registerModule("guiced.cdi.tests");
            IGuiceContext.registerModule(new SharedTestModule());
            IGuiceContext.getContext().inject();
            initialized = true;
        }
    }
}

