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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ff4j.FF4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * HomeController which load supervision services and display home page.
 */
@Controller
@RequestMapping("/home")
public class HomeController implements ApplicationConstants {

    /** logger for the class. */
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private FF4j ff4j;

    /**
     * Display screen
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("Access Home Controller");

        // HOME
        Model model = new ExtendedModelMap();
        model.addAttribute(PAGE_TITLE, "Supervision");
        model.addAttribute(PAGE_SUBTITLE, "DashBoard");

        return new ModelAndView("home", "model", model);
    }

}
