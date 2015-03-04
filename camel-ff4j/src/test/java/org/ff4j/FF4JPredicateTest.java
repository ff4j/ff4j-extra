/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ff4j;

import static org.ff4j.FF4JPredicateBuilder.checkFF4j;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Sample usage of FF4J with Apache Camel.
 *
 * @author <a href="mailto:cedrick.lunven@gmail.com">Cedrick LUNVEN</a>
 */
public class FF4JPredicateTest extends CamelTestSupport {   

    /**
     * Initialize FF4J for Builder
     */
    @BeforeClass
    public static void init() {
        FF4JPredicateBuilder.initFF4j(new FF4j().create("sample").enable("sample"));
    }
    
    /** {@inheritDoc} */
    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
                from("direct://foo").//
                    choice().when(checkFF4j("sample")).to("mock:ok"). //
                    otherwise().to("mock:ko").end(). //
                to("mock:end");
            }
        };
    }
    
    @Test
    public void testHelloWorld() throws Exception {
        
        // Expected to be OK (as sample is enabled)
        template().send("direct://foo", createExchangeWithBody("sample"));

        // Now changing status of feature
        FF4JPredicateBuilder.getFf4j().disable("sample");

        // Expected to be KO (as sample is now disabled)
        template().send("direct://foo", createExchangeWithBody("sample"));
        
        // Asserts
        getMockEndpoint("mock:ok").expectedMessageCount(1);
        getMockEndpoint("mock:ko").expectedMessageCount(1);
        getMockEndpoint("mock:end").expectedMessageCount(2);
        
        assertMockEndpointsSatisfied();
    }

    
   
}
