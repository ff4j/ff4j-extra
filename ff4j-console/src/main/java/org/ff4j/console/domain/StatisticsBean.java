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
    
    /** featureId. */
    private String featureId;
    
    /** EventRepository implementation class. */
    private String type;
    
    /** number of events used. */
    private int totalHit = 0;
    
    /** data for pie. */
    private String pieChartData;
    
    /** title for pieChart. */
    private String pieChartTitle;
    
    /** color of sector from pie. */
    private String pieChartColors;
    
    /** barchart. */
    private String barChartTitle;
    
    /** barchart. */
    private String barChartLabels;
    
    /** barchart. */
    private String barChartSeriesData;
    
    /** barchart. */
    private String barChartSeriesNames;
    
    /** barchart. */
    private String barChartSeriesColors;
    
    // ---- Choose features -----------
    
    /** list of features. */
    private List < Feature > listOfFeatures = new ArrayList<Feature>();

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
     * Getter accessor for attribute 'totalHit'.
     *
     * @return
     *       current value of 'totalHit'
     */
    public int getTotalHit() {
        return totalHit;
    }

    /**
     * Setter accessor for attribute 'totalHit'.
     * @param totalHit
     * 		new value for 'totalHit '
     */
    public void setTotalHit(int totalHit) {
        this.totalHit = totalHit;
    }

    /**
     * Getter accessor for attribute 'featureId'.
     *
     * @return
     *       current value of 'featureId'
     */
    public String getFeatureId() {
        return featureId;
    }

    /**
     * Setter accessor for attribute 'featureId'.
     * @param featureId
     * 		new value for 'featureId '
     */
    public void setFeatureId(String featureId) {
        this.featureId = featureId;
    }

    /**
     * Getter accessor for attribute 'pieChartData'.
     *
     * @return
     *       current value of 'pieChartData'
     */
    public String getPieChartData() {
        return pieChartData;
    }

    /**
     * Setter accessor for attribute 'pieChartData'.
     * @param pieChartData
     * 		new value for 'pieChartData '
     */
    public void setPieChartData(String pieChartData) {
        this.pieChartData = pieChartData;
    }

    /**
     * Getter accessor for attribute 'type'.
     *
     * @return
     *       current value of 'type'
     */
    public String getType() {
        return type;
    }

    /**
     * Setter accessor for attribute 'type'.
     * @param type
     * 		new value for 'type '
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Getter accessor for attribute 'pieChartTitle'.
     *
     * @return
     *       current value of 'pieChartTitle'
     */
    public String getPieChartTitle() {
        return pieChartTitle;
    }

    /**
     * Setter accessor for attribute 'pieChartTitle'.
     * @param pieChartTitle
     * 		new value for 'pieChartTitle '
     */
    public void setPieChartTitle(String pieChartTitle) {
        this.pieChartTitle = pieChartTitle;
    }

    /**
     * Getter accessor for attribute 'pieChartColors'.
     *
     * @return
     *       current value of 'pieChartColors'
     */
    public String getPieChartColors() {
        return pieChartColors;
    }

    /**
     * Setter accessor for attribute 'pieChartColors'.
     * @param pieChartColors
     * 		new value for 'pieChartColors '
     */
    public void setPieChartColors(String pieChartColors) {
        this.pieChartColors = pieChartColors;
    }

    /**
     * Getter accessor for attribute 'barChartTitle'.
     *
     * @return
     *       current value of 'barChartTitle'
     */
    public String getBarChartTitle() {
        return barChartTitle;
    }

    /**
     * Setter accessor for attribute 'barChartTitle'.
     * @param barChartTitle
     * 		new value for 'barChartTitle '
     */
    public void setBarChartTitle(String barChartTitle) {
        this.barChartTitle = barChartTitle;
    }

    /**
     * Getter accessor for attribute 'barChartLabels'.
     *
     * @return
     *       current value of 'barChartLabels'
     */
    public String getBarChartLabels() {
        return barChartLabels;
    }

    /**
     * Setter accessor for attribute 'barChartLabels'.
     * @param barChartLabels
     * 		new value for 'barChartLabels '
     */
    public void setBarChartLabels(String barChartLabels) {
        this.barChartLabels = barChartLabels;
    }

    /**
     * Getter accessor for attribute 'barChartSeriesData'.
     *
     * @return
     *       current value of 'barChartSeriesData'
     */
    public String getBarChartSeriesData() {
        return barChartSeriesData;
    }

    /**
     * Setter accessor for attribute 'barChartSeriesData'.
     * @param barChartSeriesData
     * 		new value for 'barChartSeriesData '
     */
    public void setBarChartSeriesData(String barChartSeriesData) {
        this.barChartSeriesData = barChartSeriesData;
    }

    /**
     * Getter accessor for attribute 'barChartSeriesNames'.
     *
     * @return
     *       current value of 'barChartSeriesNames'
     */
    public String getBarChartSeriesNames() {
        return barChartSeriesNames;
    }

    /**
     * Setter accessor for attribute 'barChartSeriesNames'.
     * @param barChartSeriesNames
     * 		new value for 'barChartSeriesNames '
     */
    public void setBarChartSeriesNames(String barChartSeriesNames) {
        this.barChartSeriesNames = barChartSeriesNames;
    }

    /**
     * Getter accessor for attribute 'barChartSeriesColors'.
     *
     * @return
     *       current value of 'barChartSeriesColors'
     */
    public String getBarChartSeriesColors() {
        return barChartSeriesColors;
    }

    /**
     * Setter accessor for attribute 'barChartSeriesColors'.
     * @param barChartSeriesColors
     * 		new value for 'barChartSeriesColors '
     */
    public void setBarChartSeriesColors(String barChartSeriesColors) {
        this.barChartSeriesColors = barChartSeriesColors;
    }
    

}
