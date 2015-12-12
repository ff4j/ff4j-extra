package org.ff4j.springboot;

import javax.annotation.PostConstruct;

import org.ff4j.FF4j;
import org.ff4j.web.ApiConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FF4jConfiguration {
    
    /** logger for the class. */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${ff4j.webapi.authentication}")
    private boolean authentication = false;
    
    @Value("${ff4j.webapi.authorization}")
    private boolean authorization = false;
    
    @Bean
    public FF4j getFF4j() {
        return null;
    }
    
    @PostConstruct
    public void initApiConfig() {
        ApiConfig apiConfig = new ApiConfig();
        apiConfig.setAuthenticate(authentication);
        apiConfig.setAutorize(authorization);
        apiConfig.setfF4j(getFF4j());
        
    }
    

}
