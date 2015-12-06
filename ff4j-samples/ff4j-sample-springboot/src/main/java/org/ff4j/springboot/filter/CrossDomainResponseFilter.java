package org.ff4j.springboot.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;

import org.springframework.stereotype.Component;

/**
 * CrosDomain Filter
 */
@Component
public class CrossDomainResponseFilter implements ContainerResponseFilter {
	
	/** {@inheritDoc} */
	@Override
	public void filter(ContainerRequestContext reqC, ContainerResponseContext resC)
	throws IOException {
		MultivaluedMap<String, Object> mmv = resC.getHeaders();
		mmv.add("Access-Control-Allow-Origin", "*");
		mmv.add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
		mmv.add("Access-Control-Allow-Credentials", "true");
		mmv.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
		mmv.add("Access-Control-Max-Age", "1209600");
	}

}
