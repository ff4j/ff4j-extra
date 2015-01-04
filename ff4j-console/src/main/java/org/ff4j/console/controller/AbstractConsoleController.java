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

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import org.ff4j.FF4j;
import org.ff4j.console.ApplicationConstants;
import org.ff4j.console.conf.ConsoleConfiguration;
import org.ff4j.console.conf.XmlConfigurationParser;
import org.ff4j.console.conf.xml.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Injection of ff4j for all controller.
 * 
 * @author <a href="mailto:cedrick.lunven@gmail.com">Cedrick LUNVEN</a>
 */
public abstract class AbstractConsoleController implements ApplicationConstants, InitializingBean {

    /** logger for the class. */
    protected Logger log = LoggerFactory.getLogger(getClass());

    /** local ff4j. */
    @Autowired
    protected FF4j ff4j;

    /** Xml Configuration */
    @Autowired
    protected XmlConfigurationParser xmlParser;

    /** Console Configuration. */
    protected ConsoleConfiguration conf;
    
    /** {@inheritDoc} */
    @Override
    public void afterPropertiesSet() throws Exception {
        conf = xmlParser.getConf();
    }

    /**
     * Select connection from map.
     * 
     * @param connId
     *            target connection identifier.
     * @return target connection
     */
    protected Connection getConnection(String connId) {
        if (!conf.getMapOfConnections().containsKey(connId)) {
            throw new IllegalArgumentException("Id " + connId + " is invalid :" + conf.getMapOfConnections().keySet());
        }
        return conf.getMapOfConnections().get(connId);
    }

   
    /**
     * Process error
     * @param e
     *      target error
     * @return
     *      target pb.
     */
    @ExceptionHandler (Exception.class)
    public ModelAndView handleException(final Exception e, HttpServletRequest request) {
        // Clear Session
        request.getSession().removeAttribute(ATTR_ENVBEAN);
        
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("errorMsg", e.getMessage());
        mav.addObject("errorStack", sw.toString().replaceAll("\\n", "<br>"));
        return mav;
    }

}
