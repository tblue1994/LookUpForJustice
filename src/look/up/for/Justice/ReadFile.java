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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that reads a csv in the form "id,lat,lng" and parses into entries
 * @author Trevor Slawnyk with Nebraska Appleseed
 * @version 1.0
 */
public class ReadFile {

    Scanner myScanner;

    public ReadFile() {
        
    }

    /**
     * Parses the file into entries. The entries will be entry(id,lat,lng)
     * @param inFileName The file path of the file to parse
     * @return an ArrayList of entries
     */
    public ArrayList<Entry> readFile(String inFileName) throws Exception {
        ArrayList<Entry> entries = new ArrayList<Entry>();
        try {
            //creates a file
            File inputFile = new File(inFileName);

            //checks if the file is in correct format and exists
            if (inputFile.exists() && inputFile.canRead() && inFileName.endsWith(".csv")) {
                Scanner fScanner = new Scanner(inputFile);
                //gets rid of the header on the file
                fScanner.nextLine();

                //while the file has a next line, this breaks the lines into entries
                while (fScanner.hasNext()) {
                    String entryInfo = fScanner.nextLine();
                    entryInfo = entryInfo.replaceAll("\"", "");
                    String[] info = entryInfo.split(",");
                    String id = info[0];
                    double lat = Double.parseDouble(info[1]);
                    double lng = Double.parseDouble(info[2]);
                    Entry entry = new Entry(id, lat, lng);
                    entries.add(entry);

                }

                fScanner.close();

            } else {
                //throws this in case the file they try to enter does not exist or isnt in the correct format
                throw new FileNotFoundException();
            }
        } catch (FileNotFoundException e) {
            throw new Exception("I'm sorry, the file could not be read or it does not exist. Please check the format of the file as well");
        }
        return entries;
    }

}
