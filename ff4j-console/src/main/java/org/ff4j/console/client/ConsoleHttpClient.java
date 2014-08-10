package org.ff4j.console.client;

/*
 * #%L
 * ff4j-console
 * %%
 * Copyright (C) 2013 - 2014 Ff4J
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import org.codehaus.jackson.map.ObjectMapper;
import org.ff4j.console.ApplicationConstants;
import org.ff4j.console.conf.xml.Connection;
import org.ff4j.console.domain.HomeBean;
import org.ff4j.web.api.FF4jWebConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * Connect to FF4J using http client.
 *
 * @author <a href="mailto:cedrick.lunven@gmail.com">Cedrick LUNVEN</a>
 */
public class ConsoleHttpClient implements ApplicationConstants, FF4jWebConstants {

    /** logger for the class. */
    protected Logger log = LoggerFactory.getLogger(getClass());

    /** Jackson Mapper. */
    protected ObjectMapper jacksonMapper = new ObjectMapper();

    /** Parser for HTTP Remote. */
    private final Client jerseyClient;

    /** Target connection. */
    private Connection ff4jConnection;

    /** Target url. */
    private String url;

    /**
     * Constructor with HTTP.
     *
     * @param conn
     */
    public ConsoleHttpClient(Connection conn) {
        this.ff4jConnection = conn;
        if (!MODE_HTTP.equalsIgnoreCase(conn.getMode())) {
            throw new IllegalArgumentException("The target connection is not of type HTTP");
        }
        // Configuration of jersey
        ClientConfig config = new DefaultClientConfig();
        config.getClasses().add(HomeBeanMsgBodyReader.class);

        jerseyClient = Client.create(config);

        url = conn.getUrl() + "/" + RESOURCE_FF4J;
        if (StringUtils.hasLength(conn.getAuthKey())) {
            url += "?authKey=" + conn.getAuthKey();
        }
        log.debug("Jersey Client initialized");
    }

    public HomeBean readFF4j() {
        // Make GET operation
        ClientResponse cRes = jerseyClient.resource(url).get(ClientResponse.class);
        // Parsing result to homebean
        return cRes.getEntity(HomeBean.class);
    }

    /**
     * Getter accessor for attribute 'ff4jConnection'.
     *
     * @return current value of 'ff4jConnection'
     */
    public Connection getFf4jConnection() {
        return ff4jConnection;
    }

    /**
     * Setter accessor for attribute 'ff4jConnection'.
     * 
     * @param ff4jConnection
     *            new value for 'ff4jConnection '
     */
    public void setFf4jConnection(Connection ff4jConnection) {
        this.ff4jConnection = ff4jConnection;
    }
    

}
