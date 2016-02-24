package org.ff4j.cli.command;

/*
 * #%L
 * ff4j-cli
 * %%
 * Copyright (C) 2013 - 2016 FF4J
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


import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class FF4jCommand {
    
    private FF4jCommand() {
    }
    
    public static Options noEnvOptions() {
     
        Options options = new Options();
        
        // Help
        options.addOption(Option.builder("?").longOpt("help")
                .desc("Display this message")              
                .build());
       
        // Config file
        options.addOption(Option.builder("env").longOpt("configfile")
                .hasArg().argName("configfile")
                .desc("XML Configuration file for ff4j").build());
        
        // Version
        options.addOption(
                new Option( "version", "display current version" ));
        
        // List
        options.addOption(
                new Option("list", "list current environment" ));
        
        // Connect
        options.addOption(Option.builder("conn").longOpt("connect")
                .hasArg().argName("envName")
                .desc("Environment ID").build());
        
        return options;
    }
    
  

}
