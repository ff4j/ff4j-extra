package org.ff4j.cli.command;

public enum FF4jCommand {
    
    CONF("conf"),
    
    LS("ls"),
    
    LIST("list"),
    
   
    CONNECT("connect"),

    VERSION("version");
    
    String keyword = "";
    
    private FF4jCommand(String keyword) {
        this.keyword = keyword;
    }
    
    public String getKeyword() { 
        return keyword; 
    }

}
