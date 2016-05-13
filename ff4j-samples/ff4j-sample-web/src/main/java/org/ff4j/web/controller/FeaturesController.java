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


import static org.ff4j.web.embedded.ConsoleConstants.CONTENT_TYPE_JSON;
import static org.ff4j.web.embedded.ConsoleConstants.OP_DISABLE;
import static org.ff4j.web.embedded.ConsoleConstants.OP_ENABLE;
import static org.ff4j.web.embedded.ConsoleConstants.OP_READ_FEATURE;
import static org.ff4j.web.embedded.ConsoleConstants.OP_RMV_FEATURE;
import static org.ff4j.web.embedded.ConsoleRenderer.msg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ff4j.FF4j;
import org.ff4j.core.Feature;
import org.ff4j.utils.Util;
import org.ff4j.web.FF4jWebConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

/**
 * Controller for main class
 *
 * @author Cedrick LUNVEN (@clunven)
 */
public class FeaturesController extends AbstractController {

	 /** Logger for this class. */
    public static final Logger LOGGER = LoggerFactory.getLogger(FeaturesController.class);

    /** View name. */
	private static final String VIEW_FEATURES = "features";

	/** {@inheritDoc} */
	public FeaturesController(FF4j ff4j, TemplateEngine te) {
		super(ff4j, VIEW_FEATURES, te);
	}

	/** {@inheritDoc}
	 * @throws IOException */
    @Override
    public void process(HttpServletRequest req, HttpServletResponse res, WebContext ctx)
    throws IOException {
        String operation = req.getParameter(FF4jWebConstants.OPERATION);
	    String featureId = req.getParameter(FF4jWebConstants.FEATID);

	    String msgType = "success";
	    String msg     = null;
	    if (Util.hasLength(operation)) {
	      if ((featureId != null) && (!featureId.isEmpty())) {
            if (getFf4j().getFeatureStore().exist(featureId)) {

                if (OP_DISABLE.equalsIgnoreCase(operation)) {
                  getFf4j().disable(featureId);
                  msg = msg(featureId, "DISABLED");
                  LOGGER.info(featureId + " has been disabled");
               }

               if (OP_ENABLE.equalsIgnoreCase(operation)) {
                  getFf4j().enable(featureId);
                  msg = msg(featureId, "ENABLED");
                  LOGGER.info(featureId + " has been enabled");
               }

               if (OP_RMV_FEATURE.equalsIgnoreCase(operation)) {
                  getFf4j().getFeatureStore().delete(featureId);
                  msg = msg(featureId, "DELETED");
                  LOGGER.info(featureId + " has been deleted");
               }

               if (OP_READ_FEATURE.equalsIgnoreCase(operation)) {
                  Feature f = getFf4j().getFeatureStore().read(featureId);
                  res.setContentType(CONTENT_TYPE_JSON);
                  res.getWriter().println(f.toJson());
                  return;
               }

             } else {
                 msgType = "warning";
                 msg = "The feature '" + featureId + "' does not exist";
             }
           }
    	 }

	    ctx.setVariable("msgType", msgType);
        ctx.setVariable("msgInfo", msg);
	    renderPage(ctx);
	}

	private void renderPage(WebContext ctx) {
	    ctx.setVariable(KEY_TITLE, "Features");

        // Sort natural Order
        Map < String, Feature > mapOfFeatures = ff4j.getFeatureStore().readAll();
        List < String > featuresNames = Arrays.asList(mapOfFeatures.keySet().toArray(new String[0]));
        Collections.sort(featuresNames);
        List < Feature > orderedFeatures = new ArrayList<Feature>();
        for (String featuName : featuresNames) {
            orderedFeatures.add(mapOfFeatures.get(featuName));
        }
        ctx.setVariable("listOfFeatures", orderedFeatures);

        // Get Group List
        List <String > myGroupList = new ArrayList<String>(ff4j.getFeatureStore().readAllGroups());
        Collections.sort(myGroupList);
        ctx.setVariable("groupList", myGroupList);
	}

}
