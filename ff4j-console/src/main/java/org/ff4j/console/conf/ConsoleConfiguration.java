package org.ff4j.console.conf;

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

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import org.ff4j.console.conf.xml.Connection;
import org.ff4j.console.conf.xml.User;

/**
 * Easy-to-use Configuration Bean
 *
 * @author <a href="mailto:cedrick.lunven@gmail.com">Cedrick LUNVEN</a>
 */
public class ConsoleConfiguration implements Serializable {

    /** serial. */
    private static final long serialVersionUID = 7118117781354548178L;

    /** Default Langugage. */
    private String language = Locale.ENGLISH.getISO3Language();
    
    /** Status of parsing. */
    private boolean confFilePresent = false;
    
    /** Status of parsing. */
    private boolean confFileParsed = false;

    /** available users. */
    private Map<String, User> mapOfUser = new HashMap<String, User>();
    
    /** Store connection but keep ordrer for dropdown. */
    private Map<String, Connection> mapOfConnections = new LinkedHashMap<String, Connection>();

    /**
     * Getter accessor for attribute 'language'.
     *
     * @return
     *       current value of 'language'
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Setter accessor for attribute 'language'.
     * @param language
     * 		new value for 'language '
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Getter accessor for attribute 'mapOfUser'.
     *
     * @return
     *       current value of 'mapOfUser'
     */
    public Map<String, User> getMapOfUser() {
        return mapOfUser;
    }

    /**
     * Setter accessor for attribute 'mapOfUser'.
     * @param mapOfUser
     * 		new value for 'mapOfUser '
     */
    public void setMapOfUser(Map<String, User> mapOfUser) {
        this.mapOfUser = mapOfUser;
    }

    /**
     * Getter accessor for attribute 'mapOfConnections'.
     *
     * @return
     *       current value of 'mapOfConnections'
     */
    public Map<String, Connection> getMapOfConnections() {
        return mapOfConnections;
    }

    /**
     * Setter accessor for attribute 'mapOfConnections'.
     * @param mapOfConnections
     * 		new value for 'mapOfConnections '
     */
    public void setMapOfConnections(Map<String, Connection> mapOfConnections) {
        this.mapOfConnections = mapOfConnections;
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
