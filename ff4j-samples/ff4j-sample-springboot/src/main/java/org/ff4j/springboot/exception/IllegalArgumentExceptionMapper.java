package org.ff4j.springboot.exception;

import javax.inject.Singleton;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
@Singleton
public class IllegalArgumentExceptionMapper implements ExceptionMapper<IllegalArgumentException> {

	/** logger for the class. */
    protected static Logger logger = LoggerFactory.getLogger(RuntimeExceptionMapper.class);
    
	/** {@inheritDoc} */
	@Override
	public Response toResponse(IllegalArgumentException ex) {
		logger.error("An error occured within REST Service", ex);
		return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).//
				entity(ex.getMessage()). //
				type(MediaType.TEXT_PLAIN). //
				build();
	}

}
