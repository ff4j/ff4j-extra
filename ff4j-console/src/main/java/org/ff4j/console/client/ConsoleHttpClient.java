package org.ff4j.console.client;

/*
 * #%L ff4j-console %% Copyright (C) 2013 - 2014 Ff4J %% Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License. #L%
 */

import static org.ff4j.web.store.FeatureStoreHttp.buildAuthorization4ApiKey;
import static org.ff4j.web.store.FeatureStoreHttp.buildAuthorization4UserName;

import java.util.HashSet;
import java.util.Set;

import org.codehaus.jackson.map.ObjectMapper;
import org.ff4j.console.ApplicationConstants;
import org.ff4j.console.conf.xml.Connection;
import org.ff4j.console.domain.FeaturesBean;
import org.ff4j.console.domain.HomeBean;
import org.ff4j.console.domain.StatisticsBean;
import org.ff4j.web.FF4jWebConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

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
        config.getClasses().add(FeaturesBeanMsgBodyReader.class);
        config.getClasses().add(StatsBeanMsgBodyReader.class);
        config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        jerseyClient = Client.create(config);

        // Target URL
        url = ff4jConnection.getUrl() + "/" + RESOURCE_FF4J;
        log.debug("Jersey Client initialized");
    }

    /**
     * Build target request with security if required.
     *
     * @param url
     *      target url to contact
     * @return
     *      target request {@link ClientResponse}
     */
    private ClientResponse buildPOSTRequest(String url) {
        if (null != ff4jConnection.getAuthKey() && !"".equals(ff4jConnection.getAuthKey())) {
            String header = buildAuthorization4ApiKey(ff4jConnection.getAuthKey());
            return jerseyClient.resource(url).header(HEADER_AUTHORIZATION, header).post(ClientResponse.class);
        } else if (null != ff4jConnection.getUserName() && !"".equals(ff4jConnection.getUserName())) {
            String header = buildAuthorization4UserName(ff4jConnection.getUserName(), ff4jConnection.getPassword());
            return jerseyClient.resource(url).header(HEADER_AUTHORIZATION, header).post(ClientResponse.class);
        }
        return jerseyClient.resource(url).post(ClientResponse.class);
    }
    
    /**
     * Build target request with security if required.
     *
     * @param url
     *      target url to contact
     * @return
     *      target request {@link ClientResponse}
     */
    private ClientResponse buildGETRequest(String url) {
        if (null != ff4jConnection.getAuthKey() && !"".equals(ff4jConnection.getAuthKey())) {
            String header = buildAuthorization4ApiKey(ff4jConnection.getAuthKey());
            return jerseyClient.resource(url).header(HEADER_AUTHORIZATION, header).get(ClientResponse.class);
        } else if (null != ff4jConnection.getUserName() && !"".equals(ff4jConnection.getUserName())) {
            String header = buildAuthorization4UserName(ff4jConnection.getUserName(), ff4jConnection.getPassword());
            return jerseyClient.resource(url).header(HEADER_AUTHORIZATION, header).get(ClientResponse.class);
        }
        return jerseyClient.resource(url).get(ClientResponse.class);
    }
    
    /**
     * Build HomePage
     * 
     * @return target home page bean
     */
    public HomeBean getHome() {
        return buildGETRequest(url).getEntity(HomeBean.class);
    }
    
    /** Custom reader. */
    public StatisticsBean getStatisticBean(String uid) {
        ClientResponse cRes;
        if (uid != null && !"".equals(uid)) {
            cRes =  buildGETRequest(url + "/monitoring/" + uid);
        } else {
            cRes =  buildGETRequest(url + "/monitoring");
        }
        return cRes.getEntity(StatisticsBean.class);
    }
    
    /**
     * Clear Cache.
     */
    public void clearCache() {
        ClientResponse response = buildPOSTRequest(url + "/store/cache");
        if (response.getStatus() != 200) {
            throw new IllegalStateException("Cannot clear cache");
        }
    }
    
    /**
     * Population permissions.
     *
     * @return
     */
    public Set<String> getAllPermissions() {
        String myUrl = ff4jConnection.getUrl() + "/" + RESOURCE_FF4J + "/" + RESOURCE_SECURITY;
        // If the FF4J instance we're connecting to doesn't have security set up,
        // this results in a JsonParseException
        try {
            return buildGETRequest(myUrl).getEntity(FeaturesBean.class).getPermissionList();
        } catch (Exception ex) {
            log.debug("Exception! " + ex.getLocalizedMessage());
            return new HashSet<>();
        }
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
