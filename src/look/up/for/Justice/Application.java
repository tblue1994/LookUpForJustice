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


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * The application of all other classes that ties everything together
 * @author Trevor Slawnyk with Nebraska Appleseed
 * @version 1.0
 */
public class Application {

    /**
     * The big "run" method, essentially bring all the other classes together. This also configures the results of the search
     * @param fileName The path name of the file
     * @return the Results in a concatenated string
     */
    public String runSearch(String fileName) throws Exception {
        //creates instances of the classes
        ReadFile read = new ReadFile();
        WriteFile write = new WriteFile();
        InternetConnect ic = new InternetConnect();
        int deleted = 0;
        //reads the entries and creates an ArrayList of entries
        ArrayList<Entry> entries = read.readFile(fileName);
        ArrayList<Entry> completes = new ArrayList<>();
        //Runs the getInternets() method and adds completed entries to a new list
        for (Entry e : entries) {
            Entry test = ic.getInternets(e);
            if (test != null) {
                completes.add(ic.getInternets(e));
            } else {
                deleted++;
            }
        }
        //simple check to see if completes is empty and acts accordingly
        if (completes.isEmpty()) { 
            throw new Exception("None of the entries were in State, so we did not write a file");
        } else {
            Date date = new Date();
            SimpleDateFormat f = new SimpleDateFormat("MM-dd-yyyy_hhmm");
            String outFileName = fileName.replaceAll(".csv", "COMPLETED_"+f.format(date)+".csv");
            write.writeFile(outFileName, completes);
            String results = "The File " + outFileName + " has been written. \nTotal entries= " + entries.size() + "\nSkipped (Out-of-State)= " + deleted + "\nCompleted (In-State)= " + completes.size();
            return results;
        }
    }
}
