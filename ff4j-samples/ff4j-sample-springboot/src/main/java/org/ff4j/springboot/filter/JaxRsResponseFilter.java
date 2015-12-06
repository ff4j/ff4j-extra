package org.ff4j.springboot.filter;

import java.io.IOException;
import java.util.UUID;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

import org.ff4j.springboot.FF4jHeaderParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HTTP logging filter.
 */
public class JaxRsResponseFilter implements ContainerRequestFilter, ContainerResponseFilter, FF4jHeaderParams {

	/** logger. */
    protected Logger logger = LoggerFactory.getLogger(JaxRsResponseFilter.class);
    
    /**
     * Before Method invocation reading HTTP REQUEST.
     *
     * {@inheritDoc}
     */
	@Override
	public void filter(ContainerRequestContext reqCtx) throws IOException {
		String correlationID = UUID.randomUUID().toString();
		reqCtx.getHeaders().add(CORRELATION_ID, correlationID);
		reqCtx.getHeaders().add(START_TIME, String.valueOf(System.currentTimeMillis()));
		logger.info("Start  " + correlationID + "(" + reqCtx.getMethod() + " : " + reqCtx.getUriInfo().getPath() + ")");
	}
	
	/** {@inheritDoc} */
	@Override
	public void filter(ContainerRequestContext reqCtx, ContainerResponseContext resCtx)
	throws IOException {
		
		String correlationID = reqCtx.getHeaderString("X-CorrelationID");
		resCtx.getHeaders().add(END_TIME, String.valueOf(System.currentTimeMillis()));
		resCtx.getHeaders().add(CORRELATION_ID, correlationID);
		
		String startTime = reqCtx.getHeaderString(START_TIME);
		if (null != startTime) {
			long response = System.currentTimeMillis() - Long.valueOf(startTime);
			resCtx.getHeaders().add(START_TIME, startTime);
			logger.info("End " + correlationID + " Response Time : " + response);
		}
		
	}

}
