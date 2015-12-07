package org.ff4j.springboot.sample;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

import org.ff4j.springboot.exception.RuntimeExceptionMapper;
import org.ff4j.springboot.filter.CrossDomainResponseFilter;
import org.ff4j.springboot.filter.JaxRsResponseFilter;
import org.ff4j.springboot.filter.JerseyApplicationEventListener;
import org.ff4j.springboot.filter.JerseyRequestEventListener;
import org.ff4j.springboot.filter.SecurityTokenChecker;
import org.ff4j.springboot.sample.resources.BaseHrResource;
import org.ff4j.springboot.sample.resources.CompanyResource;
import org.ff4j.springboot.sample.resources.DepartmentResource;
import org.ff4j.springboot.sample.resources.EmployeeResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.jersey2.InstrumentedResourceMethodApplicationListener;

import io.swagger.config.ScannerFactory;
import io.swagger.jaxrs.config.BeanConfig;

/**
 * Initialization of Jersey context configuration.
 *
 * @author clunven
 */
@Configuration
@ApplicationPath("/hr")
public class ServiceJerseyConfig extends ResourceConfig {

	/** logger for the class. */
    protected static Logger logger = LoggerFactory.getLogger(ServiceJerseyConfig.class);
    
    @Value("${sg.conf.cors}")
    private boolean enableCORS = true;
    
    @Value("${sg.conf.monitoring}")
    private boolean enableMonitoring = true;
    
    @Value("${sg.conf.documentation}")
    private boolean enableDocumentation = true;
    
    @Value("${sg.conf.security.apiKey}")
    private boolean enableSecurityApiKey = false;
    
    @Value("${sg.conf.path}")
    private String path = "/";
   
    @Autowired(required = true)
    private MetricRegistry metricRegistry;
    
    @PostConstruct
    public void initRegistry() {
    	/**
    	 * Inject existing Metrics Registry from Spring boot. And define the Jersey
    	 * interceptor to write into Metrics.
    	 * 
    	 * Those metrics are available in the /metrics endpoint (Actuator)
    	 */
    	register(new InstrumentedResourceMethodApplicationListener(metricRegistry));
    }
   
	/**
	 * Initialization of Jersey Context from configuration flags.
	 */
	public ServiceJerseyConfig() {
		
		// Monitoring through Jersey interceptors
		if (enableMonitoring) {
			register(JaxRsResponseFilter.class);
			register(JerseyApplicationEventListener.class);
			register(JerseyRequestEventListener.class);
			logger.info("Monitoring filters have been enabled.");
		}
		
		// Swagger configuration (documentation)
		if (enableDocumentation) {
			
		    BeanConfig beanConfig = new BeanConfig();
	        beanConfig.setTitle("FF4J (ff4j.org) WebAPI");
	        beanConfig.setDescription("Administrate and operate all tasks on your features through this api");
	        beanConfig.setResourcePackage("org.ff4j.web.api.resources");
	        beanConfig.setContact("@clunven");
	        beanConfig.setLicense("Apache 2.0");
	        beanConfig.setLicenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html");
	        beanConfig.setBasePath("/hr");
	        beanConfig.setScan(true);
	        ScannerFactory.setScanner(beanConfig);
	        getSingletons().add(io.swagger.jaxrs.listing.ApiListingResource.class);
	        getSingletons().add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
		}
		
		// Check token in header
		if (enableSecurityApiKey) {
			register(SecurityTokenChecker.class);
		}
		
		// HTTP Filter to override headers
		if (enableCORS) {
			register(CrossDomainResponseFilter.class);
			logger.info("CORS Filter has been enabled.");
		}
		
		// REST Resources from sample
		register(BaseHrResource.class);
		register(CompanyResource.class);
		register(EmployeeResource.class);
		register(DepartmentResource.class);
		
		// Exception handler
		register(RuntimeExceptionMapper.class);
		register(IllegalArgumentException.class);
		
		logger.info("Jersey context has been successfully initialized");
	}

}
