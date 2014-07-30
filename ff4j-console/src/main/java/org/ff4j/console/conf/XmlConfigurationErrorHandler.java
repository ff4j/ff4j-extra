package org.ff4j.console.conf;

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


import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Error handler for parsing XML.
 *
 * @author <a href="mailto:cedrick.lunven@gmail.com">Cedrick LUNVEN</a>
 */
public class XmlConfigurationErrorHandler implements ValidationEventHandler {

    /** Logger for the class. */
    protected Logger log = LoggerFactory.getLogger(getClass());

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean handleEvent(ValidationEvent event) {
        if (event.getSeverity() >= ValidationEvent.ERROR) {
            log.error("Error at line " + event.getLocator().getLineNumber() + ", column "
                    + event.getLocator().getColumnNumber() + " in file " + event.getLocator().getURL(),
                    event.getLinkedException());
            return false;
        }
        return true;
    }
}
