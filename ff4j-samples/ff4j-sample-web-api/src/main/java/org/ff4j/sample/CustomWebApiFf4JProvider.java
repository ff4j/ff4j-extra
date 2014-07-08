package org.ff4j.sample;

import org.ff4j.FF4j;
import org.ff4j.web.resources.AbstractResourceConfigFF4J;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Custom FF4J provider.
 *
 * @author <a href="mailto:cedrick.lunven@gmail.com">Cedrick LUNVEN</a>
 */
public class CustomWebApiFf4JProvider extends AbstractResourceConfigFF4J {

    /**
     * Getter accessor for attribute 'ff4j'.
     *
     * @return current value of 'ff4j'
     */
    @Override
    public FF4j getFF4j() {
        return new ClassPathXmlApplicationContext("applicationContext.xml").getBean(FF4j.class);
    }
}
