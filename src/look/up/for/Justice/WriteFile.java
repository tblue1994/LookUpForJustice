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
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class that writes the completed Nebraskan entries to a file
 * @author Trevor Slawnyk with Nebraska Appleseed
 * @version 1.0
 */
public class WriteFile {

    public WriteFile() {

    }

    /**
     * Writes a file that has the completed 
     * @param fileName the file path of the output file
     * @param entries an arrayList of completed entries
     * @throws java.lang.Exception
     */
    public void writeFile(String fileName, ArrayList<Entry> entries) throws Exception {
        
        File outputFile = new File(fileName);

        try (FileWriter outputWriter = new FileWriter(outputFile)) {
            String lines = "Database ID,Latitude,Longitude,Federal_District,Federal_Senator,State_District,"
                    + "State_Senator,State_Senator_Phone,State_Senator_Email \r\n";
            for (Entry entry : entries) {
                lines += entry.toString() + "\r\n";
            }
            outputWriter.write(lines);
        } catch (IOException e) {
            throw new Exception("Could not write to file");
        }
        
    }

}

