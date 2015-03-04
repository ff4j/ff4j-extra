package org.ff4j;

import static org.apache.camel.util.ObjectHelper.notNull;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.ff4j.core.FlippingExecutionContext;

/**
 * Builder to use FF4J as predicate.
 *
 * @author <a href="mailto:cedrick.lunven@gmail.com">Cedrick LUNVEN</a>
 */
public class FF4JPredicateBuilder {
    
    /** Injection of ff4j. */
    private static FF4j ff4j;
    
    /**
     * Initialisation.
     * @param ff4j
     *      current ff4j.
     */
    public static synchronized void initFF4j(FF4j ff4j) {
        FF4JPredicateBuilder.ff4j = ff4j;
    }
    
    /**
     * Check if a feature is enabled (feature Toggling) with FF4J Framework.
     * 
     * @param featureName
     *      target feature name
     * @param fex
     *      feature context (if required)
     * @return
     *      true if enabled
     */
    public static Predicate checkFF4j(final String featureName) {
        notNull(featureName, "featureName");
        return new Predicate() {
            public boolean matches(Exchange exchange) {
                return ff4j.check(featureName);
            }

            @Override
            public String toString() {
                return "ff4j.check('" + featureName + "')";
            }
        };
    }
    
    /**
     * Use FF4J to check feature within 
     * @param featureName
     *      target feature name
     * @param fex
     *      feature context is required
     * @return
     *      value
     */
    public static Predicate ff4j(final String featureName, final FlippingExecutionContext fex) {
        notNull(featureName, "featureName");
        return new Predicate() {
            public boolean matches(Exchange exchange) {
                return ff4j.check(featureName, fex);
            }

            @Override
            public String toString() {
                return "ff4j.check('" + featureName + "')";
            }
        };
    }

    /**
     * Getter accessor for attribute 'ff4j'.
     *
     * @return
     *       current value of 'ff4j'
     */
    public static FF4j getFf4j() {
        return ff4j;
    }
}
