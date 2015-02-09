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

import static javax.servlet.http.HttpServletResponse.SC_OK;
import static org.ff4j.web.console.handler.HttpClient.POST;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.http.HttpMethod;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Hanlde shutdown command.
 */
public class ShutdownHandler extends AbstractHandler {

    /** Expected context. */
    public static final String SHUTDOWN = "/shutdown";
    
    /** Current Web application context. */
    private final WebAppContext context;
    
    /** Current serverName. */
    private final Server server;

    /**
     * Constructor.
     * @param server
     *      current jetty server
     * @param context
     *      current web application context
     */
    public ShutdownHandler(Server server, WebAppContext context) {
        this.server = server;
        this.context = context;
    }

    /** {@inheritDoc} */
    public void handle(String target, Request req, HttpServletRequest httpReq, HttpServletResponse response)
    throws IOException, ServletException {
        // Expected request POST on context /shutdown
        if (!SHUTDOWN.equals(target) || ! HttpMethod.POST.name().equals(httpReq.getMethod()))
            return;
        
        // Prepare shuting down
        PrintWriter out = response.getWriter();
        try {
            response.setStatus(SC_OK);
            out.println("Shutting down....");
            try {
                context.stop();
            } catch (Exception e1) {
                out.println("WARN : Cannot closing context : " + e1.getMessage());
            }
            try {
                server.stop();
            } catch (Exception e2) {
                out.println("WARN : Cannot shutting server down: " + e2.getMessage());
            }
            out.println("Done");
        } finally {
            out.close();
        }
        System.exit(0);
    }

    public static String shutdown(int port) {
      return POST("http://127.0.0.1:" + port + "/shutdown").body;
    }
}
