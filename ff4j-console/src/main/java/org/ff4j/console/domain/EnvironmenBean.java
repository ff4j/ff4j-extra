package org.ff4j.console.domain;

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

import java.util.ArrayList;

import org.ff4j.console.conf.xml.Connection;

/**
 * WebBean to list environements.
 *
 * @author <a href="mailto:cedrick.lunven@gmail.com">Cedrick LUNVEN</a>
 */
public class EnvironmenBean {

    /** environment id. */
    private String envId;

    /** environment url. */
    private String envUrl;

    /** connection mode. */
    private String connectionMode;

    /** list of environment (display dropdown) */
    private ArrayList<Connection> listOfConnection = new ArrayList<Connection>();
    
    /** presence of configuration file. */
    private boolean confFilePresent = true;
    
    /** presence of configuration file. */
    private boolean confFileParsed = true;

    /**
     * Populate connection informations
     * 
     * @param conn
     *            connection
     */
    public EnvironmenBean() {}
    
    /**
     * Populate connection informations
     * 
     * @param conn
     *            connection
     */
    public EnvironmenBean(Connection conn) {
        this.envId = conn.getId();
        this.envUrl = conn.getUrl();
        this.connectionMode = conn.getMode();
    }

    /**
     * Populate connection informations
     * 
     * @param conn
     *            connection
     */
    public EnvironmenBean(Connection conn, ArrayList<Connection> lc) {
        this.envId = conn.getId();
        this.envUrl = conn.getUrl();
        this.connectionMode = conn.getMode();
        this.listOfConnection = lc;
    }

    /**
     * Populate connection informations
     * 
     * @param conn
     *            connection
     */
    public EnvironmenBean(ArrayList<Connection> lc) {
        this.listOfConnection = lc;
    }

    /**
     * Getter accessor for attribute 'envId'.
     *
     * @return current value of 'envId'
     */
    public String getEnvId() {
        return envId;
    }

    /**
     * Setter accessor for attribute 'envId'.
     * 
     * @param envId
     *            new value for 'envId '
     */
    public void setEnvId(String envId) {
        this.envId = envId;
    }

    /**
     * Getter accessor for attribute 'envUrl'.
     *
     * @return current value of 'envUrl'
     */
    public String getEnvUrl() {
        return envUrl;
    }

    /**
     * Setter accessor for attribute 'envUrl'.
     * 
     * @param envUrl
     *            new value for 'envUrl '
     */
    public void setEnvUrl(String envUrl) {
        this.envUrl = envUrl;
    }

    /**
     * Getter accessor for attribute 'connectionMode'.
     *
     * @return current value of 'connectionMode'
     */
    public String getConnectionMode() {
        return connectionMode;
    }

    /**
     * Setter accessor for attribute 'connectionMode'.
     * 
     * @param connectionMode
     *            new value for 'connectionMode '
     */
    public void setConnectionMode(String connectionMode) {
        this.connectionMode = connectionMode;
    }

    /**
     * Getter accessor for attribute 'listOfConnection'.
     *
     * @return current value of 'listOfConnection'
     */
    public ArrayList<Connection> getListOfConnection() {
        return listOfConnection;
    }

    /**
     * Setter accessor for attribute 'listOfConnection'.
     * 
     * @param listOfConnection
     *            new value for 'listOfConnection '
     */
    public void setListOfConnection(ArrayList<Connection> listOfConnection) {
        this.listOfConnection = listOfConnection;
    }

    /**
     * Getter accessor for attribute 'confFilePresent'.
     *
     * @return
     *       current value of 'confFilePresent'
     */
    public boolean isConfFilePresent() {
        return confFilePresent;
    }

    /**
     * Setter accessor for attribute 'confFilePresent'.
     * @param confFilePresent
     * 		new value for 'confFilePresent '
     */
    public void setConfFilePresent(boolean confFilePresent) {
        this.confFilePresent = confFilePresent;
    }

    /**
     * Getter accessor for attribute 'confFileParsed'.
     *
     * @return
     *       current value of 'confFileParsed'
     */
    public boolean isConfFileParsed() {
        return confFileParsed;
    }

    /**
     * Setter accessor for attribute 'confFileParsed'.
     * @param confFileParsed
     * 		new value for 'confFileParsed '
     */
    public void setConfFileParsed(boolean confFileParsed) {
        this.confFileParsed = confFileParsed;
    }

}
