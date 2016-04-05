package org.ff4j.cli;

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


import static org.ff4j.cli.ansi.AnsiTerminal.foreGroundColor;
import static org.ff4j.cli.ansi.AnsiTerminal.logInfo;
import static org.ff4j.cli.ansi.AnsiTerminal.logWarn;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.ff4j.FF4j;
import org.ff4j.cli.ansi.AnsiForegroundColor;
import org.ff4j.cli.ansi.AnsiTerminal;
import org.ff4j.cli.command.FF4jCommand;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Command line to operate FF4J.
 * 
 * ff4j>
 * 
 * uptime info version ? / help clear cache
 * 
 * list features enable "jkjkj" disable "jjjj" grant role on "featureid" grant role on "featureid
 * 
 * addFixedValue on propertyName value setName on ddddd setType on xxx
 * 
 * create-feature featureName create-property name type <value>
 * 
 *
 * connect $envName
 * li
 * 
 *
 * @author Cedrick Lunven (@clunven)</a>
 */
public class MainCli {
    
    /** Commons-cli help. */
    private static HelpFormatter HELP_FORMAT = new HelpFormatter();
    
    /** Commons-cli parsing. */
    private static CommandLineParser CMD_PARSER = new DefaultParser();

    /**
     * Main method for ff4j-cli.
     *
     * @param args
     *      all parameters related to ff4j commands
     * @throws Exception
     *      error during manipulation
     */
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            
            foreGroundColor(AnsiForegroundColor.GREEN);
            System.out.println("  __  __ _  _   _ ");
            System.out.println(" / _|/ _| || | (_)");
            System.out.println("| |_| |_| || |_| |");
            System.out.println("|  _|  _|__   _| |");
            System.out.println("|_| |_|    |_|_/ |");
            System.out.print("             |__/   ");
            System.out.println("\n");
            logInfo("No parameter provided, enable interactive mode");
            logInfo("You are not connected, enter '?' or 'help' to get help");
            System.out.println("");
            
            ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("ff4j-cli-config.xml");
            Map < String, FF4j> envs = ctx.getBeansOfType(FF4j.class);
            if (envs != null) {
                for(String envName : envs.keySet()) {
                    System.out.println(envName + " = " + envs.get(envName));
                }
            }
            ctx.close();
            
            // START INTERACTIVE MODE
            String currentEnv = null;
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                displayPrompt(currentEnv);
                String commandLine = console.readLine();
                if (commandLine.startsWith("help") || commandLine.startsWith("?")) {
                    
                    System.out.println("");
                    HELP_FORMAT.printHelp(commandLine , FF4jCommand.noEnvOptions() );
                    
                } else {
                
                    String[] line = commandLine.split(" ");
                    
                            
                    CommandLine cmd = CMD_PARSER.parse(FF4jCommand.noEnvOptions(), line);
                    System.out.println(cmd.hasOption('c'));
                    
                    
                    // if the user entered a return, just loop again
                    if (line[0].equalsIgnoreCase("conn")) {
                        logInfo("Connecting to " + line[1]);
                    } else if (line[0].equalsIgnoreCase("exit")) {
                        exit(0);
                    } else if (line[0].equalsIgnoreCase("quit")) {
                        exit(0);
                    }
                }
            }
        } else {
            for (String string : args) {
               logWarn(string);
            }
        }
    }
    
    private static void displayPrompt(String currentEnv) {
        // read what the user entered
        AnsiTerminal.foreGroundColor(AnsiForegroundColor.GREEN);
        System.out.print("ff4j");
        if (null != currentEnv) {
            AnsiTerminal.foreGroundColor(AnsiForegroundColor.YELLOW);
            System.out.print(currentEnv); 
            AnsiTerminal.foreGroundColor(AnsiForegroundColor.WHITE);
        }
        System.out.print(">");
        System.out.flush();
        AnsiTerminal.foreGroundColor(AnsiForegroundColor.WHITE);
    }
    
    private static void exit(int code) {
        logInfo("Good Bye");
        AnsiTerminal.foreGroundColor(AnsiForegroundColor.WHITE);
        System.exit(code);
    }
    
    
}
