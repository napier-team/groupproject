package com.napier.sem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * A simple "smoke test" for the App class.
 * Real testing of App's functionality is done via Integration Tests.
 */
public class AppTest {

    /**
     * Test that the App class can be instantiated.
     * This is useful for code coverage metrics.
     */
    @Test
    public void testAppConstructor() {
        App app = new App();
        assertNotNull(app);
    }
}