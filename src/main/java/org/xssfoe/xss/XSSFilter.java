package org.xssfoe.xss;

import org.owasp.esapi.ESAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xssfoe.xss.module.*;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * <p>This filter will try to render XSS attack vectors supplied by malicious user in a HTTP request into a harmless string</p>
 * <p>The servlet filter needed to be use in web.xml or in the spring security configuration class.</p>
 * <p>As crackers gets more creative and browser getting more advanced, its not a perfect countermeasure to repel ALL XSS attack,
 * but it should prove to clean up 90% of known (and popular) attack vectors.</p>
 *
 * Created by fneman on 5/19/17.
 */
public class XSSFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(XSSFilter.class);

    /**
     * A flag to indicate whether HTML safe encoding will be applied to the input after XSS strip.
     * Please refer to ESAPI.encoder().encodeForHTML();
     */
    public static boolean HTML_SAVE_ENCODING = false;

    private XSSStripper stripper;

    /**
     * <p>Initialize the filter. It will load all stripper modules. In the future, list modules should be loaded as per configuration.</p>
     * @param filterConfig Filter configuration specified in web.xml
     * @throws ServletException Thrown if there is a problem while configuring the servlet, eg while instantiating module(s).
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        stripper = new XSSStripper();
        stripper.add(new EvalModule());
        stripper.add(new EvalModule());
        stripper.add(new EventModule());
        stripper.add(new ExpressionModule());
        stripper.add(new JavascriptModule());
        stripper.add(new LonelyScriptModule());
        stripper.add(new ScriptModule());
        stripper.add(new SrcModule());
        stripper.add(new VBScriptModule());
        stripper.add(new LocatorModule());
        stripper.add(new ElementModule());
        log.info("XSSFOE filter initialized....");
    }

    /**
     * <p>Destroy this filter. It does nothing.</p>
     */
    @Override
    public void destroy() {
    }

    /**
     * <p>Execute the request/response filtering.</p>
     * <p>It will wrap the request object into <code>XSSRequestWrapper</code> class and use the method in this wrapper to strip XSS attack vectors.</p>
     *
     * @param request The servlet request object that going to be wrapped.
     * @param response The servlet response object. So far, this servlet filter library will not do anything to the response.
     * @param chain FilterChain object.
     * @throws IOException Exception thrown if there is an error while doing IO operation on the filter.
     * @throws ServletException Exception thrown if there is a servlet error.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        chain.doFilter(new XSSRequestWrapper((HttpServletRequest) request, stripper), response);
    }

    public XSSStripper getStripper() {
        return stripper;
    }
}
