package com.mycompany.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    @Test
    public void testAppConstructor() {
        App app1 = new App();
        App app2 = new App();
        assertEquals(app1.getMessage(), app2.getMessage());
    }

    @Test
    public void testAppMessage()
    {
        App app = new App();
        assertEquals("Hello from Spark Web Server!!!xxx", app.getMessage());
    }

    @Test
    public void testAddNumbers() {
        App app = new App();
        assertEquals(5, app.addNumbers(2, 3));
    }

    @Test
    public void testDoubleNumber() {
        App app = new App();
        int result = app.doubleNumber(7);
        assertEquals(14, result);  // 7 * 2 = 14
    }
}