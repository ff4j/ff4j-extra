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
import org.ff4j.console.client.ConsoleHttpClient;
import org.ff4j.console.conf.xml.Connection;
import org.ff4j.console.domain.EnvironmenBean;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * HomeController which load supervision services and display home page.
 */
@Controller
@RequestMapping("/" + ApplicationConstants.VIEW_HOME + ".do")
public class HomeController extends AbstractConsoleController {

    /**
     * Allows to display screen.
     * 
     * @param request
     *          http request
     * @param response
     *          http response
     * @return
     *          model and view
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("Page <HOME>");
        // Check First Access, still : no environment selected
        if (null == request.getSession().getAttribute(ATTR_ENVBEAN)) {
            return new ModelAndView("redirect:loadConfig.do");
        }

        // Using environnment to create homebean
        return buildHomePage(request, (EnvironmenBean) request.getSession().getAttribute(ATTR_ENVBEAN));
    }

    /**
     * Select target environnement to be connected
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView selectEnv(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String targetEnvId = request.getParameter(FORM_PARAM_ENVIRONMENT);

        log.info("Page <HOME> - Select environment " + targetEnvId);
        if (StringUtils.hasText(targetEnvId)) {
            if (conf.getMapOfConnections().containsKey(targetEnvId)) {
                ArrayList<Connection> conns = new ArrayList<Connection>(conf.getMapOfConnections().values());
                EnvironmenBean envBean = new EnvironmenBean(conf.getMapOfConnections().get(targetEnvId), conns);
                request.getSession().setAttribute(ATTR_ENVBEAN, envBean);
            }
        }

        // using environnment to create homebean
        return buildHomePage(request, (EnvironmenBean) request.getSession().getAttribute(ATTR_ENVBEAN));
    }

    /**
     * Build target page and model from environment.
     * 
     * @param envBean
     *            environment bean
     * @return
     * 
     */
    private ModelAndView buildHomePage(HttpServletRequest request, EnvironmenBean envBean) {
        // Output page is HOME
        ModelAndView mav = new ModelAndView(VIEW_HOME);
        log.info("Working with environnement " + envBean.getEnvId());
        mav.addObject(ATTR_ENVBEAN, envBean);
        ConsoleHttpClient client = new ConsoleHttpClient(getConnection(envBean.getEnvId()));
        mav.addObject(ATTR_HOMEBEAN, client.getHome());
        return mav;
    }

}
