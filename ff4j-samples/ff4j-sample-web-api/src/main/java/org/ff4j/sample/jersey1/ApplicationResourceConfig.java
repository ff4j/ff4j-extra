package org.ff4j.sample.jersey1;

import javax.ws.rs.ApplicationPath;

import com.sun.jersey.api.core.PackagesResourceConfig;

@ApplicationPath("resources")
public class ApplicationResourceConfig extends PackagesResourceConfig {

    public ApplicationResourceConfig() {
        super("org.ff4j.web.resources");
    }

}
