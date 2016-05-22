package org.ff4j.web.controller;

import static org.ff4j.web.utils.WebUtils.getCookie;
import static org.ff4j.web.utils.WebUtils.getSessionAttribute;
import static org.ff4j.web.utils.WebUtils.setSessionAttribute;

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
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response.Status;

import org.ff4j.FF4j;
import org.ff4j.web.WebConstants;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

/**
 * Display view.
 *
 * @author Cedrick LUNVEN (@clunven)
 */
public abstract class AbstractController {

	/** KEY. */
	protected static final String KEY_TITLE =  "TITLE";

	/** FF4J instance. */
	protected FF4j ff4j;

    /** Template engine. */
	protected TemplateEngine templateEngine = null;

    /** Success View. */
    protected String successView = null;

    /**
     * Default constructor.
     *
     * @param ff4j
     * 		current instance of FF4J.
     * @param te
     * 		target template engine.
     */
    public AbstractController(FF4j ff4j, String view, TemplateEngine te) {
    	this.ff4j = ff4j;
    	this.successView = view;
    	this.templateEngine = te;
    }

    /**
     * Format uptime.
     * 
     * @return
     *      current runtime
     */
    private String getUptime() {
        StringBuilder sb = new StringBuilder();
        long uptime = System.currentTimeMillis() - ff4j.getStartTime();
        long daynumber = uptime / (1000 * 3600 * 24L);
        uptime = uptime - daynumber * 1000 * 3600 * 24L;
        long hourNumber = uptime / (1000 * 3600L);
        uptime = uptime - hourNumber * 1000 * 3600L;
        long minutenumber = uptime / (1000 * 60L);
        sb.append(daynumber + " days ");
        sb.append(hourNumber + " hours ");
        sb.append(minutenumber + " min ");
        return sb.toString();
    }
    
    /**
     * Define response Locale (Cookie <-> HttpSession <-> Request)
     *
     * @param req
     *      current request.
     * @param res
     *      current response.
     */
    private void i18n(HttpServletRequest req, HttpServletResponse res) {
        
        String lang = req.getParameter(WebConstants.LANG);
        if (lang != null) {
            // Update Request
            res.setLocale(new Locale(lang));
            // Update Session
            setSessionAttribute(req, WebConstants.LANG_ATTRIBUTE, lang);
            // Create Cookie
            Cookie cookie = new Cookie(WebConstants.LANG_ATTRIBUTE, lang);
            cookie.setMaxAge(365 * 24 * 60 * 60); // 1 years
            res.addCookie(cookie);
            
        } else {
            String langSession = (String) getSessionAttribute(req, WebConstants.LANG_ATTRIBUTE);
            if (langSession != null) {
                res.setLocale(new Locale(langSession));
            } else {
                // Not in session, look for cookie if cookie update session
               Cookie cookie = getCookie(req, WebConstants.LANG_ATTRIBUTE);
               if (cookie != null) {
                   setSessionAttribute(req, WebConstants.LANG_ATTRIBUTE, cookie.getValue());
                   res.setLocale(new Locale(cookie.getValue()));
               }
            }
        }
    }
    
    /**
     * Invoked by dispatcher.
     *
     * @param req
     * 		current request
     * @param res
     * 		current response
     * @throws IOException
     * 		error occured.
     */
    public void get(HttpServletRequest req, HttpServletResponse res)
    throws IOException {
        i18n(req, res);
        
        WebContext ctx = new WebContext(req, res,  req.getSession().getServletContext(), res.getLocale());
    	ctx.setVariable("uptime",  getUptime());
    	ctx.setVariable("version", ff4j.getVersion());
    	
    	// Security
    	ctx.setVariable("secure", false);
        if (getFf4j().getAuthorizationsManager() != null) {
    	    ctx.setVariable("secure", true);
    	    ctx.setVariable("userName", getFf4j().getAuthorizationsManager().getCurrentUserName());
    	    Set < String > permissions = getFf4j().getAuthorizationsManager().getCurrentUserPermissions();
    	    ctx.setVariable("userPermissions", permissions);
    	    
    	    // If no role FF4J_CONSOLE_READ => 403
    	    if (!permissions.contains(WebConstants.ROLE_USER)) {
    	        res.setStatus(Status.FORBIDDEN.getStatusCode());
    	        res.getWriter().println("You cannot access FF4J console, insuffisant permissions");
    	        return;
    	    }
        }
    	
    	try {
    	    get(req, res, ctx);
    	} catch(Throwable t) {
    	    ctx.setVariable("msgType", "error");
            ctx.setVariable("msgInfo", t.getMessage());
    	}

    	// Render to view
    	templateEngine.process(getSuccessView(), ctx, res.getWriter());
    }
    
    /**
     * Invoked by dispatcher.
     *
     * @param req
     *      current request
     * @param res
     *      current response
     * @throws IOException
     *      error occured.
     */
    public void post(HttpServletRequest req, HttpServletResponse res)
    throws IOException {
        WebContext ctx = new WebContext(req, res,  req.getSession().getServletContext(), req.getLocale());
        ctx.setVariable("uptime",  getUptime());
        ctx.setVariable("version", ff4j.getVersion());

        // Adding attribute to response
        try {
            post(req, res, ctx);
        } catch(Throwable t) {
            ctx.setVariable("msgType", "error");
            ctx.setVariable("msgInfo", t.getMessage());
        }

        // Render to view
        templateEngine.process(getSuccessView(), ctx, res.getWriter());
    }

	/**
	 * Create view from template.
	 *
	 * @param req
	 * 		current http request
	 * @param res
	 * 		current http response
	 * @throws IOException
	 * 		target error
	 */
	public abstract void get(HttpServletRequest req, HttpServletResponse res, WebContext ctx)
	throws IOException;
	

    /**
     * Create view from template.
     *
     * @param req
     *      current http request
     * @param res
     *      current http response
     * @throws IOException
     *      target error
     */
    public abstract void post(HttpServletRequest req, HttpServletResponse res, WebContext ctx)
    throws Exception;

	/**
	 * Getter accessor for attribute 'ff4j'.
	 *
	 * @return
	 *       current value of 'ff4j'
	 */
	public FF4j getFf4j() {
		return ff4j;
	}

	/**
	 * Setter accessor for attribute 'ff4j'.
	 * @param ff4j
	 * 		new value for 'ff4j '
	 */
	public void setFf4j(FF4j ff4j) {
		this.ff4j = ff4j;
	}

	/**
	 * Getter accessor for attribute 'templateEngine'.
	 *
	 * @return
	 *       current value of 'templateEngine'
	 */
	public TemplateEngine getTemplateEngine() {
		return templateEngine;
	}

	/**
	 * Setter accessor for attribute 'templateEngine'.
	 * @param templateEngine
	 * 		new value for 'templateEngine '
	 */
	public void setTemplateEngine(TemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}

	/**
	 * Getter accessor for attribute 'successView'.
	 *
	 * @return
	 *       current value of 'successView'
	 */
	public String getSuccessView() {
		return successView;
	}

	/**
	 * Setter accessor for attribute 'successView'.
	 * @param successView
	 * 		new value for 'successView '
	 */
	public void setSuccessView(String successView) {
		this.successView = successView;
	}


}
