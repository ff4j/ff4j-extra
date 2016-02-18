package org.ff4j.cli;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.fusesource.jansi.AnsiConsole;

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
 * @author Cedrick Lunven (@clunven)</a>
 */
public class MainCli {

    public static void main(String[] args) throws java.io.IOException {

        AnsiConsole.systemInstall();

        String commandLine;
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Welcome to ff4j");
        while (true) {
            // read what the user entered
            System.out.print("ff4j>");            
            commandLine = console.readLine();
            
            // if the user entered a return, just loop again
            if (commandLine.equals("list")) {
                System.out.println("- DEV\n -INT");;
            } else if (commandLine.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye");
                System.exit(0);
            } else if (commandLine.equalsIgnoreCase("quit")) {
                System.out.println("Goodbye");
                System.exit(0);
            }
        }
    }
}
