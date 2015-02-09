package org.ff4j;

/**
 * Sample Filter for FF4J.
 *
 * @author <a href="mailto:cedrick.lunven@gmail.com">Cedrick LUNVEN</a>
 */
public class FF4JFilter {
    
    /**
     * As dedicated Filter, single method returning boolean.
     *
     * @param featId
     *      current feature name.
     */
    public boolean check(String featId) {
        
        return true;
    }

}
