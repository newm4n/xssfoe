package org.xssfoe.xss;

import junit.framework.TestCase;
import org.owasp.esapi.ESAPI;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fneman on 6/22/17.
 */
public class XSSStripperTest extends TestCase {

    String[] vector = null;
    String[] valid = null;
    XSSStripper stripper = null;

    @Override
    protected void setUp() throws Exception {
        InputStream is = XSSStripperTest.class.getResourceAsStream("/xssvector.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        List<String> l = new ArrayList<>();
        while(true) {
            String line = reader.readLine();
            if(line != null) {
                l.add(line.trim());
            } else {
                break;
            }
        }
        vector = l.toArray(new String[l.size()]);
        reader.close();

        is = XSSStripperTest.class.getResourceAsStream("/valid.txt");
        reader = new BufferedReader(new InputStreamReader(is));
        l = new ArrayList<>();
        while(true) {
            String line = reader.readLine();
            if(line != null) {
                l.add(line.trim());
            } else {
                break;
            }
        }
        valid = l.toArray(new String[l.size()]);
        reader.close();


        /*
         * This is how you setup your filter.
         */
        XSSFilter filter = new XSSFilter();
        filter.init(null);

        /*
         * This is how you get your stripper out of the filter.
         */
        stripper = filter.getStripper();
    }

    public void testStrip() throws Exception {

        for(String s : vector) {
            System.out.println(s);
            String strip = stripper.stripXSS(s);
            assertFalse(s.equals(strip));
            System.out.println("["+strip+"]\n\n");
        }

    }

    public void testStripValid() throws Exception {

        for(String s : valid) {
            String ss = ESAPI.encoder().canonicalize(s);
            System.out.println(s);
            String strip = stripper.stripXSS(s);
            System.out.println("["+strip+"]\n\n");
            assertTrue(ss.equals(strip));
        }

    }

}
