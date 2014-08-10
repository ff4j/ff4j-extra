package org.ff4j.console.test;

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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.ff4j.console.domain.HomeBean;
import org.junit.Test;

public class ParserJsonTest {

    @SuppressWarnings("unchecked")
    @Test
    public void testJsonParser() throws JsonParseException, JsonMappingException, IOException {

        String data = "{\"uptime\":\"0 day(s) 1 hours(s) 1 minute(s) 23 seconds\", \"autocreate\":false, \"version\": \"1.2.1-SNAPSHOT\", \"featuresStore\":{\"type\":\"org.ff4j.store.InMemoryFeatureStore\",\"xmlInputFile\":\"null\",\"cached\":false,\"numberOfFeatures\":9,\"features\":[\"sayHello\",\"sayGoodBye\",\"feat1\",\"feat2\",\"feat6\",\"feat7\",\"feat3\",\"feat4\",\"feat5\"],\"numberOfGroups\":2,\"groups\":[\"strategies\",\"Groupe1\"]}, \"eventRepository\":{\"type\":\"org.ff4j.audit.InMemoryEventRepository\",\"numberOfEvents\":0}, \"authorizationsManager\":null}";

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> myMap = new HashMap<String, Object>();
        myMap = objectMapper.readValue(data, HashMap.class);
        System.out.println("Map is: " + myMap);

        HomeBean hb = new HomeBean();

        // uptime
        hb.setUptime((String) myMap.get("uptime"));
        
        // Version
        hb.setVersion((String) myMap.get("version"));
        
        // Monitoring
        if (myMap.containsKey("eventRepository")) {
            Map<String, Object> evtMap = (Map<String, Object>) myMap.get("eventRepository");
            if (evtMap != null) {
                String classType = (String) evtMap.get("type");
                hb.setMonitoring(classType.substring(classType.lastIndexOf(".") + 1));
            }
        } else {
            hb.setMonitoring("no monitoring");
        }

        // Security
        if (myMap.containsKey("authorizationsManager")) {
            Map<String, Object> secMap = (Map<String, Object>) myMap.get("authorizationsManager");
            if (secMap != null) {
                String classType = (String) secMap.get("type");
                hb.setSecurity(classType.substring(classType.lastIndexOf(".") + 1));
            }
        } else {
            hb.setSecurity("no permissions");
        }

        // Store
        if (myMap.containsKey("featuresStore")) {
            Map<String, Object> featMap = (Map<String, Object>) myMap.get("featuresStore");
            if (featMap != null) {
                String classType = (String) featMap.get("type");
                hb.setStore(classType.substring(classType.lastIndexOf(".") + 1));
            }
        }

        // Caching
        if (myMap.containsKey("cached")) {
            boolean cached = (Boolean) myMap.get("cached");
            if (cached) {
                String classType = (String) myMap.get("cacheStore");
                String cacheProvider = (String) myMap.get("cacheProvider");
                hb.setCaching(classType.substring(classType.lastIndexOf(".") + 1) + "(" + cacheProvider + " )");
            }
        } else {
            hb.setCaching("no cache");
        }

    }

}
