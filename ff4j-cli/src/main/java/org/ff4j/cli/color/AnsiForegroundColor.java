package org.ff4j.cli.color;

/**
 * Foreground color in ANSI Terminal
 *
 * @author Cedrick Lunven (@clunven)</a>
 * @author Diogo Nunes (inspired by https://github.com/dialex/JCDP/blob/master/src/print/color/Ansi.java)
 */
public enum AnsiForegroundColor {

    BLACK   (30),
    
    RED     (31),
    
    GREEN   (32),
    
    YELLOW  (33),
    
    BLUE    (34),
    
    MAGENTA (35),
    
    CYAN    (36),
    
    WHITE   (37);
    
    /** Code color for foreGround. */
    private final int code;
    
    /**
     * Default Constructor.
     *
     * @param pcode
     */
    private AnsiForegroundColor(int pcode) {
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
