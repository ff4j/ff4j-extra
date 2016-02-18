package org.ff4j.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.ff4j.cli.color.AnsiForegroundColor;
import org.ff4j.cli.color.AnsiText;
import org.junit.Test;

public class ParserTest {
     
    @Test
    public void testParse() throws ParseException {
        
        Options options = new Options();
        
        Option help  = new Option( "help", "print this message" );
        options.addOption(help);
        
        Option help2 = new Option( "?", "print this message" );
        options.addOption(help2);
        
        /** Configuration File. */
        Option confFileOption = Option.builder("conf").longOpt("configfile")
                            .hasArg()
                            .argName("configfile")
                            .desc("XML Configuration file for ff4j")              
                            .build();
        options.addOption(confFileOption);
        
        //CONNECT
        //list
        
        
        
        CommandLineParser parser = new DefaultParser();        
        CommandLine cmd = parser.parse( options, new String[] { "ff4j", "--configfile", "C:/sample.xml" } );
        
        // automatically generate the help statement
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp( "ff4j", options );
        
        if( cmd.hasOption( "configfile" ) ) {
            new AnsiText(cmd.getOptionValue("configfile"), AnsiForegroundColor.RED).display();
            //System.out.println(cmd.getOptionValue("configfile"));
        }
        
    }

}
