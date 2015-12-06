/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package org.ff4j.springboot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

/**
 * Main Application to work with SpringBoot
 *
 * @author Cedrick Lunven (@clunven)</a>
 */
@SpringBootApplication
public class FF4jSpringBootApplication extends SpringBootServletInitializer {
    /*
     * @Autowired private ODataServlet demoODataServlet;
     * 
     * /** Generic layer on top of low-levelOlingo API.
     * 
     * @Autowired private IntrospectionODataServlet oDataServlet;
     * 
     * @Bean public ServletRegistrationBean servletRegistrationBean(){ return new
     * ServletRegistrationBean(demoODataServlet,"/odata/*"); }
     * 
     * @Bean public ServletRegistrationBean servletRegistrationBean2(){ return new
     * ServletRegistrationBean(oDataServlet,"/odata2/*"); }
     */

    @Bean
    public SecurityProperties securityProperties() {
        SecurityProperties security = new SecurityProperties();
        security.getBasic().setPath("");
        return security;
    }

    /**
     * Main Operation to launch white application.
     *
     * @param args
     *            command line arguments
     */
    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(FF4jSpringBootApplication.class);
        new FF4jSpringBootApplication().configure(builder).run(args);
    }

    /** {@inheritDoc} */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(FF4jSpringBootApplication.class);
    }

}
