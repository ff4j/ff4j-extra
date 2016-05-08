package org.ff4j.web.controller;

/*
 * #%L
 * ff4j-sample-web
 * %%
 * Copyright (C) 2013 - 2016 FF4J
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


import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ff4j.FF4j;
import org.ff4j.audit.graph.PieSector;
import org.ff4j.audit.proxy.FeatureStoreAuditProxy;
import org.ff4j.audit.proxy.PropertyStoreAuditProxy;
import org.ff4j.core.FeatureStore;
import org.ff4j.property.store.PropertyStore;
import org.ff4j.utils.TimeUtils;
import org.ff4j.web.ApiConfig;
import org.ff4j.web.bean.HomeBean;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

/**
 * Controller for main class
 *
 * @author Cedrick LUNVEN (@clunven)
 */
public class HomeController extends AbstractController {
	
	/** View name. */
	private static final String VIEW_HOME = "home";
	
	/** {@inheritDoc} */
	public HomeController(FF4j ff4j, TemplateEngine te) {
		super(ff4j, VIEW_HOME, te);
	}

	/** {@inheritDoc} */
	public void process(HttpServletRequest req, HttpServletResponse res, WebContext ctx)
	throws IOException {

		ctx.setVariable(KEY_TITLE, "Home");
		
		HomeBean hb = new HomeBean();
		
		// General
		StringBuilder sb = new StringBuilder();
        long uptime = System.currentTimeMillis() - ff4j.getStartTime();
        long daynumber = uptime / (1000 * 3600 * 24L);
        uptime = uptime - daynumber * 1000 * 3600 * 24L;
        long hourNumber = uptime / (1000 * 3600L);
        uptime = uptime - hourNumber * 1000 * 3600L;
        long minutenumber = uptime / (1000 * 60L);
        uptime = uptime - minutenumber * 1000 * 60L;
        long secondnumber = uptime / 1000L;
        sb.append(daynumber + " days ");
        sb.append(hourNumber + " hours ");
        sb.append(minutenumber + " min ");
        sb.append(secondnumber + " sec");
        hb.setUptime(sb.toString());
        hb.setVersion(ff4j.getVersion());
        
        // Feature Store
        hb.setFeatureStore("---");
        if (ff4j.getFeatureStore() != null) {
        	FeatureStore fs = ff4j.getFeatureStore();
        	if (fs instanceof FeatureStoreAuditProxy) {
        		fs = ((FeatureStoreAuditProxy) fs).getTarget();
        	}
            hb.setFeatureStore(fs.getClass().getSimpleName());
            hb.setNbFeature(ff4j.getFeatureStore().readAll().size());
            hb.setNbGroup(ff4j.getFeatureStore().readAllGroups().size());
         }
        
        // PropertyStore
        if (ff4j.getPropertiesStore() != null) {
        	PropertyStore ps = ff4j.getPropertiesStore();
        	if (ps instanceof PropertyStoreAuditProxy) {
        		ps = ((PropertyStoreAuditProxy) ps).getTarget();
        	}
        	hb.setPropertyStore(ps.getClass().getSimpleName());
        	hb.setNbProperties(ff4j.getPropertiesStore().listPropertyNames().size());
        }
        
        // Security
        hb.setSecurity("---");
        if (ff4j.getAuthorizationsManager() != null) {
            hb.setSecurity(ff4j.getAuthorizationsManager().getClass().getSimpleName());
        }
        
        // Audit
        hb.setMonitoring("---");
        if (ff4j.getEventRepository() != null) {
            hb.setMonitoring(ff4j.getEventRepository().getClass().getSimpleName());
            hb.setNbEvents(ff4j.getEventRepository().getTotalEventCount());
            
            StringBuilder sbData = new StringBuilder("[");
            StringBuilder sbColor = new StringBuilder("[");
            List < PieSector > sectors = ff4j.getEventRepository().featuresListDistributionPie(
            		TimeUtils.getTodayMidnightTime(), System.currentTimeMillis()).getSectors();
            boolean first = true;
            for (PieSector sector : sectors) {
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
        
    	ctx.setVariable("homebean", hb);
        
    	ctx.setVariable("CHAINE",  req.getPathInfo());
    	ApiConfig api = new ApiConfig();
    	api.setHost("HOST_X");
    	ctx.setVariable("BEAN", api);
    	ctx.setVariable("today", Calendar.getInstance());
    	
    	List < ApiConfig > listA = new ArrayList<ApiConfig>();
    	listA.add(new ApiConfig());
    	listA.add(new ApiConfig());
    	listA.add(api);
    	ctx.setVariable("apis", listA);
	}

}
