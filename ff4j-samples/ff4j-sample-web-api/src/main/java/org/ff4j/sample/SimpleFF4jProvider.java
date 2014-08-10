package org.ff4j.sample;

import org.ff4j.FF4j;
import org.ff4j.web.api.FF4JWebProvider;

public class SimpleFF4jProvider implements FF4JWebProvider {

    private FF4j ff4j = null;

    @Override
    public FF4j getFF4j() {
        if (ff4j == null) {
            ff4j = new FF4j("ff4j.xml");
        }
        return ff4j;
    }
    

}
