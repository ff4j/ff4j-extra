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

import org.ff4j.core.Feature;

/**
 * Display monitoring data.
 *
 * @author <a href="mailto:cedrick.lunven@gmail.com">Cedrick LUNVEN</a>
 */
public class StatisticsBean {
    
    /** number of features to display. */
    private int nbFeature = 0;

    /** number of groups to display. */
    private int nbGroup = 0;

    /** number of events used. */
    private int nbEvents = 0;
    
     /** data for pie. */
    private String todayHitCountData;
    
    /** color of sector from pie. */
    private String todayHitCountColors;
    
    // ---- Choose features -----------
    
    /** list of features. */
    private List < Feature > listOfFeatures = new ArrayList<Feature>();

    /**
     * Getter accessor for attribute 'nbFeature'.
     *
     * @return
     *       current value of 'nbFeature'
     */
    public int getNbFeature() {
        return nbFeature;
    }

    /**
     * Setter accessor for attribute 'nbFeature'.
     * @param nbFeature
     * 		new value for 'nbFeature '
     */
    public void setNbFeature(int nbFeature) {
        this.nbFeature = nbFeature;
    }

    /**
     * Getter accessor for attribute 'nbGroup'.
     *
     * @return
     *       current value of 'nbGroup'
     */
    public int getNbGroup() {
        return nbGroup;
    }

    /**
     * Setter accessor for attribute 'nbGroup'.
     * @param nbGroup
     * 		new value for 'nbGroup '
     */
    public void setNbGroup(int nbGroup) {
        this.nbGroup = nbGroup;
    }

    /**
     * Getter accessor for attribute 'nbEvents'.
     *
     * @return
     *       current value of 'nbEvents'
     */
    public int getNbEvents() {
        return nbEvents;
    }

    /**
     * Setter accessor for attribute 'nbEvents'.
     * @param nbEvents
     * 		new value for 'nbEvents '
     */
    public void setNbEvents(int nbEvents) {
        this.nbEvents = nbEvents;
    }

    /**
     * Getter accessor for attribute 'todayHitCountData'.
     *
     * @return
     *       current value of 'todayHitCountData'
     */
    public String getTodayHitCountData() {
        return todayHitCountData;
    }

    /**
     * Setter accessor for attribute 'todayHitCountData'.
     * @param todayHitCountData
     * 		new value for 'todayHitCountData '
     */
    public void setTodayHitCountData(String todayHitCountData) {
        this.todayHitCountData = todayHitCountData;
    }

    /**
     * Getter accessor for attribute 'todayHitCountColors'.
     *
     * @return
     *       current value of 'todayHitCountColors'
     */
    public String getTodayHitCountColors() {
        return todayHitCountColors;
    }

    /**
     * Setter accessor for attribute 'todayHitCountColors'.
     * @param todayHitCountColors
     * 		new value for 'todayHitCountColors '
     */
    public void setTodayHitCountColors(String todayHitCountColors) {
        this.todayHitCountColors = todayHitCountColors;
    }

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
    

}
