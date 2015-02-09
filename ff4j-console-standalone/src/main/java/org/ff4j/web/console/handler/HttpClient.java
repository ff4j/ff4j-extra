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

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.URL;

import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;

/**
 * This is just for convenience. It's not safe.
 */
public class HttpClient {

  public static HttpResponse GET(String uri) {
    return http("GET", uri);
  }

  public static HttpResponse POST(String uri) {
    return http("POST", uri);
  }

  static HttpResponse http(String method, String uri) {
    try {
      URL url = new URL(uri);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod(method);
      Object content = conn.getContent();
        if(content instanceof InputStream)
            return new HttpResponse(conn.getResponseCode(), IOUtils.toString((InputStream) content, "UTF-8"));
        else if(content instanceof String)
            return new HttpResponse(conn.getResponseCode(), (String) content);
        else
            return new HttpResponse(conn.getResponseCode(), "unknown");
        
    } catch (SocketException e) {
      return new HttpResponse(SC_NOT_FOUND);
    } catch (IOException e) {
      return new HttpResponse(SC_INTERNAL_SERVER_ERROR);
    }
  }

}