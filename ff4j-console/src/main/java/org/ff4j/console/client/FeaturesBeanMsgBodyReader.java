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

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.map.ObjectMapper;
import org.ff4j.console.domain.FeaturesBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class FeaturesBeanMsgBodyReader implements MessageBodyReader<FeaturesBean> {

    /** logger for the class. */
    protected Logger log = LoggerFactory.getLogger(getClass());

    private static final String PERMISSIONS_TAG = "permissions";

    /** {@inheritDoc} */
    @Override
    public boolean isReadable(Class<?> arg0, Type type, Annotation[] arg2, MediaType arg3) {
        return (type == FeaturesBean.class);
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public FeaturesBean readFrom(Class<FeaturesBean> type, Type genericType, Annotation[] annotations, MediaType mediaType,
            MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> myMap = new HashMap<String, Object>();
        myMap = objectMapper.readValue(entityStream, HashMap.class);
        FeaturesBean fb = new FeaturesBean();
        fb.setPermissionList(new HashSet<String>());
        if (myMap.containsKey(PERMISSIONS_TAG)) {
            ArrayList<String> permissions = (ArrayList<String>) myMap.get(PERMISSIONS_TAG);
            fb.setPermissionList(new HashSet<String>(permissions));
        }
        log.debug("Permissions (" + fb.getPermissionList().size() + ") : " + fb.getPermissionList());
        return fb;
    }

}
