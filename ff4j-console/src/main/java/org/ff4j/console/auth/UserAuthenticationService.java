package org.ff4j.console.auth;

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

import java.util.ArrayList;
import java.util.List;

import org.ff4j.console.conf.XmlConfigurationParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

/**
 * Authentication services based on dedicated configuration file.
 *
 * @author <a href="mailto:cedrick.lunven@gmail.com">Cedrick LUNVEN</a>
 */
@Repository("authenticateUser")
public class UserAuthenticationService implements UserDetailsService {

    /** Logger for the class. */
    protected Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private XmlConfigurationParser conf;

    /** {@inheritDoc} */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Authentication of " + username);
        // Initialization from XML
        List<GrantedAuthority> listOfRoles = new ArrayList<GrantedAuthority>();
        GrantedAuthority simpl = new SimpleGrantedAuthority("ROLE_USER");
        listOfRoles.add(simpl);
        User usr = new User("admin", "password", true, true, true, true, listOfRoles);
        return usr;
    }

    /**
     * Getter accessor for attribute 'conf'.
     *
     * @return current value of 'conf'
     */
    public XmlConfigurationParser getConf() {
        return conf;
    }

    /**
     * Setter accessor for attribute 'conf'.
     * 
     * @param conf
     *            new value for 'conf '
     */
    public void setConf(XmlConfigurationParser conf) {
        this.conf = conf;
    }

}