package org.ff4j.springboot.exception;

import javax.inject.Singleton;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Mapper to convert any {@link Throwable} into dedicated response.
 *
 * @author clunven
 */
@Provider
@Singleton
public class RuntimeExceptionMapper implements ExceptionMapper<Throwable> {

	/** logger for the class. */
    protected static Logger logger = LoggerFactory.getLogger(RuntimeExceptionMapper.class);
    
	/** {@inheritDoc} */
	@Override
	public Response toResponse(Throwable ex) {
		logger.error("An error occured within REST Service", ex);
		
		// TODO sent dedicated message into Monitoring
		
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).//
				entity(ex.getMessage()). //
				type(MediaType.APPLICATION_JSON). //
				build();
	}

}
