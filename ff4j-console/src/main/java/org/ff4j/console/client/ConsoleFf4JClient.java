package org.ff4j.console.client;

/*
 * #%L
 * ff4j-console
 * %%
 * Copyright (C) 2013 - 2015 Ff4J
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

import org.ff4j.FF4j;
import org.ff4j.cache.FeatureStoreCacheProxy;
import org.ff4j.console.domain.HomeBean;
import org.ff4j.web.api.resources.domain.FF4jStatusApiBean;
import org.ff4j.web.api.resources.domain.PieSectorApiBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Client with FF4J usage
 *
 * @author <a href="mailto:cedrick.lunven@gmail.com">Cedrick LUNVEN</a>
 */
public class ConsoleFf4JClient {
    
    /** logger for the class. */
    protected Logger log = LoggerFactory.getLogger(getClass());
    
    /**
     * Create home page.
     *
     * @return
     */
    public static HomeBean getHomeBean(FF4j ff4j) {
        // Populate beans as for API
        FF4jStatusApiBean sfab = new FF4jStatusApiBean(ff4j);
        HomeBean hb = new HomeBean();
        hb.setUptime(sfab.getUptime());
        hb.setVersion(sfab.getVersion());
        // Store
        if (ff4j.getFeatureStore() != null) {
            hb.setStore(sfab.getFeaturesStore().getClass().getName());
            hb.setNbFeature(sfab.getFeaturesStore().getNumberOfFeatures());
            hb.setNbGroup(sfab.getFeaturesStore().getNumberOfGroups());
            hb.setNbEvents(sfab.getEventRepository().getHitCount());
        }
        // Security
        hb.setSecurity("---");
        if (ff4j.getAuthorizationsManager() != null) {
            hb.setSecurity(ff4j.getAuthorizationsManager().getClass().getName());
        }
        // Cache
        hb.setCaching("---");
        hb.setCache(ff4j.getFeatureStore().isCached());
        if (hb.isCache()) {
            Set < String > featureNames = ((FeatureStoreCacheProxy) ff4j.getFeatureStore()).getCacheManager().listCachedFeatureNames();
            hb.setCacheFeature(new ArrayList<String>(featureNames));
            hb.setCaching(ff4j.getFeatureStore().getCacheProvider());
        }
        // Monitoring
        hb.setMonitoring("---");
        if (ff4j.getEventRepository() != null) {
            hb.setMonitoring(ff4j.getEventRepository().getClass().getName());
            StringBuilder sbData = new StringBuilder("[");
            StringBuilder sbColor = new StringBuilder("[");
            List <PieSectorApiBean > sectors = sfab.getEventRepository().getEventsPie().getSectors();
            boolean first = true;
            for (PieSectorApiBean sector : sectors) {
                if (!first) {
                    sbData.append(",");
                    sbColor.append(",");
                }
                sbData.append("['" + sector.getLabel() + "', " + String.valueOf(sector.getValue()) + "]");
                sbColor.append("\"#" + sector.getColor() + "\"");
                first = false;
            }
            sbData.append("]");
            hb.setTodayHitCountData(sbData.toString());
            sbColor.append("]");
            hb.setTodayHitCountColors(sbColor.toString());
        }
        return hb;
    }

}
