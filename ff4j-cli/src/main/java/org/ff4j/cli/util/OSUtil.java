package org.ff4j.cli.util;

/**
 * Get current OS
 *
 * @author Cedrick Lunven (@clunven)</a>
 */
public class OSUtil {
    
    /** Current OS Value. */
    private static String OS = System.getProperty("os.name").toLowerCase();
    
    private static OSSupported currentOS = null;
    
    /**
     * Retrieve OS.
     *
     * @return
     */
    public static OSSupported getCurrentOS() {
        if (currentOS ==null) {
            if (OS.contains("win")) {
                currentOS = OSSupported.WINDOWS;
            } else if (OS.contains("mac")) {
                currentOS = OSSupported.OS_X;
            } else if (OS.contains("nix") || OS.contains("nux")|| OS.contains("aix")) {
                currentOS = OSSupported.UNIX;
            } else if (OS.contains("sunos")) {
                currentOS = OSSupported.SOLARIS;
            }
        }
        return currentOS;
    }

}
