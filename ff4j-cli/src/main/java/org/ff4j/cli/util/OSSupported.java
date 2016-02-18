package org.ff4j.cli.util;

/**
 * Operating System.
 *
 * @author Cedrick Lunven (@clunven)</a>
 */
public enum OSSupported {

    /** Windows. */
    WINDOWS("win"),
    
    /** Mac. */
    OS_X("mac"),
    
    /** Unix. */
    UNIX("unix"),
    
    /** Unix. */
    SOLARIS("solaris");
    
    /** Code color for foreGround. */
    private final String name;
    
    /**
     * Default Constructor.
     *
     * @param pname
     *      current name
     */
    private OSSupported(String pname) {
        this.name = pname;
    }

    /**
     * Getter accessor for attribute 'name'.
     *
     * @return
     *       current value of 'name'
     */
    public String getName() {
        return name;
    }
    
}
