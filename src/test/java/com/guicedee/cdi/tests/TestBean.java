package com.guicedee.cdi.tests;

/**
 * A shared test bean class used by all CDI tests.
 */
public class TestBean {
    private final String name;

    public TestBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

