package org.ff4j.web.console;

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
import java.io.FileInputStream;

import org.ff4j.web.console.handler.ShutdownHandler;
import org.ff4j.web.console.server.JettyEmbeddedServer;

/**
 * Wrap WAR to a dedicated standalone Jetty package
 * 
 * @author <a href="mailto:cedrick.lunven@gmail.com">Cedrick LUNVEN</a>
 *
 *         Based on bekkopen work
 */
public class Main {
    
    /** Command Start. */
    private static final String COMMAND_START = "start";
    
    /** Command Stop. */
    private static final String COMMAND_STOP = "stop";
   
    /**
     * Main Application.
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        try {
            
            // Default
            int port = 8080;
            String contextPath = "/ff4j-console";
            String workPath =  null;
            
            // Load parameters
            String configFile = System.getProperty("config", "env.properties");
            if (new File(configFile).exists()) {
                System.getProperties().load(new FileInputStream(configFile));
                port = Integer.parseInt(System.getProperty("jetty.port", "8080"));
                contextPath = System.getProperty("jetty.context", "/");
            }
            
            System.getProperty("jetty.workDir", null);
            
            // Create server
            JettyEmbeddedServer jetty = new JettyEmbeddedServer(port, contextPath, workPath);
            
            // Actions from command line
            if ((args.length != 1) || COMMAND_START.equals(args[0])) {
                jetty.start();
            } else if (COMMAND_STOP.equals(args[0])) {
                ShutdownHandler.shutdown(port);
            } else {
                System.out.println("Usage: java -jar <file.jar> [start|stop]");
                System.out.println("start \tStart the server (default)"); 
                System.out.println("stop \tStop the server gracefully");
                System.exit(-1);
            }
        } catch (Exception e) {
            System.out.println("Unexpected error occured (-2): " + e.getMessage());
            System.exit(-2);
        }
        
    }   

}
