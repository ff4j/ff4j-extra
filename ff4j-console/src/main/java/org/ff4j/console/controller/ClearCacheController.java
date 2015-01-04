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

import org.ff4j.console.client.ConsoleHttpClient;
import org.ff4j.console.domain.EnvironmenBean;
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
        ConsoleHttpClient client = new ConsoleHttpClient(getConnection(envBean.getEnvId()));
        client.clearCache();
       
        return "redirect:home.do";
    }

}
