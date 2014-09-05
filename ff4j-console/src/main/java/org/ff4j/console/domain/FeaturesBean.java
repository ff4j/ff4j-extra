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
import java.util.List;
import java.util.Set;

import org.ff4j.console.ApplicationConstants;
import org.ff4j.core.Feature;

/**
 * Bean for features screen (embedded console like)
 *
 * @author <a href="mailto:cedrick.lunven@gmail.com">Cedrick LUNVEN</a>
 */
public class FeaturesBean {
    
    /** features to be displayed. */
    private List < Feature > listOfFeatures = new ArrayList<Feature>();
    
    /** message to be displayed. */
    private String message = null;
    
    /** color of message to be displayed. */
    private String messageType = ApplicationConstants.MSG_INFO;
    
    /** expression (string) for permission list. */
    private Set < String > permissionList = null;
    
    /** list of group. */
    private Set < String > groupList = null;
    
    /** expression html for permissions. */
    private String htmlPermissions = null;
  
    /**
     * Getter accessor for attribute 'listOfFeatures'.
     *
     * @return
     *       current value of 'listOfFeatures'
     */
    public List<Feature> getListOfFeatures() {
        return listOfFeatures;
    }

    /**
     * Setter accessor for attribute 'listOfFeatures'.
     * @param listOfFeatures
     * 		new value for 'listOfFeatures '
     */
    public void setListOfFeatures(List<Feature> listOfFeatures) {
        this.listOfFeatures = listOfFeatures;
    }

    /**
     * Getter accessor for attribute 'message'.
     *
     * @return
     *       current value of 'message'
     */
    public String getMessage() {
        return message;
    }

    /**
     * Setter accessor for attribute 'message'.
     * @param message
     * 		new value for 'message '
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Getter accessor for attribute 'messageType'.
     *
     * @return
     *       current value of 'messageType'
     */
    public String getMessageType() {
        return messageType;
    }

    /**
     * Setter accessor for attribute 'messageType'.
     * @param messageType
     * 		new value for 'messageType '
     */
    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    /**
     * Getter accessor for attribute 'permissionList'.
     *
     * @return
     *       current value of 'permissionList'
     */
    public Set<String> getPermissionList() {
        return permissionList;
    }

    /**
     * Setter accessor for attribute 'permissionList'.
     * @param permissionList
     * 		new value for 'permissionList '
     */
    public void setPermissionList(Set<String> permissionList) {
        this.permissionList = permissionList;
    }

    /**
     * Getter accessor for attribute 'htmlPermissions'.
     *
     * @return
     *       current value of 'htmlPermissions'
     */
    public String getHtmlPermissions() {
        return htmlPermissions;
    }

    /**
     * Setter accessor for attribute 'htmlPermissions'.
     * @param htmlPermissions
     * 		new value for 'htmlPermissions '
     */
    public void setHtmlPermissions(String htmlPermissions) {
        this.htmlPermissions = htmlPermissions;
    }

    /**
     * Getter accessor for attribute 'groupList'.
     *
     * @return
     *       current value of 'groupList'
     */
    public Set<String> getGroupList() {
        return groupList;
    }

    /**
     * Setter accessor for attribute 'groupList'.
     * @param groupList
     * 		new value for 'groupList '
     */
    public void setGroupList(Set<String> groupList) {
        this.groupList = groupList;
    }

}
