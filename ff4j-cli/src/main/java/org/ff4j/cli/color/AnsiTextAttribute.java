package org.ff4j.cli.color;

/**
 * Foreground color in ANSI Terminal
 *
 * @author Cedrick Lunven (@clunven)</a>
 * @author Diogo Nunes (inspired by https://github.com/dialex/JCDP/blob/master/src/print/color/Ansi.java)
 */
public enum AnsiTextAttribute {
    
    CLEAR(0),
    
    BOLD(1),
    
    LIGHT(1),
    
    DARK(2),
    
    UNDERLINE(4),
    
    REVERSE(7),
    
    HIDDEN(8);
    
    
    /** Code color for foreGround. */
    private final int code;
    
    /**
     * Default Constructor.
     *
     * @param pcode
     */
    private AnsiTextAttribute(int pcode) {
        this.code = pcode;
    }

    /**
     * Getter accessor for attribute 'code'.
     *
     * @return
     *       current value of 'code'
     */
    public int getCode() {
        return code;
    }
}
