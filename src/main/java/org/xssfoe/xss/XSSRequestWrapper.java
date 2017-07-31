package org.xssfoe.xss;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Map;

/**
 * Wrap the ServletRequest object, this class will provide the XSS striping layer
 * to any methods related to information retrieval as it provided by web user using remote browser.
 *
 * Created by fneman on 5/19/17.
 */
public class XSSRequestWrapper extends HttpServletRequestWrapper {

    private XSSStripper stripper;

    /**
     * <p>Constructor for this wrapper.</p>
     *
     * @param request ServletRequest object to be wrapped by this wrapper.
     * @param stripper The stripper object that will do the XSS stripping on return values of wrapped method.
     */
    public XSSRequestWrapper(HttpServletRequest request, XSSStripper stripper) {
        super(request);
        this.stripper = stripper;
    }

    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);

        if (values == null) {
            return null;
        }

        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = stripper.stripXSS(values[i]);
        }

        return encodedValues;
    }

    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);

        return stripper.stripXSS(value);
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        return stripper.stripXSS(value);
    }

    @Override
    public String getQueryString() {
        return stripper.stripXSS(super.getQueryString());
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> ori = super.getParameterMap();
        if(ori == null) return null;

        Map<String, String[]> rets = new HashMap<>();
        for(String key : ori.keySet()) {
            String[] orival = ori.get(key);
            String[] vals = new String[orival.length];
            for(int i = 0; i < vals.length; i++) {
                vals[i] = stripper.stripXSS(orival[i]);
            }
            rets.put(key, vals);
        }
        return rets;
    }
}
