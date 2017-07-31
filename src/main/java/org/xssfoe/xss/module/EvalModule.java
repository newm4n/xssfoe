package org.xssfoe.xss.module;

import java.util.regex.Pattern;

/**
 * Created by fneman on 6/22/17.
 */
public class EvalModule  extends AbstractXSSStripperModule {
    private Pattern[] pattern = new Pattern[] {
            Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL)
    };

    @Override
    public Pattern[] getPatterns() {
        return pattern;
    }

    @Override
    public String about() {
        return "EVAL fragment";
    }

}