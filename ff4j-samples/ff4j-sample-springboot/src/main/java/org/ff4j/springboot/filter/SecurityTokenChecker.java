package org.ff4j.springboot.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.ff4j.springboot.FF4jHeaderParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Filter to get security.
 *
 * @author <a href="mailto:cedrick.lunven@gmail.com">Cedrick LUNVEN</a>
 */
public class SecurityTokenChecker implements ContainerRequestFilter, FF4jHeaderParams {

    /** logger for this class. */
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    /** Registered Keys. */
    protected Set < String > registeredKey = new HashSet<>(); 
    
    /**
     * Load information from properties file.
     */
    private void checkKey(String apiKey) {
    	
    	if (apiKey == null) {
            handleUnAuthorized("Parameter '"+ API_KEY + "' is required in header for identication");
        }
        
        if (registeredKey.isEmpty()) {
        	// TODO Load from repository, inject spring into filters
        	registeredKey.add("apikey1");
        }

        // Identification of a final user in HTTP-BASIC MODE
        if (!registeredKey.contains(apiKey.toUpperCase())) {
            handleUnAuthorized("Unknown ApiKey");
        }
    }
    
    /** {@inheritDoc} */
    @Override
	public void filter(ContainerRequestContext cReqCtx) throws IOException {
	String method = cReqCtx.getMethod();
        String path = cReqCtx.getUriInfo().getPath();
        logger.debug("Entering security filter for <" + path + ">");
        
        // We do allow wadl to be retrieve
        if (method.equals("GET") && (path.equals("application.wadl") || path.equals("application.wadl/xsd0.xsd"))) {
        	logger.info("Accessing schema and wadl ok");
        	
        } else {
	        checkKey(cReqCtx.getHeaderString(API_KEY));
	        logger.debug("ApiKey checked");
        }
    }

    /**
     * Dedicated error.
     * 
     * @param message
     *            target message
     */
    private void handleUnAuthorized(String message) {
        logger.error("Authentication error :" + message);
        throw new WebApplicationException(Response.status(Status.UNAUTHORIZED).entity(message).type(MediaType.TEXT_PLAIN).build());
    }

	
}
