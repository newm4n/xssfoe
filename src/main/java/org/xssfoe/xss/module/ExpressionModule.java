package org.xssfoe.xss.module;

import java.util.regex.Pattern;

/**
 * Created by fneman on 6/22/17.
 */
public class ExpressionModule extends AbstractXSSStripperModule {
    private Pattern[] pattern = new Pattern[] {
            Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL)
    };

    @Override
    public Pattern[] getPatterns() {
        return pattern;
    }

    @Override
    public String about() {
        return "expression fragment";
    }

}
