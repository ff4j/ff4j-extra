package org.ff4j.sample;

import org.ff4j.FF4j;
import org.ff4j.web.api.jersey.FF4JApiApplication;
import org.ff4j.web.api.jersey.FF4jApiConfig;

public class SimpleFF4JJerseyApplication extends FF4JApiApplication {

    /** {@inheritDoc} */
    @Override
    public FF4jApiConfig getApiConfig() {

        FF4jApiConfig conf = new FF4jApiConfig();
        conf.setFF4j(new FF4j("ff4j.xml"));

//        // login/Password
//        conf.setEnableAuthentication(true);
//        conf.setEnableAuthorization(true);
//        conf.createUser("admin", "admin", true, true);
//        conf.createUser("user", "user", true, false);
//        conf.createUser("clu", "clu", true, false);
//
//        // ApiKeys (identification only)
//        conf.createApiKey("12345", false, false);
//        conf.createApiKey("abcde", true, true);

        return conf;
    }

}
