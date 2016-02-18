package org.ff4j.cli.color;

import org.ff4j.cli.util.OSSupported;
import org.ff4j.cli.util.OSUtil;
import org.fusesource.jansi.AnsiConsole;

/**
 * Diplay color in terminal.
 *
 * @author Cedrick Lunven (@clunven)</a>
 */
public class AnsiText {
    
    /** Every Ansi escape code begins with this PREFIX. */
    private static String PREFIX = "\033[";
    
    /** Every attribute is separated by this SEPARATOR. */
    private static String SEPARATOR = ";";
    
    /** Every Ansi escape code end with this POSTFIX. */
    private static String POSTFIX = "m";
    
    /** raw text to be displayed. */
    private String rawText;
    
    /** Color in foreground. */
    private AnsiForegroundColor color = null;
    
    /** Color in background. */
    private AnsiBackGroundColor backgroundColor = null;
    
    /** Attributes */
    private AnsiTextAttribute attribute = null;
    
    /**
     * Initialize with raw text.
     *
     * @param prawtext
     *      target raw text
     */
    public AnsiText(String prawtext) {
        this.rawText = prawtext;
    }
    
    /**
     * Init with a color.
     * @param prawtext
     * @param pcolor
     */
    public AnsiText(String prawtext, AnsiForegroundColor pcolor) {
        this(prawtext);
        this.color = pcolor;
    }
    
    /**
     * Init with a color.
     * @param prawtext
     * @param pcolor
     */
    public AnsiText(String prawtext, AnsiForegroundColor pcolor, AnsiBackGroundColor bcolor) {
        this(prawtext, pcolor);
        this.backgroundColor = bcolor;
    }
    
    /**
     * Init with a color.
     * @param prawtext
     * @param pcolor
     */
    public AnsiText(String prawtext, AnsiForegroundColor pcolor, AnsiBackGroundColor bcolor, AnsiTextAttribute txt) {
        this(prawtext, pcolor, bcolor);
        this.attribute = txt;
    }
    
    /** Allow to render. */
    public String renderCode() {
        StringBuilder sb = new StringBuilder(PREFIX);
        if (attribute != null) {
            sb.append(attribute.getCode());
        }
        sb.append(SEPARATOR);
        if (color != null) {
            sb.append(color.getCode());
        }
        sb.append(SEPARATOR);
        if (backgroundColor != null) {
            sb.append(backgroundColor.getCode());
        }
        sb.append(POSTFIX);
        return sb.toString();
    }
    
    /** Get OS. */
    public void display() {
        OSSupported os = OSUtil.getCurrentOS();
        if (os == null) {
            System.out.println(renderCode() + rawText);
        } else {
            switch (os) {
                case WINDOWS:
                    AnsiConsole.out.println(renderCode() + rawText);
                break;
                case UNIX:
                case OS_X:
                case SOLARIS:
                    System.out.println(renderCode() + rawText);
                break;
            }
        }
    }
    
    
}
