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

import org.ff4j.web.ApiConfig;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

/**
 * Controller for main class
 *
 * @author Cedrick LUNVEN (@clunven)
 */
public class HomeController extends AbstractController {

	/** {@inheritDoc} */
	public void process(HttpServletRequest req, HttpServletResponse res, TemplateEngine engine) throws IOException {
		
		WebContext ctx = 
				new WebContext(req, res,  req.getSession().getServletContext(), req.getLocale());
    	
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
    	
    	engine.process("home", ctx, res.getWriter());
    	
	}

}
