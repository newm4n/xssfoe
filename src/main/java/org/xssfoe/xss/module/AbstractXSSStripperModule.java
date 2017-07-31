package org.xssfoe.xss.module;

import java.util.regex.Pattern;

/**
 * Abstract generic class implementation of a stripper module.
 *
 * Created by fneman on 6/22/17.
 */
public abstract class AbstractXSSStripperModule implements XSSStripperModule {

    /**
     * Get the regex pattern object used for detection of this module.
     * @return Array of regex pattern.
     */
    public abstract Pattern[] getPatterns();

    @Override
    public boolean willStrip(String string) {
        for(Pattern pattern : getPatterns()) {
            if(pattern.matcher(string).find()) return true;
        }
        return false;
    }

    @Override
    public String strip(String string) {
        String str = string;
        for(Pattern pattern : getPatterns()) {
            str = pattern.matcher(str).replaceAll("");
        }
        return str;
    }

}
