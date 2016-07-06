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

import static org.ff4j.web.bean.WebConstants.ERROR;
import static org.ff4j.web.bean.WebConstants.FLIPFILE;
import static org.ff4j.web.bean.WebConstants.OPERATION;
import static org.ff4j.web.embedded.ConsoleOperations.importFile;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.ff4j.FF4j;
import org.ff4j.audit.repository.EventRepository;
import org.ff4j.core.FeatureStore;
import org.ff4j.property.store.PropertyStore;
import org.ff4j.web.bean.HomeBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

/**
 * Controller for main class
 *
 * @author Cedrick LUNVEN (@clunven)
 */
public class HomeController extends AbstractController {
	
    /** Logger for this class. */
    public static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
    
    /** Resolver for images. */
    public static final String START_PICTURE_URL = "static/img/db/db-";
    
	/** View name. */
	private static final String VIEW_HOME = "home";
	
	/** {@inheritDoc} */
	public HomeController(FF4j ff4j, TemplateEngine te) {
		super(ff4j, VIEW_HOME, te);
	}
	
	/** {@inheritDoc} */
    public void post(HttpServletRequest req, HttpServletResponse res, WebContext ctx)
    throws Exception {
        String msg       = null;
        String msgType   = "success";
        
        // Upload XML File
        if (ServletFileUpload.isMultipartContent(req)) {
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
            for (FileItem item : items) {
                if (item.isFormField()) {
                    if (OPERATION.equalsIgnoreCase(item.getFieldName())) {
                        LOGGER.info("Processing action : " + item.getString());
                    }
                } else if (FLIPFILE.equalsIgnoreCase(item.getFieldName())) {
                    String filename = FilenameUtils.getName(item.getName());
                    if (filename.toLowerCase().endsWith("xml")) {
                        importFile(getFf4j(), item.getInputStream());
                        msg = "The file <b>" + filename + "</b> has been successfully imported";
                    } else {
                        msgType = ERROR;
                        msg = "Invalid FILE, must be CSV, XML or PROPERTIES files";
                    }
                }
            }
        }
        ctx.setVariable("msgType", msgType);
        ctx.setVariable("msgInfo", msg);
        get(req, res, ctx);
    }
    
    /** {@inheritDoc} */
    public void get(HttpServletRequest req, HttpServletResponse res, WebContext ctx)
	throws Exception {

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
        	FeatureStore fs = ff4j.getConcreteFeatureStore();
        	hb.setFeatureStore(fs.getClass().getSimpleName().replaceAll("FeatureStore", ""));
            hb.setNbFeature(fs.readAll().size());
            hb.setNbGroup(fs.readAllGroups().size());
         }
        
        // PropertyStore
        if (ff4j.getPropertiesStore() != null) {
        	PropertyStore ps = ff4j.getConcretePropertyStore();
        	hb.setPropertyStore(ps.getClass().getSimpleName().replaceAll("PropertyStore", ""));
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
            EventRepository er = ff4j.getEventRepository();
            hb.setMonitoring(er.getClass().getSimpleName().replaceAll("EventRepository", ""));
        }
        
    	ctx.setVariable("homebean", hb);
    	ctx.setVariable("today", Calendar.getInstance());
	}

}
