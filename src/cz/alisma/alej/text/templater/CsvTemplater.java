/* MIT License
 *
 * Copyright (c) 2019 Ivo Korinek
 * Copyright (c) 2019 Gymnazium Nad Aleji
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package cz.alisma.alej.text.templater;

import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class CsvTemplater {
    public static void main(String[] args) throws IOException {
        List<String> template = new ArrayList<String>();
        String outputFileName = "templater-out-%05d.txt";
        Scanner csvScanner = null;
        Scanner templateScanner = null;

        for (String str : args) {
            String[] splitArg = str.split("=");
            switch (splitArg[0]) {
                case "--csv":
                    csvScanner = new Scanner(new File(splitArg[1]));
                    csvScanner.useDelimiter(",|\n");
                    break;
                case "--template":
                    templateScanner = new Scanner(new File(splitArg[1]));
                    while (templateScanner.hasNextLine()) {
                        template.add(templateScanner.nextLine());
                    }
                    break;
                case "--out":
                    outputFileName = splitArg[1];
                    break;
                default:
                throw new IllegalArgumentException(
                    "Invalid argument! Possible arguments:\n--csv=[CSV filepath]\n--template=[template filepath]\n--out=[output filepath]"
                );
            }
        }

        boolean firstLine = true;
        String[] templateKeys = null;
        int outputFileNum = 1;

        while (csvScanner.hasNext()) {
            if (firstLine) {
                firstLine = false;
                templateKeys = csvScanner.nextLine().split(",");
            } else {
                Map<String, String> keys = new HashMap<String, String>();
                for (String key : templateKeys) {
                    keys.put(key, csvScanner.next());
                }

                OutputStream output = new FileOutputStream(
                    String.format(outputFileName, outputFileNum)
                );
                outputFileNum++;
                output.write(TemplateFiller.fill(template, keys).getBytes());
                output.close();
            }
        }
    }
}
