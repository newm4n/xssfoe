package org.xssfoe.xss.module;

/**
 * <p>Define the specification of stripper module class.</p>
 * <p>Implementation of this interface should detect and strip (if required) the supplied String from XSS threat</p>
 *
 * Created by fneman on 6/22/17.
 */
public interface XSSStripperModule {
    /**
     * Detect if the supplied parameter contains XSS threat OR to indicate that this implementation are capable
     * to strip/remove the detected XSS threat.
     *
     * @param string The string the will be scanned.
     * @return
     */
    public boolean willStrip(String string);

    /**
     * Strip the provided string parameter from a XSS threat and return the safe form of it, ready to use string.
     * The contract is, the returned string should be safe enough to use and the XSS thread is disabled. The returned string
     * are not required to be perfect and clean to human perception. Malicious user may fill in untrusted and unsafe information to the system
     * and the system must be able to disable harmful information by rendering it into garbage or possibly removing it entirely.
     *
     * @param string String parameter to be strip
     * @return A safe to use string after xss threat is removed.
     */
    public String strip(String string);

    /**
     * Small description about the XSS module of the implementation class.
     * @return Module short description.
     */
    public String about();
}
