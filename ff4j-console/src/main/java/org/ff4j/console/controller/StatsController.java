package org.ff4j.console.controller;

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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ff4j.console.ApplicationConstants;
import org.ff4j.console.domain.EnvironmenBean;
import org.ff4j.console.domain.StatisticsBean;
import org.ff4j.core.Feature;
import org.ff4j.core.FeatureStore;
import org.ff4j.web.api.FF4jWebConstants;
import org.ff4j.web.store.FeatureStoreHttp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller to display stats screen
 *
 * @author <a href="mailto:cedrick.lunven@gmail.com">Cedrick LUNVEN</a>
 */
@Controller
@RequestMapping("/" + ApplicationConstants.VIEW_STATS)
public class StatsController extends AbstractConsoleController {

    /**
     * Display screen
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showPage(HttpServletRequest req, HttpServletResponse res)
    throws Exception {
       
        // Environment de travail
        EnvironmenBean envBean = (EnvironmenBean) req.getSession().getAttribute(ATTR_ENVBEAN);
        log.info("Page <MONITORING>, action<GET>, env<" + envBean.getEnvId() + ">");
        
        // Access features through HTTP store (all parsing done)
        FeatureStore storeHTTP = new FeatureStoreHttp(envBean.getEnvUrl() + "/" + FF4jWebConstants.RESOURCE_FF4J);
      
        // StatisticBeans
        StatisticsBean statisticBean = new StatisticsBean();
        statisticBean.setListOfFeatures(new ArrayList<Feature>(storeHTTP.readAll().values()));
      
        // Display output page
        ModelAndView mav = new ModelAndView(VIEW_STATS);
        mav.addObject(ATTR_ENVBEAN, envBean);
        mav.addObject(ATTR_STATISTICBEAN, statisticBean);
        return mav;
    }

}
