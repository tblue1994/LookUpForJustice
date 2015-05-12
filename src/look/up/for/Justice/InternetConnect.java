/*
Copyright (c) 2014 Nebraska Appleseed Center for Law in the Public Interest, Inc.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 */
package Look.Up.For.Justice;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * THis class connects to the internet, reads from an XML file, and 
 * @author Trevor Slawnyk with Nebraska Appleseed
 * @version 1.0
 */
public class InternetConnect {

    //this map is for FEDERAL representatives
    private static final Map<String, String> FEDERAL = new HashMap<>();

    static {
        FEDERAL.put("1", "Fortenberry");
        FEDERAL.put("2", "Terry");
        FEDERAL.put("3", "Smith");
    }

    /*
    This map is for STATE representatives and their info. To modify, one just needs 
    to find the corresponding district number and change the name and 
    email
    */
    private static final Map<String, String> STATE = new HashMap<>(); // map to complete State Senator Info

    static {
        STATE.put("1", "Watermeier,402-471-2733,dwatermeier@leg.ne.gov");
        STATE.put("2", "Kintner,402-471-2613,bkintner@leg.ne.gov");
        STATE.put("3", "Garrett,402-471-2627,tgarrett@leg.ne.gov");
        STATE.put("4", "Pirsch,402-471-2621,ppirsch@leg.ne.gov");
        STATE.put("5", "Mello,402-471-2710,hmello@leg.ne.gov");
        STATE.put("6", "Nelson,402-471-2714,jnelson@leg.ne.gov");
        STATE.put("7", "Nordquist,402-471-2721,jnordquist@leg.ne.gov");
        STATE.put("8", "Harr,402-471-2722,bharr@leg.ne.gov");
        STATE.put("9", "Howard,402-471-2723,showard@leg.ne.gov");
        STATE.put("10", "Krist,402-471-2718,bkrist@leg.ne.gov");
        STATE.put("11", "Chambers,402-471-2612,echambers@leg.ne.gov");
        STATE.put("12", "Lathrop,402-471-2623,slathrop@leg.ne.gov");
        STATE.put("13", "Cook,402-471-2727,tcook@leg.ne.gov");
        STATE.put("14", "Smith,402-471-2730,jsmith@leg.ne.gov");
        STATE.put("15", "Janssen,402-471-2625,cjanssen@leg.ne.gov");
        STATE.put("16", "Brasch,402-471-2728,lbrasch@leg.ne.gov");
        STATE.put("17", "Bloomfield,402-471-2716,dbloomfield@leg.ne.gov");
        STATE.put("18", "Lautenbaugh,402-471-2618,slautenbaugh@leg.ne.gov");
        STATE.put("19", "Scheer,402-471-2929,jscheer@leg.ne.gov");
        STATE.put("20", "Ashford,402-471-2622,bashford@leg.ne.gov");
        STATE.put("21", "Haar,402-471-2673,khaar@leg.ne.gov");
        STATE.put("22", "Schumacher,402-471-2715,pschumacher@leg.ne.gov");
        STATE.put("23", "Johnson,402-471-2719,jjohnson@leg.ne.gov");
        STATE.put("24", "Adams,402-471-2756,gadams@leg.ne.gov");
        STATE.put("25", "Campbell,402-471-2731,kcampbell@leg.ne.gov");
        STATE.put("26", "McGill,402-471-2610,amcgill@leg.ne.gov");
        STATE.put("27", "Coash,402-471-2632,ccoash@leg.ne.gov");
        STATE.put("28", "Avery,402-471-2633,bavery@leg.ne.gov");
        STATE.put("29", "Bolz,402-471-2734,kbolz@leg.ne.gov");
        STATE.put("30", "Wallman,402-471-2620,nwallman@leg.ne.gov");
        STATE.put("31", "Kolowski,402-471-2327,rkolowski@leg.ne.gov");
        STATE.put("32", "Karpisek,402-471-2711,rkarpisek@leg.ne.gov");
        STATE.put("33", "Seiler,402-471-2712,lseiler@leg.ne.gov");
        STATE.put("34", "Dubas,402-471-2630,adubas@leg.ne.gov");
        STATE.put("35", "Gloor,402-471-2617,mgloor@leg.ne.gov");
        STATE.put("36", "Wightman,402-471-2642,jwightman@leg.ne.gov");
        STATE.put("37", "Hadley,402-471-2726,ghadley@leg.ne.gov");
        STATE.put("38", "Carlson,402-471-2732,tcarlson@leg.ne.gov");
        STATE.put("39", "McCoy,402-471-2885,bmccoy@leg.ne.gov");
        STATE.put("40", "Larson,402-471-2801,tlarson@leg.ne.gov");
        STATE.put("41", "Sullivan,402-471-2631,ksullivan@leg.ne.gov");
        STATE.put("42", "Hansen,402-471-2729,thansen@leg.ne.gov");
        STATE.put("43", "Davis,402-471-2628,adavis@leg.ne.gov");
        STATE.put("44", "Christensen,402-471-2805,mchristensen@leg.ne.gov");
        STATE.put("45", "Crawford,402-471-2615,scrawfprd@leg.ne.gov");
        STATE.put("46", "Conrad,402-471-2720,dconrad@leg.ne.gov");
        STATE.put("47", "Schilz,402-471-2616,kschilz@leg.ne.gov");
        STATE.put("48", "Harms,402-471-2802,jharms@leg.ne.gov");
        STATE.put("49", "Murante,402-471-2725,jmurante@leg.ne.gov");
    }

    public InternetConnect() {

    }

    /**
     * Concatenates a URL with the Latitude and Longitude 
     * @param lat the latitude
     * @param lng the Longitude
     * @return the correct URL for Mobile commons to run
     */
    private URL createURL(double lat, double lng) throws Exception {
        try {

            URL mobileCommons = new URL(
                    "http://congress.mcommons.com/districts/lookup.xml?lat="
                    + lat + "&lng=" + lng);

            return mobileCommons;
        } catch (MalformedURLException e) {
            throw new Exception("The URLs do not seem to be working");
        }
    }

    /**
     *
     * @param e
     * @return
     */
    public Entry getInternets(Entry e) throws Exception {
        URL mc = createURL(e.getLat(), e.getLng());
        String federalDistrict = "";
        String stateDistrict = "";
        String stateName = "";
        try {
            //connects to the internet
            URLConnection mcConnect = mc.openConnection();
            // creates an inputStrem that the connection returns
            InputStream is = mcConnect.getInputStream();
            //Parses the InputStrem into a XML doc
            Document doc = parseXML(is);
            //Closes the InputStream
            is.close();
            
            //Searches the document for tags and gets the appropriate info
            NodeList nodes = doc.getElementsByTagName("federal");
            for (int i = 0; i < nodes.getLength(); i++) {
                Element element = (Element) nodes.item(i);

                NodeList state = element.getElementsByTagName("state");
                Element line2 = (Element) state.item(0);
                stateName = getCharacterDataFromElement(line2);

                NodeList fedDist = element.getElementsByTagName("district");
                Element line = (Element) fedDist.item(0);
                federalDistrict = getCharacterDataFromElement(line);
                federalDistrict = federalDistrict.replaceFirst("^0+(?!$)", "");
            }
            if (!stateName.equals("NE")) {
                
                return null;
            } else {

                NodeList nodes2 = doc.getElementsByTagName("state_upper");

                for (int i = 0; i < nodes2.getLength(); i++) {
                    Element element = (Element) nodes2.item(i);

                    NodeList stDist = element.getElementsByTagName("district");
                    Element line = (Element) stDist.item(0);
                    stateDistrict = getCharacterDataFromElement(line);
                }

                Entry complete = new Entry(e.getId(), e.getLat(), e.getLng(), federalDistrict,
                        getFedInfo(federalDistrict), stateDistrict, getStateInfo(stateDistrict));
                
                return complete;

            }
            

        } catch (ParserConfigurationException | SAXException | IOException e1) {
            throw new Exception("I'm sorry, the program did not work correctly. Chech your internet and file, and try again");
            
        }

    }

    /**
     * Gets info from an XML element
     * @param e the element
     * @return The info
     */
    public String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "";
    }

    /**
     * Uses the hash maps to match with the correct State senator
     * @param dist the district to match
     * @return the senator name and Info
     */
    private String getStateInfo(String dist) {
        String SenatorInfo = STATE.get(dist);
        return SenatorInfo;
    }

    /**
     * Uses the hash maps to match with the correct federal senator
     * @param dist the district to match
     * @return the senator name
     */
    private String getFedInfo(String dist) {
        String SenatorInfo = FEDERAL.get(dist);
        return SenatorInfo;
    }

    /**
     * Parses an Input stream into an XML doc
     * @param stream the stream to parse
     * @return An XML document
     * @throws Exception 
     */
    private Document parseXML(InputStream stream)
            throws Exception {
        DocumentBuilderFactory objDocumentBuilderFactory = null;
        DocumentBuilder objDocumentBuilder = null;
        Document doc = null;
        
        try {
            objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
            objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();
            
            doc = objDocumentBuilder.parse(stream);
            
        } catch (Exception ex) {
            throw ex;
        }

        return doc;
    }

}
