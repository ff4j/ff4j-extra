package org.ff4j.web.console.handler;

/*
 * #%L
 * Wrap FF4J application
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

/**
 * Response from HTTP requests.
 *
 * @author <a href="mailto:cedrick.lunven@gmail.com">Cedrick LUNVEN</a>
 */
public class HttpResponse {
    
    /** error code. */
    public final int code;
    
    /** response body. */
    public final String body;

    /**
     * Parameterized constructor.
     * @param code
     *      target code
     * @param body
     *      target body
     */
    public HttpResponse(int code, String body) {
      this.code = code;
      this.body = body;
    }

    /**
     * Parameterized constructor.
     * @param code
     *      target code
     */
    public HttpResponse(int code) {
      this(code, "");
    }
    
  }