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

/**
 * POJO, and entry is each line in a document
 * @author Trevor Slawnyk with Nebraska Appleseed
 * @version 1.0
 */
public class Entry {

    private String id;
    private double lat;
    private double lng;
    private String fedDist;
    private String fedSenator;
    private String stateDist;
    private String stateSenator; 
   
    public Entry(String id, double lat, double lng) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
    }

    public Entry(String id, double lat, double lng, String fedDist, String fedSenator, String stateDist, String stateSenator) {
        this(id,lat,lng);
        this.fedDist = fedDist;
        this.fedSenator = fedSenator;
        this.stateDist = stateDist;
        this.stateSenator = stateSenator;
    }

    public String getId() {
        return id;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    /**
     * @return the fedDist
     */
    public String getFedDist() {
        return fedDist;
    }

    /**
     * @return the fedSenator
     */
    public String getFedSenator() {
        return fedSenator;
    }

    /**
     * @return the stateDist
     */
    public String getStateDist() {
        return stateDist;
    }

    /**
     * @return the stateSenator
     */
    public String getStateSenator() {
        return stateSenator;
    }
    
    @Override
    public String toString(){
        return id+","+ lat+","+lng+","+fedDist+","+fedSenator+","+stateDist+","+stateSenator;
    }

}
