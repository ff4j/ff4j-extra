package org.ff4j.sample;

import org.ff4j.FF4j;
import org.ff4j.web.api.FF4JWebProvider;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimpleFF4jProvider implements FF4JWebProvider {
   
    /** ff4j instance. */
    private final FF4j ff4j;

    /**
     * Default constructeur invoked by servlet.
     */
    @SuppressWarnings("resource")
    public SimpleFF4jProvider() {
        // At this point no spring context available yet
        ff4j = new ClassPathXmlApplicationContext("applicationContext.xml").getBean(FF4j.class);
    }

    /**
     * Getter accessor for attribute 'fF4j'.
     *
     * @return current value of 'fF4j'
     */
    @Override
    public FF4j getFF4j() {
        return ff4j;
    }

}
