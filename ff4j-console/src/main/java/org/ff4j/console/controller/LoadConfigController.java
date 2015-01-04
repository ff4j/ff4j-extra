package org.ff4j.console.controller;

/*
 * #%L
 * ff4j-console
 * %%
 * Copyright (C) 2013 - 2015 Ff4J
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

import org.ff4j.console.conf.xml.Connection;
import org.ff4j.console.domain.EnvironmenBean;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Load configuration from file and ask the user to pick one.
 *
 * @author <a href="mailto:cedrick.lunven@gmail.com">Cedrick LUNVEN</a>
 */
@Controller
@RequestMapping("/loadConfig.do")
public class LoadConfigController extends AbstractConsoleController {
    
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
        
        ArrayList<Connection> conns = new ArrayList<Connection>(conf.getMapOfConnections().values());
        
        // Single Environnement, no pb redirect to home and pick the correct one
        if (1 == conf.getMapOfConnections().size()) {
            log.info("Selecting <" + conns.get(0).getId() + "> as default and single environement");
            request.getSession().setAttribute(ATTR_ENVBEAN, new EnvironmenBean(conns.get(0), conns));
            return new ModelAndView("redirect:home.do");
        }
       
        // Several Environnments or none, will display loadconf file
        ModelAndView mav = new ModelAndView("loadconfig");
        EnvironmenBean envBean = new EnvironmenBean(conns);
        envBean.setConfFilePresent(conf.isConfFilePresent());
        envBean.setConfFileParsed(conf.isConfFileParsed());
        mav.addObject(ATTR_ENVBEAN, envBean);
        if (conf.getMapOfConnections().size() > 1) {
            log.info("No environnment selected yet, ask current user to choose if possible");
        } else {
            log.info("No environnement defined at all");
        }
        return mav;
    }
    
    
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
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView pickTargetEnv(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String targetEnvId = request.getParameter(FORM_PARAM_ENVIRONMENT);
        log.info("Page <LOADCONFIG> - Select environment " + targetEnvId);
        if (StringUtils.hasText(targetEnvId)) {
            if (conf.getMapOfConnections().containsKey(targetEnvId)) {
                ArrayList<Connection> conns = new ArrayList<Connection>(conf.getMapOfConnections().values());
                EnvironmenBean envBean = new EnvironmenBean(conf.getMapOfConnections().get(targetEnvId), conns);
                request.getSession().setAttribute(ATTR_ENVBEAN, envBean);
            }
        }
        return new ModelAndView("redirect:home.do");
    }
    
}
