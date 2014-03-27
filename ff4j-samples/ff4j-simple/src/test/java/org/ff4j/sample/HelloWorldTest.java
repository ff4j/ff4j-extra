package org.ff4j.sample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.ff4j.FF4j;
import org.junit.Test;

public class HelloWorldTest {

    @Test
    public void myFirstFF4JTest() {

        FF4j ff4j = new FF4j("ff4j.xml");
        assertEquals(2, ff4j.getFeatures().size());
        assertTrue(ff4j.exist("sayHello"));
        assertTrue(ff4j.isFlipped("sayHello"));

        // Test value at runtime
        if (ff4j.isFlipped("sayHello")) {
            // Feature ok !
            System.out.println("Hello World !");
        } else {
            fail();
        }
    }

    @Test
    public void autoCreateFeatureEnableTest() {

        // Default : store = inMemory, load features from ff4j.xml file
        FF4j ff4j = new FF4j("ff4j.xml").autoCreate(true);

        if (!ff4j.isFlipped("autoCreatedFeature")) {
            System.out.println("Not available but code won't failed");
        } else {
            fail();
        }
    }

    @Test
    public void createFeatureDynamically() {

        // Initialize with empty store
        FF4j ff4j = new FF4j();

        // Dynamically register new features
        ff4j.create("f1").enable("f1");

        // Testing
        assertTrue(ff4j.exist("f1"));
        assertTrue(ff4j.isFlipped("f1"));
    }

}
