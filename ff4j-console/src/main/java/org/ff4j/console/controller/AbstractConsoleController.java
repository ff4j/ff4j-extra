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

import org.ff4j.FF4j;
import org.ff4j.console.ApplicationConstants;
import org.ff4j.console.conf.ConsoleConfiguration;
import org.ff4j.console.conf.XmlConfigurationParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

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

}
