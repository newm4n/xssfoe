package org.xssfoe.xss;

import org.owasp.esapi.ESAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xssfoe.xss.module.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The stripper class. This class will use the provided stripper modules to strip untrusted input.
 *
 * Created by fneman on 6/22/17.
 */
public class XSSStripper extends ArrayList<XSSStripperModule> {

    private static final Logger log = LoggerFactory.getLogger(XSSStripper.class);

    /**
     * Detect and decide whether the supplied string parameter will be stripped or not.
     * @param value The supplied tobe detected.
     * @return <code>true</code> if the specified value contains possible XSS attack vector, <code>false</code> if the specified value is considered safe.
     */
    public synchronized boolean detectXSS(String value) {
        if (value != null) {
            long time = System.currentTimeMillis();
            try {
                // avoid encoded attacks.
                value = ESAPI.encoder().canonicalize(value);

                // Avoid unwanted characters
                value = value.replaceAll("[\uFEFF-\uFFFF]", "");

                // Remove all sections that match a pattern
                for (XSSStripperModule module : this) {
                    if (module.willStrip(value)) {
                        log.warn(module.about() + " detected XSS.");
                        return true;
                    }
                }
            }
            finally {
                long span = System.currentTimeMillis()-time;
                if(span > 50) {
                    log.debug("XSS detect takes " + (System.currentTimeMillis() - time) + " ms.");
                }
            }
        }
        return false;
    }

    /**
     * Immediatelly strip the specified value string from any known XSS attack vector.
     *
     * @param value String value to check and strip
     * @return The stripped/safe string.
     */
    public synchronized String stripXSS(String value) {
        if (value != null) {
            long time = System.currentTimeMillis();
            try {
                // avoid encoded attacks.
                value = ESAPI.encoder().canonicalize(value);

                // Avoid unwanted characters
                value = value.replaceAll("[\uFEFF-\uFFFF]", "");

                // Remove all sections that match a pattern
                for (XSSStripperModule module : this) {
                    if (module.willStrip(value)) {
                        log.warn(module.about() + " detected XSS. Stripping...");
                        value = module.strip(value);
                    }
                }

                if (XSSFilter.HTML_SAVE_ENCODING) {
                    value = ESAPI.encoder().encodeForHTML(value);
                }
            }
            finally {
                long span = System.currentTimeMillis()-time;
                if(span > 50) {
                    log.debug("XSS strip takes " + (System.currentTimeMillis() - time) + " ms.");
                }
            }
        }
        return value;
    }
}
