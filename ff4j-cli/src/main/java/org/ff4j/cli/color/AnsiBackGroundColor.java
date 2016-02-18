package org.ff4j.cli.color;

/**
 * Foreground color in ANSI Terminal
 *
 * @author Cedrick Lunven (@clunven)</a>
 * @author Diogo Nunes (inspired by https://github.com/dialex/JCDP/blob/master/src/print/color/Ansi.java)
 */
public enum AnsiBackGroundColor {
    
    BLACK   (40),
    
    RED     (41),
    
    GREEN   (42),
    
    YELLOW  (43),
    
    BLUE    (44),
    
    MAGENTA (45),
    
    CYAN    (46),
    
    WHITE   (47);
    
    /** Code color for foreGround. */
    private final int code;
    
    /**
     * Default Constructor.
     *
     * @param pcode
     */
    private AnsiBackGroundColor(int pcode) {
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
