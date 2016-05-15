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


import static org.ff4j.web.FF4jWebConstants.OP_EXPORT;
import static org.ff4j.web.embedded.ConsoleConstants.CONTENT_TYPE_JSON;
import static org.ff4j.web.embedded.ConsoleConstants.OP_FEATURES;
import static org.ff4j.web.embedded.ConsoleOperations.exportFile;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response.Status;

import org.ff4j.FF4j;
import org.ff4j.core.Feature;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

public class OperationsController extends AbstractController {

    /** {@inheritDoc} */
    public OperationsController(FF4j ff4j, TemplateEngine te) {
        super(ff4j, null, te);
    }

    /** {@inheritDoc} */
    public void post(HttpServletRequest req, HttpServletResponse res, WebContext ctx)
    throws IOException {
    }
    
    /** {@inheritDoc} */
    public void get(HttpServletRequest req, HttpServletResponse res, WebContext ctx)
    throws IOException {
        String[] pathParts = req.getPathInfo().split("/");
        String operation   = pathParts[2];

        if (OP_EXPORT.equalsIgnoreCase(operation)) {
            exportFile(ff4j, res);
            return;

        } else if (OP_FEATURES.equalsIgnoreCase(operation)) {
            res.setContentType(CONTENT_TYPE_JSON);
            if (pathParts.length > 3) {
                String featureId   = pathParts[3];
                if (getFf4j().getFeatureStore().exist(featureId)) {
                    Feature f = getFf4j().getFeatureStore().read(featureId);
                    res.getWriter().println(f.toJson());
                } else {
                    res.setStatus(Status.NOT_FOUND.getStatusCode());
                    res.getWriter().println("Feature " + featureId + " does not exist in feature store." );
                }
            } else {
                Map< String, Feature > mapOfFeatures = getFf4j().getFeatureStore().readAll();
                StringBuilder sb = new StringBuilder("[");
                boolean first = true;
                for (Feature feature : mapOfFeatures.values()) {
                    if (!first) {
                        sb.append(",");
                    }
                    sb.append(feature.toJson());
                   first = false;
                }
                sb.append("]");
                res.getWriter().println(sb.toString());
            }
            return;
        }
    }

}
