package org.ff4j.springboot;

/**
 * Proposition of Header for FF4J.
 *
 * @author clunven
 */
public interface FF4jHeaderParams {
	
	/** Business identifier for transaction. */
	String CORRELATION_ID = "X-FF4J-CorrelationID";
	
	/** Mandatory in incoming request, identify the caller. */
	String CALLER_ID = "X-FF4J-CallerID";
	
	/** Identifier for final end user requesting service. */ 
	String USER_ID = "X-FF4J-UserID";
	
	/** Technical Key use to check if current request should be granted or not. */
	String API_KEY = "X-FF4J-ApiKey";
	
	/** HTTP call starting time. */
	String START_TIME = "X-StartTime";
	
	/** HTTP Call ending time. */
	String END_TIME = "X-EndTime";
	 
    /** Normalized HTTP Key for security. */
    String HEADER_AUTHORIZATION = "Authorization";
}
