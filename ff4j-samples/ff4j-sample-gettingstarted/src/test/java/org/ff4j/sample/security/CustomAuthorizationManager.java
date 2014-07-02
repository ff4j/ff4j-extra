package org.ff4j.sample.security;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.ff4j.security.AuthorizationsManager;

public class CustomAuthorizationManager implements AuthorizationsManager {

    Map<String, Set<String>> permissions = new HashMap<String, Set<String>>();

    /**
     * Initialization of our sample
     */
    private void init() {
        // UserA
        String[] ss = {""};
        Set<String> usrAPermissions = new HashSet<String>(Arrays.asList(ss));
        
        
        
        // UserB

    }

    /** {@inheritDoc} */
    public Set<String> getCurrentUserPermissions() {
        // TODO Auto-generated method stub
        return null;
    }

    /** {@inheritDoc} */
    public Set<String> listAllPermissions() {
        return null;
    }

}
