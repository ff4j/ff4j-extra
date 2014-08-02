package org.ff4j.console.client;

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

import org.ff4j.console.conf.xml.Connection;
import org.ff4j.console.domain.HomeBean;

public class HttpFF4jConsoleClient {

    private final Connection ff4jConnecion;

    public HttpFF4jConsoleClient(Connection ff4jConnecion) {
        this.ff4jConnecion = ff4jConnecion;
    }

    public HomeBean readFF4j(Connection ff4jConnecion) {
        return null;
    }

    void clearCache(Connection ff4jConnection) {

    }

}
