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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ff4j.FF4j;
import org.ff4j.cache.FeatureStoreCacheProxy;
import org.ff4j.console.client.ConsoleHttpClient;
import org.ff4j.console.conf.xml.Connection;
import org.ff4j.console.domain.EnvironmenBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/clearCache" + ".do")
public class ClearCacheController  extends AbstractConsoleController {

    /**
     * Select target environnement to be connected
     */
    @RequestMapping(method = RequestMethod.GET)
    public String changeLang(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "redirect:home.do";
    }
    
    /**
     * Select target environnement to be connected
     */
    @RequestMapping(method = RequestMethod.POST)
    public String clearCache(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Environment de travail
        EnvironmenBean envBean = (EnvironmenBean) request.getSession().getAttribute(ATTR_ENVBEAN);
        
        // Access features through HTTP store (all parsing done)
       
        clearCache(envBean.getEnvId());
        return "redirect:home.do";
    }
    
    private void clearCache(String envId) {
        Connection xmlConfconn = getConnection(envId);
        if ("bean".equalsIgnoreCase(xmlConfconn.getMode())) {
            ClassPathXmlApplicationContext cap = new ClassPathXmlApplicationContext(xmlConfconn.getUrl());
            FF4j ff4j = cap.getBean(FF4j.class);
            ((FeatureStoreCacheProxy)ff4j.getFeatureStore()).getCacheManager().clear();
            cap.close();
        } else if ("http".equalsIgnoreCase(xmlConfconn.getMode())) {
            new ConsoleHttpClient(getConnection(envId)).clearCache();
        }
        
        throw new IllegalArgumentException("Cannot Handle mode " + xmlConfconn.getMode() + " in configuration file");
    }

}
