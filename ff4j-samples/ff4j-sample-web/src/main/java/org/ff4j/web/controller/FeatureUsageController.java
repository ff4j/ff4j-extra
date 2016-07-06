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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ff4j.FF4j;
import org.ff4j.web.bean.WebConstants;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

/**
 * Controller for main class
 *
 * @author Cedrick LUNVEN (@clunven)
 */
public class FeatureUsageController extends AbstractController {
    
    /** Date format. */
    public static SimpleDateFormat SDF = new SimpleDateFormat("YYYYMMdd-HHmmss");
	
	/** {@inheritDoc} */
	public FeatureUsageController(FF4j ff4j, TemplateEngine te) {
		super(ff4j, WebConstants.VIEW_FEATURE_USAGE, te);
	}

	/** {@inheritDoc} */
    public void post(HttpServletRequest req, HttpServletResponse res, WebContext ctx)
    throws Exception {
    }
    
    /** {@inheritDoc} */
    public void get(HttpServletRequest req, HttpServletResponse res, WebContext ctx)
	throws Exception {
		ctx.setVariable(KEY_TITLE, "Feature Usage");
		
		 // Today MidNight
        long startTime = 0;
        String startParam = req.getParameter(WebConstants.START_DATE);
        if (startParam == null) {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            startTime = c.getTimeInMillis();
        } else {
            startTime = SDF.parse(startParam).getTime(); 
        }
        
        // Tomorrow 00:00
        long endTime   = 0; 
        String endDate = req.getParameter(WebConstants.END_DATE);
        if (endDate == null) {
            Calendar c2 = Calendar.getInstance();
            c2.setTime(new Date(System.currentTimeMillis() + 1000 * 3600 * 24));
            c2.set(Calendar.HOUR_OF_DAY, 0);
            c2.set(Calendar.MINUTE, 0);
            c2.set(Calendar.SECOND, 0);
            endTime = c2.getTimeInMillis();
        } else {
            startTime = SDF.parse(endDate).getTime();
        }
        
        // Distribution PIE (Percent per feature)
        ff4j.getEventRepository().getFeatureUsagePieChart(startTime, endTime);
		/* BarChart barChart  = ff4j.getEventRepository().getFeatureUsageBarChart(startTime, endTime);
		 ctx.setVariable("pieChart", pieChart);
		 ctx.setVariable("barChart", barChart);
		*/
		
	}

}
