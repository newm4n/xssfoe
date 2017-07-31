package org.xssfoe.xss.module;

import java.util.regex.Pattern;

/**
 * Created by fneman on 6/22/17.
 */
public class ScriptModule extends AbstractXSSStripperModule {

    private Pattern[] pattern = new Pattern[] {
            Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE),
            Pattern.compile("<script", Pattern.CASE_INSENSITIVE),
            Pattern.compile("</script>", Pattern.CASE_INSENSITIVE)
    };

    @Override
    public Pattern[] getPatterns() {
        return pattern;
    }

    @Override
    public String about() {
        return "SCRIPT fragment";
    }
}
