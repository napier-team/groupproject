package com.napier.sem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit tests for the App class.
 */
public class AppTest {

    @Test
    public void testAppConstructor() {
        App app = new App();
        assertNotNull(app, "App instance should not be null");
    }
}