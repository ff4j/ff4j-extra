package org.ff4j.web;

import static org.ff4j.web.FF4jWebConstants.ERROR;
import static org.ff4j.web.FF4jWebConstants.OP_EXPORT;
import static org.ff4j.web.FF4jWebConstants.VIEW_404;
import static org.ff4j.web.FF4jWebConstants.VIEW_API;
import static org.ff4j.web.FF4jWebConstants.VIEW_DEFAULT;
import static org.ff4j.web.FF4jWebConstants.VIEW_STATIC;
import static org.ff4j.web.embedded.ConsoleConstants.CONTENT_TYPE_HTML;
import static org.ff4j.web.embedded.ConsoleConstants.CONTENT_TYPE_JSON;
import static org.ff4j.web.embedded.ConsoleConstants.FEATID;
import static org.ff4j.web.embedded.ConsoleConstants.OPERATION;
import static org.ff4j.web.embedded.ConsoleConstants.OP_ADD_FIXEDVALUE;
import static org.ff4j.web.embedded.ConsoleConstants.OP_DELETE_FIXEDVALUE;
import static org.ff4j.web.embedded.ConsoleConstants.OP_DISABLE;
import static org.ff4j.web.embedded.ConsoleConstants.OP_ENABLE;
import static org.ff4j.web.embedded.ConsoleConstants.OP_READ_PROPERTY;
import static org.ff4j.web.embedded.ConsoleConstants.OP_RMV_FEATURE;
import static org.ff4j.web.embedded.ConsoleConstants.OP_RMV_PROPERTY;
import static org.ff4j.web.embedded.ConsoleConstants.PARAM_FIXEDVALUE;
import static org.ff4j.web.embedded.ConsoleOperations.exportFile;
import static org.ff4j.web.embedded.ConsoleRenderer.msg;
import static org.ff4j.web.embedded.ConsoleRenderer.renderMessageBox;
import static org.ff4j.web.embedded.ConsoleRenderer.renderMsgProperty;
import static org.ff4j.web.embedded.ConsoleRenderer.renderPage;

/*
 * #%L
 * AdministrationConsoleServlet.java (ff4j-web) by Cedrick LUNVEN
 * %%
 * Copyright (C) 2013 Ff4J
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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ff4j.property.Property;
import org.ff4j.web.embedded.ConsoleRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Unique Servlet to manage FlipPoints and security
 *
 * @author <a href="mailto:cedrick.lunven@gmail.com">Cedrick LUNVEN</a>
 */
public class FF4jDispatcherServlet extends FF4jServlet {

    /** serial number. */
    private static final long serialVersionUID = -3982043895954284269L;

    /** Logger for this class. */
    public static final Logger LOGGER = LoggerFactory.getLogger(FF4jDispatcherServlet.class);


    /** {@inheritDoc} */
    public void doGet(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {
        
    	String targetView  = getTargetView(req);

    	if (VIEW_STATIC.equals(targetView) && req.getPathInfo().length() > 1) {
    		staticResourceController.get(req, res, null);

    	} else if (VIEW_API.equals(targetView)) {
    		operationsController.get(req, res, null);

    	} else if (!mapOfControllers.containsKey(targetView)) {
        	targetView = VIEW_404;
        	
        } else {
        	mapOfControllers.get(targetView).get(req, res);
    	}
    }

    /** {@inheritDoc} */
    public void doPost(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {
       
        String targetView = getTargetView(req);
        
        if (VIEW_API.equals(targetView)) {
            operationsController.post(req, res, null);
        
        } else if (!mapOfControllers.containsKey(targetView)) {
            targetView = VIEW_404;
        
        } else {
            mapOfControllers.get(targetView).post(req, res);
        }

        /*
                String operation = req.getParameter(OPERATION);
                String uid       = req.getParameter(FEATID);
                String message = null;
                String messagetype = "info";
                LOGGER.info("POST - op=" + operation + " feat=" + uid);
                if (operation != null && !operation.isEmpty()) {
                    

                    } else if (OP_CREATE_PROPERTY.equalsIgnoreCase(operation)) {
                        createProperty(getFf4j(), req);
                        message = renderMsgProperty(req.getParameter(NAME), "ADDED");
                    
                    } else {
                        LOGGER.error("Invalid POST OPERATION" + operation);
                        messagetype = ERROR;
                        message = "Invalid REQUEST";
                    }
                } else {
                    LOGGER.error("No ID provided" + operation);
                    messagetype = ERROR;
                    message = "Invalid UID";
                }*/

    }

    /**
     * Current target view.
     *
     * @param req
     *      current http request
     * @return
     *      target view
     */
    private String getTargetView(HttpServletRequest req) {
        String targetView  = VIEW_DEFAULT;
        String pathInfo    = req.getPathInfo();
        if (pathInfo != null) {
            String[] pathParts = pathInfo.split("/");
            if (pathParts.length > 1) {
                targetView = pathParts[1];
            }
        }
        return targetView;
    }
    
    
    public void pageCore(HttpServletRequest req, HttpServletResponse res) throws IOException {
    	String message = null;
        String messagetype = "info";
        try {

            // 'RSC' parameter will load some static resources
            if (ConsoleRenderer.renderResources(req, res)) return;

            // Serve operation from GET
            String operation = req.getParameter(OPERATION);
            String featureId = req.getParameter(FEATID);
            LOGGER.info("GET - op=" + operation + " feat=" + featureId);
            if (operation != null && !operation.isEmpty()) {

                // operation which do not required features
                if (OP_EXPORT.equalsIgnoreCase(operation)) {
                    exportFile(ff4j, res);
                    return;
                }

                // Work on a feature ID
                if ((featureId != null) && (!featureId.isEmpty())) {

                    if (getFf4j().getFeatureStore().exist(featureId)) {

                        if (OP_DISABLE.equalsIgnoreCase(operation)) {
                            getFf4j().disable(featureId);
                            res.setContentType(CONTENT_TYPE_HTML);
                            res.getWriter().println(renderMessageBox(msg(featureId, "DISABLED"), "info"));
                            LOGGER.info(featureId + " has been disabled");
                            return;
                        }

                        if (OP_ENABLE.equalsIgnoreCase(operation)) {
                            getFf4j().enable(featureId);
                            res.setContentType(CONTENT_TYPE_HTML);
                            res.getWriter().println(renderMessageBox(msg(featureId, "ENABLED"), "info"));
                            LOGGER.info("Feature '" + featureId + "' has been successfully enabled");
                            return;
                        }

                        // As no return the page is draw
                        if (OP_RMV_FEATURE.equalsIgnoreCase(operation)) {
                            getFf4j().getFeatureStore().delete(featureId);
                            message = msg(featureId, "DELETED");
                            LOGGER.info(featureId + " has been deleted");
                        }

                    }

                    if (getFf4j().getPropertiesStore().existProperty(featureId)) {

                        if (OP_RMV_PROPERTY.equalsIgnoreCase(operation)) {
                            getFf4j().getPropertiesStore().deleteProperty(featureId);
                            message = renderMsgProperty(featureId, "DELETED");
                            LOGGER.info("Property '" + featureId + "' has been deleted");
                        }

                        if (OP_READ_PROPERTY.equalsIgnoreCase(operation)) {
                            Property<?> ap = getFf4j().getPropertiesStore().readProperty(featureId);
                            res.setContentType(CONTENT_TYPE_JSON);
                            res.getWriter().println(ap.toString());
                            return;
                        }

                        if (OP_DELETE_FIXEDVALUE.equalsIgnoreCase(operation)) {
                            String fixedValue = req.getParameter(PARAM_FIXEDVALUE);
                            Property<?> ap = getFf4j().getPropertiesStore().readProperty(featureId);
                            ap.getFixedValues().remove(fixedValue);
                            getFf4j().getPropertiesStore().updateProperty(ap);
                            return;
                        }

                        if (OP_ADD_FIXEDVALUE.equalsIgnoreCase(operation)) {
                            String fixedValue = req.getParameter(PARAM_FIXEDVALUE);
                            Property<?> ap = getFf4j().getPropertiesStore().readProperty(featureId);
                            ap.add2FixedValueFromString(fixedValue);
                            getFf4j().getPropertiesStore().updateProperty(ap);
                            return;
                        }

                    }

                }
            }

        } catch (Exception e) {
            // Any Error is trapped and display in the console
            messagetype = ERROR;
            message = e.getMessage();
            LOGGER.error("An error occured ", e);
        }

        // Default page rendering (table)
        renderPage(getFf4j(), req, res, message, messagetype);
    }
   
}
