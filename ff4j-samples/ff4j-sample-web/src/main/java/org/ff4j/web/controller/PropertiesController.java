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
public class PropertiesController extends AbstractController {
	
	/** View name. */
	private static final String VIEW_PROPERTIES = "properties";
	
	/** {@inheritDoc} */
	public PropertiesController(FF4j ff4j, TemplateEngine te) {
		super(ff4j, VIEW_PROPERTIES, te);
	}

	/** {@inheritDoc} */
	public void process(HttpServletRequest req, HttpServletResponse res, WebContext ctx)
	throws IOException {
		ctx.setVariable(KEY_TITLE, "Properties");
	}

}
