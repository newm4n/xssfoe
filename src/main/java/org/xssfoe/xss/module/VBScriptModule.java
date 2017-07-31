package org.xssfoe.xss.module;

import java.util.regex.Pattern;

/**
 * Created by fneman on 6/22/17.
 */
public class VBScriptModule extends AbstractXSSStripperModule {
    private Pattern[] pattern = new Pattern[] {
            Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE)
    };

    @Override
    public Pattern[] getPatterns() {
        return pattern;
    }

    @Override
    public String about() {
        return "VBScript fragment";
    }

}