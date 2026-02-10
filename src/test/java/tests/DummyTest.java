package tests;

import org.testng.annotations.Test;
import base.BaseTest;

public class DummyTest extends BaseTest {

    @Test
    public void testBrowserLaunch() {
        System.out.println("Browser launched: " + driver.getTitle());
        System.out.println("Current URL: " + driver.getCurrentUrl());
    }
}
