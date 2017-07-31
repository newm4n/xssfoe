package org.xssfoe.xss.module;

import java.util.regex.Pattern;

/**
 * Created by fneman on 6/22/17.
 */
public class ElementModule  extends AbstractXSSStripperModule {
    private Pattern[] pattern = new Pattern[] {
            Pattern.compile("</?(body|head|title|br|select|option|b|strong|em|span|div|input|button|form|iframe|frame|p|a|i|script|table|tr|td|th|video|canvas|link|style|meta)\\s*(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            Pattern.compile("(<|¼)/?[a-zA-Z0-9]+\\s*(.*?)(>|¾)?", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL)
    };

    @Override
    public Pattern[] getPatterns() {
        return pattern;
    }

    @Override
    public String about() {
        return "Element fragment";
    }

}
