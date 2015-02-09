package org.ff4j.web.console.server;

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

import java.io.File;
import java.io.IOException;
import java.security.ProtectionDomain;

import org.apache.commons.io.FileUtils;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;
import org.ff4j.web.console.Main;
import org.ff4j.web.console.handler.ShutdownHandler;

/**
 * Runtime of an embedded Jetty.
 *
 * @author <a href="mailto:cedrick.lunven@gmail.com">Cedrick LUNVEN</a>
 */
public class JettyEmbeddedServer {

    /** target port. */
    private final int port;
    
    /** Context Path included. */
    private final String contextPath;
    
    /** Target work path. */
    private final String workPath;
    
    /**
     * Unique Constructor.
     * 
     * @param port
     *      listening port for jetty
     * @param ctx
     *      context path for application
     * @param path
     *      working path
     */
    public JettyEmbeddedServer(int port, String ctx, String path) {
        this.port = port;
        this.contextPath = ctx;
        this.workPath = path;
    }

    /**
     * Start Jetty.
     */
    public void start() {
        try {

            // Setup Threadpool
            QueuedThreadPool threadPool = new QueuedThreadPool(512);

            // Setup Jetty Server instance
            Server server = new Server(threadPool);
            server.setStopAtShutdown(true);
            server.setStopTimeout(5000);

            ServerConnector connector = new ServerConnector(server);
            connector.setPort(port);
            connector.setIdleTimeout(30000);
            server.setConnectors(new Connector[] {connector});

            // Get the war-file
            ProtectionDomain protectionDomain = Main.class.getProtectionDomain();
            String warFile = protectionDomain.getCodeSource().getLocation().toExternalForm();
            String currentDir = new File(protectionDomain.getCodeSource().getLocation().getPath()).getParent();

            // Add the warFile (this jar)
            WebAppContext context = new WebAppContext(warFile, contextPath);
            context.setServer(server);
            cleanTempDirectory(context, currentDir);

            // Add the handlers
            HandlerList handlers = new HandlerList();
            handlers.addHandler(context);
            handlers.addHandler(new ShutdownHandler(server, context));
            server.setHandler(handlers);
            server.start();
            server.join();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Clean temp directory.
     *
     * @param context
     *      current application context.
     * @param currentDir
     *      current directory
     * @throws IOException
     *      exception during cleaning directory
     */
    private void cleanTempDirectory(WebAppContext context, String currentDir)
    throws IOException {
        File workDir;
        if (workPath != null) {
            workDir = new File(workPath);
        } else {
            workDir = new File(currentDir, "work");
        }
        FileUtils.deleteDirectory(workDir);
        context.setTempDirectory(workDir);
    }
}
