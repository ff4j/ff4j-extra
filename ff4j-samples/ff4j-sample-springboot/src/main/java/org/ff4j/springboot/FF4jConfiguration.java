package org.ff4j.springboot;

import org.ff4j.FF4j;
import org.ff4j.cache.FF4jCacheProxy;
import org.ff4j.cache.InMemoryCacheManager;
import org.ff4j.store.InMemoryFeatureStore;
import org.ff4j.web.ApiConfig;
import org.ff4j.web.embedded.ConsoleServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FF4jConfiguration {
 
    @Value("${ff4j.webapi.authentication}")
    private boolean authentication = false;
    
    @Value("${ff4j.webapi.authorization}")
    private boolean authorization = false;
    
    @Bean
    public FF4j getFF4j() {
        FF4j ff4j = new FF4j();

        FF4jCacheProxy cacheProxy = new FF4jCacheProxy();
        cacheProxy.setTargetFeatureStore(new InMemoryFeatureStore());
        cacheProxy.setCacheManager(new InMemoryCacheManager());

        ff4j.setFeatureStore(cacheProxy);
        ff4j.setAuthorizationsManager(new CustomAuthorizationManager());

        return ff4j;
    }
    
    @Bean
    public ConsoleServlet getFF4JServlet() {
        ConsoleServlet cs = new ConsoleServlet();
        cs.setFf4j(getFF4j());
        return cs;
    }
    
    @Bean
    public ApiConfig getApiConfig() {
        ApiConfig apiConfig = new ApiConfig();
        apiConfig.setAuthenticate(authentication);
        apiConfig.setAutorize(authorization);
        apiConfig.setfF4j(getFF4j());
        return apiConfig;
    }

}
